package view;

import java.awt.CardLayout;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private StartMenuPanel startMenuPanel;
    private RegisterPanel registerPanel;
    private LoginPanel loginPanel;
    private GamePanel gamePanel;

    public GameFrame() {
        setTitle("타이핑 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        getContentPane().setLayout(cardLayout);

        startMenuPanel = new StartMenuPanel(this);
        getContentPane().add("view.StartMenuPanel", startMenuPanel);

        registerPanel = new RegisterPanel();
        getContentPane().add("view.RegisterPanel", registerPanel);

        loginPanel = new LoginPanel();
        getContentPane().add("view.LoginPanel", loginPanel);

        gamePanel = new GamePanel();
        getContentPane().add("view.GamePanel", gamePanel);

        setVisible(true);
    }

    public void changePanel(String panelName) {
        cardLayout.show(getContentPane(), panelName);
    }
}
