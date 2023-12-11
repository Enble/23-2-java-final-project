package view.game;

import domain.Bomb;
import enums.BombType;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import service.MemberService;
import service.RankService;
import service.WordService;
import view.MainFrame;

// 게임 화면을 담당하는 패널
public class MonitorPanel extends JPanel {
    private final MainFrame mainFrame;
    private final TypingLifePanel typingLifePanel;
    private final TimePanel timePanel;
    private final ItemPanel itemPanel;

    // 폭탄이 저장되는 배열.
    // 동시성 문제를 해결하기 위해 CopyOnWriteArrayList를 사용한다.
    private final List<Bomb> bombs = new CopyOnWriteArrayList<>();

    // 폭탄 이미지
    private final ImageIcon bombIi = new ImageIcon("src/images/bomb.png");
    private final Image smallBombImage = bombIi.getImage().getScaledInstance(20, 40, Image.SCALE_SMOOTH);
    private final Image mediumBombImage = bombIi.getImage().getScaledInstance(30, 60, Image.SCALE_SMOOTH);
    private final Image largeBombImage = bombIi.getImage().getScaledInstance(40, 80, Image.SCALE_SMOOTH);
    private final Image nukeBombImage = bombIi.getImage().getScaledInstance(60, 120, Image.SCALE_SMOOTH);

    // 배경 이미지
    private final ImageIcon backgroundIi = new ImageIcon("src/images/background.png");

    // 게임 시작 버튼
    private JButton startButton;
    // 게임 스레드
    private GameThread gameThread;
    // Thread sleep 시간 (폭탄 낙하 속도 및 생성 속도 조절)
    private int sleepTime = 30;
    // 폭탄 낙하 중지를 위한 flag
    private boolean stopFlag = false;

    // 시간 스레드
    private TimeThread timeThread;
    // 게임 시작 시간
    private Instant startTime;

    public MonitorPanel(MainFrame mainFrame, TypingLifePanel typingLifePanel, TimePanel timePanel,
                        ItemPanel itemPanel) {
        this.mainFrame = mainFrame;
        this.typingLifePanel = typingLifePanel;
        this.timePanel = timePanel;
        this.itemPanel = itemPanel;

        // 레이아웃을 null 로 설정
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 배경 그리기
        g.drawImage(backgroundIi.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);

        // 폭탄 그리기
        for (Bomb bomb : bombs) {
            Point location = bomb.getLocation();
            if (bomb.getBombType() == BombType.SMALL) {
                g.drawImage(smallBombImage, location.x, location.y, this);
            } else if (bomb.getBombType() == BombType.MEDIUM) {
                g.drawImage(mediumBombImage, location.x, location.y, this);
            } else if (bomb.getBombType() == BombType.LARGE) {
                g.drawImage(largeBombImage, location.x, location.y, this);
            } else if (bomb.getBombType() == BombType.NUKE) {
                g.drawImage(nukeBombImage, location.x, location.y, this);
            }
            g.setColor(Color.white);
            g.setFont(new Font("맑은 고딕", Font.BOLD, 15));
            g.drawString(bomb.getWord(), location.x, location.y);
        }
    }

