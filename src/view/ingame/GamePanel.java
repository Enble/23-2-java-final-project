package view.ingame;

import domain.GameController;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class GamePanel extends JPanel {
    private GameGround gameGround = new GameGround();
    private TypingBar typingBar = new TypingBar();
    private ScorePanel scorePanel = new ScorePanel();
    private ItemPanel itemPanel = new ItemPanel();

    private final GameController gameController = new GameController(gameGround, typingBar, scorePanel, itemPanel);

    public GamePanel() {
        setLayout(new BorderLayout());
        splitPanel();
    }

    private void splitPanel() {
        JSplitPane hPane = new JSplitPane();
        hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        hPane.setDividerLocation(800);
        hPane.setEnabled(false);
        this.add(hPane);

        JSplitPane vPaneLeft = new JSplitPane();
        vPaneLeft.setOrientation(JSplitPane.VERTICAL_SPLIT);
        vPaneLeft.setDividerLocation(650);
        vPaneLeft.setEnabled(false);

        vPaneLeft.setTopComponent(gameGround);
        vPaneLeft.setBottomComponent(typingBar);

        JSplitPane vPaneRight = new JSplitPane();
        vPaneRight.setOrientation(JSplitPane.VERTICAL_SPLIT);
        vPaneRight.setDividerLocation(250);
        vPaneRight.setEnabled(false);

        vPaneRight.setTopComponent(scorePanel);
        vPaneRight.setBottomComponent(itemPanel);

        hPane.setLeftComponent(vPaneLeft);
        hPane.setRightComponent(vPaneRight);
    }
}
