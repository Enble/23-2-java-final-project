package view;

import java.awt.CardLayout;
import javax.swing.JFrame;

public class MainFrame extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private StartMenuPanel startMenuPanel;
    private RegisterPanel registerPanel;
    private LoginPanel loginPanel;
    private GamePanel gamePanel;

    public MainFrame() {
        setTitle("타이핑 게임");
        // 화면 한 가운데로 설정
        setSize(1200, 800);
        setLocationRelativeTo(null);

        // 카드 레이아웃으로 설정
        getContentPane().setLayout(cardLayout);

        // 시작메뉴
        startMenuPanel = new StartMenuPanel(this);
        getContentPane().add("view.StartMenuPanel", startMenuPanel);

        // 회원가입
        registerPanel = new RegisterPanel(this);
        getContentPane().add("view.RegisterPanel", registerPanel);

        // 로그인
        loginPanel = new LoginPanel();
        getContentPane().add("view.LoginPanel", loginPanel);

        // 게임
        gamePanel = new GamePanel();
        getContentPane().add("view.GamePanel", gamePanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void changePanel(String panelName) {
        cardLayout.show(getContentPane(), panelName);
    }
}
