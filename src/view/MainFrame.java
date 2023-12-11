package view;

import domain.Member;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import service.WordService;
import view.game.GamePanel;
import view.menu.DifficultyPanel;
import view.menu.LoginPanel;
import view.menu.MainMenuPanel;
import view.menu.RankPanel;
import view.menu.RegisterPanel;
import view.menu.StartMenuPanel;

// 메인 프레임
public class MainFrame extends JFrame {
    // 카드 레이아웃
    private final CardLayout cardLayout = new CardLayout();
    private final GamePanel gamePanel;
    private final RankPanel rankPanel;

    // 현재 이 게임의 플레이어
    private Member player;

    public MainFrame() {
        setTitle("타이핑 게임");
        // 화면 한 가운데로 설정
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(false);

        // 메뉴바 생성
        makeMenu();

        // 카드 레이아웃으로 설정
        getContentPane().setLayout(cardLayout);

        // 시작메뉴
        StartMenuPanel startMenuPanel = new StartMenuPanel(this);
        getContentPane().add("view.menu.StartMenuPanel", startMenuPanel);

        // 회원가입
        RegisterPanel registerPanel = new RegisterPanel(this);
        getContentPane().add("view.menu.RegisterPanel", registerPanel);

        // 로그인
        LoginPanel loginPanel = new LoginPanel(this);
        getContentPane().add("view.menu.LoginPanel", loginPanel);

        // 메인메뉴
        MainMenuPanel mainMenuPanel = new MainMenuPanel(this);
        getContentPane().add("view.menu.MainMenuPanel", mainMenuPanel);

        // 난이도 선택
        DifficultyPanel difficultyPanel = new DifficultyPanel(this);
        getContentPane().add("view.menu.DifficultyPanel", difficultyPanel);

        // 게임
        gamePanel = new GamePanel(this);
        getContentPane().add("view.game.GamePanel", gamePanel);

        // 랭킹
        rankPanel = new RankPanel(this);
        getContentPane().add("view.menu.RankPanel", rankPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // 메뉴바 생성
    private void makeMenu() {
        // 메뉴바 생성
        JMenuBar mb = new JMenuBar();
        this.setJMenuBar(mb);

        /*
          게임 메뉴
         */
        JMenu gameMenu = new JMenu("게임 메뉴");

        // 메인 메뉴로 돌아가기
        JMenuItem mainMenuMi = gameMenu.add(new JMenuItem("메인 메뉴"));
        mainMenuMi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 로그인이 되어있지 않으면 메인 메뉴로 돌아갈 수 없음
                if (player == null) {
                    JOptionPane.showMessageDialog(null, "로그인이 필요합니다.", "로그인 에러", JOptionPane.ERROR_MESSAGE);
                } else {
                    gamePanel.getMonitorPanel().stopGame();
                    gamePanel.getMonitorPanel().clearUI();
                    changePanel("view.menu.MainMenuPanel");
                }
            }
        });

        // 로그아웃
        JMenuItem logoutMi = gameMenu.add(new JMenuItem("로그아웃"));
        logoutMi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 게임이 진행 중이면 게임을 종료하고 로그아웃
                gamePanel.getMonitorPanel().stopGame();
                setPlayer(null);
                changePanel("view.menu.StartMenuPanel");
            }
        });

        // 게임 종료
        JMenuItem exitMi = gameMenu.add(new JMenuItem("게임 종료"));
        exitMi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        mb.add(gameMenu);

        /*
          단어장 메뉴
         */
        JMenu editMenu = new JMenu("단어장");

        // 단어 추가
        JMenuItem addWordMi = editMenu.add(new JMenuItem("단어 추가"));
        addWordMi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 단어 추가
                String word = JOptionPane.showInputDialog("추가할 단어를 입력하세요.");
                // 단어가 null이 아니면 단어 추가
                if (word != null) {
                    WordService.addWord(word);
                    JOptionPane.showMessageDialog(null, "단어가 추가되었습니다.", "단어 추가 성공", JOptionPane.INFORMATION_MESSAGE);
                } else { // 단어가 null이면 단어 추가 실패
                    JOptionPane.showMessageDialog(null, "단어를 입력하지 않았습니다.", "단어 추가 오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        mb.add(editMenu);
    }

    // 패널 변경
    public void changePanel(String panelName) {
        cardLayout.show(getContentPane(), panelName);
    }

    public Member getPlayer() {
        return player;
    }

    public void setPlayer(Member player) {
        this.player = player;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public RankPanel getRankPanel() {
        return rankPanel;
    }

}