    // 게임 시작
    public void startGame() {
        // 게임시작버튼 추가
        startButton = new JButton("게임시작");
        // 게임 시작 버튼
        startButton.setBounds(MonitorPanel.this.getWidth() / 2 - 50, MonitorPanel.this.getHeight() / 2 - 25,
                100, 50);
        // 게임시작버튼 클릭시 게임시작
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 게임시작버튼 제거
                remove(startButton);

                // 게임 스레드 시작
                gameThread = new GameThread();
                gameThread.start();

                // 시간 스레드 시작
                timeThread = new TimeThread();
                timeThread.start();

                // 타이핑 필드의 포커스 요청
                typingLifePanel.requestTypingFieldFocus();
            }
        });
        add(startButton);
    }

    // 게임 중지
    public void stopGame() {
        // 모든 폭탄 제거
        bombs.clear();
        // 게임 스레드 중지
        if (gameThread != null) {
            gameThread.interrupt();
        }
        // 시간 스레드 중지
        if (timeThread != null) {
            timeThread.interrupt();
        }
    }

    // UI 초기화
    public void clearUI() {
        // 시간 라벨 초기화
        timePanel.resetTimeLabel();
        // 게임시작버튼 제거
        if (startButton != null) {
            remove(startButton);
        }
    }

    // 폭탄 제거
    public boolean removeBomb(String word) {
        for (Bomb bomb : bombs) {
            if (bomb.getWord().equals(word)) {
                bombs.remove(bomb);
                this.repaint();
                return true;
            }
        }
        return false;
    }

    // 모든 폭탄 제거
    public void removeAllBomb() {
        bombs.clear();
        this.repaint();
    }

    // 게임 스레드 sleep 시간 설정
    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    // 폭탄 낙하 중지
    public void pauseBomb() {
        gameThread.stopThread();
    }

    // 폭탄 낙하 재개
    public void resumeBomb() {
        gameThread.resumeThread();
    }

    /**
     * 게임 스레드
     */
    class GameThread extends Thread {
        private long tickCount = 0;

        // 스레드 일시정지
        public void stopThread() {
            stopFlag = true;
        }

        // 스레드 재개
        public synchronized void resumeThread() {
            stopFlag = false;
            notifyAll();
        }

        // 스레드 일시정지 여부 확인
        private synchronized void checkThreadPause() {
            try {
                if (stopFlag) {
                    wait();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    // 맞바람 아이템 사용이 감지되면 스레드 일시정지
                    checkThreadPause();

                    // tickCount가 50의 배수일 때마다 폭탄 생성
                    if (tickCount % 50 == 0) {
                        addBomb();
                    }

                    // tickCount가 1000의 배수일 때마다 폭탄 속도 증가
                    if (tickCount % 1000 == 0 && sleepTime > 1) {
                        sleepTime--;
                    }

                    // 폭탄 이동
                    changeBombLocation();

                    // 체력이 0이 되면 게임 종료
                    checkLife();

                    // tickCount 증가 및 sleep
                    tickCount++;
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // 체력이 0이 되면 게임 종료
        private void checkLife() {
            if (typingLifePanel.isDead()) {
                // 게임 중지
                stopGame();

                // 스레드의 interrupt()가 충분히 호출될 수 있도록 invokeLater()를 사용한다.
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // 점수 동기화 및 유저 정보 업데이트
                        Duration score = Duration.between(startTime, Instant.now());
                        timePanel.setTimeLabel(Duration.between(startTime, Instant.now()));

                        // 유저 기록 업데이트
                        mainFrame.getPlayer().setScore(score);
                        // 유저 정보 업데이트
                        MemberService.getInstance().update(mainFrame.getPlayer());

                        // 랭킹 보드 업데이트
                        RankService.getInstance().updateRankBoard(mainFrame.getPlayer());

                        // 게임 종료 메시지
                        JOptionPane.showMessageDialog(MonitorPanel.this, "게임 오버!", "게임 오버",
                                JOptionPane.INFORMATION_MESSAGE);

                        // ui 초기화 후 패널 전환
                        clearUI();
                        mainFrame.changePanel("view.menu.MainMenuPanel");
                    }
                });
            }
        }

        // 폭탄 생성
        private void addBomb() {
            // 폭탄 생성
            BombType bombType = BombType.generateRandomBombType(mainFrame.getPlayer().getDifficultyType());
            Point location = new Point(
                    (int) (Math.random() * MonitorPanel.this.getWidth() * 0.8 + MonitorPanel.this.getWidth() * 0.1), 0);
            String word = WordService.nextWord();

            Bomb bomb = new Bomb(location, word, bombType);
            bombs.add(bomb);
        }

        // 폭탄을 아래로 이동
        private void changeBombLocation() {
            for (Bomb bomb : bombs) {
                // 폭탄 위치 변경
                Point location = bomb.getLocation();
                int speed = bomb.getBombType().getSpeed();
                location.y += speed;

                // 폭탄이 화면 아래로 벗어나면 체력 감소 및 폭탄 제거
                if (location.y > MonitorPanel.this.getHeight()) {
                    typingLifePanel.decreaseLife(bomb.getBombType().getDamage());
                    bombs.remove(bomb);
                }

                // 화면 다시 그리기
                MonitorPanel.this.repaint();
            }
        }
    }

    /**
     * 시간 스레드
     */
    class TimeThread extends Thread {
        @Override
        public void run() {
            try {
                // 게임 시작 시간 저장
                startTime = Instant.now();
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(10);
                    // 시간 라벨 업데이트
                    timePanel.setTimeLabel(Duration.between(startTime, Instant.now()));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
