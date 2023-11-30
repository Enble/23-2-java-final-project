package view;

import domain.Member;
import java.awt.CardLayout;
import javax.swing.JFrame;
import view.beforegame.LoginPanel;
import view.beforegame.MainMenuPanel;
import view.beforegame.RegisterPanel;
import view.beforegame.StartMenuPanel;

public class MainFrame extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private StartMenuPanel startMenuPanel;
    private RegisterPanel registerPanel;
    private LoginPanel loginPanel;
    private MainMenuPanel mainMenuPanel;
    private GamePanel gamePanel;

    // 현재 이 게임의 플레이어
    private Member player;

    public MainFrame() {
        setTitle("타이핑 게임");
        // 화면 한 가운데로 설정
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setResizable(false);

        // 카드 레이아웃으로 설정
        getContentPane().setLayout(cardLayout);

        // 시작메뉴
        startMenuPanel = new StartMenuPanel(this);
        getContentPane().add("view.beforegame.StartMenuPanel", startMenuPanel);

        // 회원가입
        registerPanel = new RegisterPanel(this);
        getContentPane().add("view.beforegame.RegisterPanel", registerPanel);

        // 로그인
        loginPanel = new LoginPanel(this);
        getContentPane().add("view.beforegame.LoginPanel", loginPanel);

        // 메인메뉴
        mainMenuPanel = new MainMenuPanel(this);
        getContentPane().add("view.beforegame.MainMenuPanel", mainMenuPanel);

        // 게임
        gamePanel = new GamePanel();
        getContentPane().add("view.GamePanel", gamePanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
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
}
