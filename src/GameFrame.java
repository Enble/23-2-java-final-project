import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private StartMenuPanel startMenuPanel;
    private GamePanel gamePanel;
    private LoginPanel loginPanel;

    public GameFrame() {
        setTitle("타이핑 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        getContentPane().setLayout(cardLayout);

        setVisible(true);
    }
}
