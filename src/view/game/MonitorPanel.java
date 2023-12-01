package view.game;

import domain.Bomb;
import enums.BombType;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import service.WordService;
import view.MainFrame;

public class MonitorPanel extends JPanel {
    private final MainFrame mainFrame;
    private final TypingPanel typingPanel;
    private final ScorePanel scorePanel;
    private final ItemPanel itemPanel;

    private List<Bomb> bombs = new ArrayList<>();
    private ImageIcon bombIi = new ImageIcon("src/images/bomb.png");
    private Image smallBombImage = bombIi.getImage().getScaledInstance(20, 40, Image.SCALE_SMOOTH);
    private Image mediumBombImage = bombIi.getImage().getScaledInstance(30, 60, Image.SCALE_SMOOTH);
    private Image largeBombImage = bombIi.getImage().getScaledInstance(40, 80, Image.SCALE_SMOOTH);
    private Image nukeBombImage = bombIi.getImage().getScaledInstance(60, 120, Image.SCALE_SMOOTH);
    private ImageIcon backgroundIi = new ImageIcon("src/images/background.png");

    // 게임 시작 버튼
    JButton startButton = new JButton("게임시작");

    // 게임 스레드
    private GameThread gameThread;

    public MonitorPanel(MainFrame mainFrame, TypingPanel typingPanel, ScorePanel scorePanel, ItemPanel itemPanel) {
        this.mainFrame = mainFrame;
        this.typingPanel = typingPanel;
        this.scorePanel = scorePanel;
        this.itemPanel = itemPanel;

        setLayout(null);

        // 게임시작버튼 추가
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // 체력 설정
                typingPanel.setLifeBasedOnDifficulty(mainFrame.getDifficultyType());

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
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 배경 그리기
        g.drawImage(backgroundIi.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);

        // 폭탄 그리기
        for (Bomb bomb : bombs) {
            Point location = bomb.getLocation();
            if(bomb.getBombType() == BombType.SMALL) {
                g.drawImage(smallBombImage, location.x, location.y, this);
            } else if(bomb.getBombType() == BombType.MEDIUM) {
                g.drawImage(mediumBombImage, location.x, location.y, this);
            } else if(bomb.getBombType() == BombType.LARGE) {
                g.drawImage(largeBombImage, location.x, location.y, this);
            } else if(bomb.getBombType() == BombType.NUKE) {
                g.drawImage(nukeBombImage, location.x, location.y, this);
            }
            g.drawString(bomb.getWord(), location.x, location.y);
        }
    }

    public void removeBomb(String word) {
        Iterator<Bomb> bombIterator = bombs.iterator();
        while (bombIterator.hasNext()) {
            Bomb bomb = bombIterator.next();
            if (bomb.getWord().equals(word)) {
                bombIterator.remove();
//                scorePanel.increaseScore();
                break;
            }
        }
        this.repaint();
    }

    /**
     * 게임 스레드
     */
    class GameThread extends Thread {
        private int tickCount = 0;

        @Override
        public void run() {
            while (true) {
                if (tickCount == 50) {
                    tickCount = 0;
                    addBomb();
                }
                changeBombLocation();

                tickCount++;
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void addBomb() {
            // 폭탄 생성
            BombType bombType = BombType.generateRandomBombType(mainFrame.getDifficultyType());
            Point location = new Point((int) (Math.random() * MonitorPanel.this.getWidth()), 0);
            String word = WordService.nextWord();

            Bomb bomb = new Bomb(location, word, bombType);
            bombs.add(bomb);
        }

        private void changeBombLocation() {
            Iterator<Bomb> bombIterator = bombs.iterator();
            while (bombIterator.hasNext()) {
                Bomb bomb = bombIterator.next();
                Point location = bomb.getLocation();
                int speed = bomb.getBombType().getSpeed();
                location.y += speed;

                if (location.y > mainFrame.getHeight()) {
                    typingPanel.decreaseLife(bomb.getBombType().getDamage());
                    bombIterator.remove();
                }

                MonitorPanel.this.repaint();
            }
        }
    }
}
