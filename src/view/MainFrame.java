package view;

import domain.Member;
import enums.DifficultyType;
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
import view.menu.RegisterPanel;
import view.menu.StartMenuPanel;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final StartMenuPanel startMenuPanel;
    private final RegisterPanel registerPanel;
    private final LoginPanel loginPanel;
    private final MainMenuPanel mainMenuPanel;
    private final DifficultyPanel difficultyPanel;
    private final GamePanel gamePanel;

    // 현재 이 게임의 플레이어
    private Member player;

    // 게임 난이도
    private DifficultyType difficultyType;

    public MainFrame() {
        setTitle("타이핑 게임");
        // 화면 한 가운데로 설정
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(false);

        makeMenu();

        // 카드 레이아웃으로 설정
        getContentPane().setLayout(cardLayout);

        // 시작메뉴
        startMenuPanel = new StartMenuPanel(this);
        getContentPane().add("view.menu.StartMenuPanel", startMenuPanel);

        // 회원가입
        registerPanel = new RegisterPanel(this);
        getContentPane().add("view.menu.RegisterPanel", registerPanel);

        // 로그인
        loginPanel = new LoginPanel(this);
        getContentPane().add("view.menu.LoginPanel", loginPanel);

        // 메인메뉴
        mainMenuPanel = new MainMenuPanel(this);
        getContentPane().add("view.menu.MainMenuPanel", mainMenuPanel);

        // 난이도 선택
        difficultyPanel = new DifficultyPanel(this);
        getContentPane().add("view.menu.DifficultyPanel", difficultyPanel);

        // 게임
        gamePanel = new GamePanel(this);
        getContentPane().add("view.game.GamePanel", gamePanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void makeMenu() {
        JMenuBar mb = new JMenuBar();
        this.setJMenuBar(mb);

        // 게임 종료
        JMenu exitMenu = new JMenu("게임 종료");
        JMenuItem exitMi = exitMenu.add(new JMenuItem("종료"));
        exitMi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mb.add(exitMenu);

        JMenu editMenu = new JMenu("단어장");
        JMenuItem addWordMi = editMenu.add(new JMenuItem("단어 추가"));
        addWordMi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = JOptionPane.showInputDialog("추가할 단어를 입력하세요.");
                if (word != null) {
                    WordService.addWord(word);
                    JOptionPane.showMessageDialog(null, "단어가 추가되었습니다.", "단어 추가 성공", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "단어를 입력하지 않았습니다.", "단어 추가 오류", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        mb.add(editMenu);
    }

    public void changePanel(String panelName) {
        cardLayout.show(getContentPane(), panelName);
    }

    public Member getPlayer() {
        return player;
    }

    public void setPlayer(Member player) {
        this.player = player;
    }

    public DifficultyType getDifficultyType() {
        return difficultyType;
    }

    public void setDifficultyType(DifficultyType difficultyType) {
        this.difficultyType = difficultyType;
    }
}
