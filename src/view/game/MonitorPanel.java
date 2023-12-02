package view.game;

import domain.Bomb;
import enums.BombType;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import service.WordService;
import view.MainFrame;

public class MonitorPanel extends JPanel {
    private final MainFrame mainFrame;
    private final TypingPanel typingPanel;
    private final TimePanel timePanel;
    private final ItemPanel itemPanel;

    // 폭탄이 저장되는 배열. 내부적으로 JLabel을 들고 있다.
    // 동시성 문제를 해결하기 위해 CopyOnWriteArrayList를 사용한다.
    private final List<Bomb> bombs = new CopyOnWriteArrayList<>();
    private final ImageIcon bombIi = new ImageIcon("src/images/bomb.png");
    private final Image smallBombImage = bombIi.getImage().getScaledInstance(20, 40, Image.SCALE_SMOOTH);
    private final Image mediumBombImage = bombIi.getImage().getScaledInstance(30, 60, Image.SCALE_SMOOTH);
    private final Image largeBombImage = bombIi.getImage().getScaledInstance(40, 80, Image.SCALE_SMOOTH);
    private final Image nukeBombImage = bombIi.getImage().getScaledInstance(60, 120, Image.SCALE_SMOOTH);
    private final ImageIcon backgroundIi = new ImageIcon("src/images/background.png");

    // 게임 시작 버튼
    private JButton startButton;

    // 게임 스레드
    private GameThread gameThread;

    // Thread sleep 시간
    private int sleepTime = 30;

    public MonitorPanel(MainFrame mainFrame, TypingPanel typingPanel, TimePanel timePanel, ItemPanel itemPanel) {
        this.mainFrame = mainFrame;
        this.typingPanel = typingPanel;
        this.timePanel = timePanel;
        this.itemPanel = itemPanel;

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
            g.drawString(bomb.getWord(), location.x, location.y);
        }
    }

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

                gameThread = new GameThread();
                gameThread.start();
            }
        });
        add(startButton);
    }

    public void removeBomb(String word) {
        for (Bomb bomb : bombs) {
            if (bomb.getWord().equals(word)) {
                bombs.remove(bomb);
                break;
            }
        }
        this.repaint();
    }

    public void removeAllBombs() {
        bombs.clear();
        this.repaint();
    }

    public void setSleepTime(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    public void stopGame() {
        bombs.clear();
        gameThread.interrupt();
    }

    /**
     * 게임 스레드
     */
    class GameThread extends Thread {
        private int tickCount = 0;

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("sleepTime: " + sleepTime);

                    if (tickCount == 50) {
                        tickCount = 0;
                        addBomb();
                    }
                    changeBombLocation();

                    tickCount++;
                    Thread.sleep(sleepTime);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        private void addBomb() {
            // 폭탄 생성
            BombType bombType = BombType.generateRandomBombType(mainFrame.getDifficultyType());
            Point location = new Point(
                    (int) (Math.random() * MonitorPanel.this.getWidth() * 0.8 + MonitorPanel.this.getWidth() * 0.1), 0);
            String word = WordService.nextWord();

            Bomb bomb = new Bomb(location, word, bombType);
            bombs.add(bomb);
        }

        private void changeBombLocation() {
            for (Bomb bomb : bombs) {
                Point location = bomb.getLocation();
                int speed = bomb.getBombType().getSpeed();
                location.y += speed;

                if (location.y > MonitorPanel.this.getHeight()) {
                    typingPanel.decreaseLife(bomb.getBombType().getDamage());
                    bombs.remove(bomb);
                }

                MonitorPanel.this.repaint();
            }
        }
    }
}
