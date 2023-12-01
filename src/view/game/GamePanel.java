package view.game;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import view.MainFrame;

public class GamePanel extends JPanel {
    private final MainFrame mainFrame;
    private final MonitorPanel monitorPanel;
    private final TypingPanel typingPanel;
    private final ScorePanel scorePanel;
    private final ItemPanel itemPanel;

    public GamePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        typingPanel = new TypingPanel(this);
        scorePanel = new ScorePanel();
        itemPanel = new ItemPanel();
        monitorPanel = new MonitorPanel(mainFrame, typingPanel, scorePanel, itemPanel);

        setLayout(new BorderLayout());
        splitPanel();
    }

    private void splitPanel() {
        JSplitPane hPane = new JSplitPane();
        hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        hPane.setDividerLocation(850);
        hPane.setEnabled(false);
        this.add(hPane);

        JSplitPane vPaneLeft = new JSplitPane();
        vPaneLeft.setOrientation(JSplitPane.VERTICAL_SPLIT);
        vPaneLeft.setDividerLocation(650);
        vPaneLeft.setEnabled(false);

        vPaneLeft.setTopComponent(monitorPanel);
        vPaneLeft.setBottomComponent(typingPanel);

        JSplitPane vPaneRight = new JSplitPane();
        vPaneRight.setOrientation(JSplitPane.VERTICAL_SPLIT);
        vPaneRight.setDividerLocation(250);
        vPaneRight.setEnabled(false);

        vPaneRight.setTopComponent(scorePanel);
        vPaneRight.setBottomComponent(itemPanel);

        hPane.setLeftComponent(vPaneLeft);
        hPane.setRightComponent(vPaneRight);
    }

    public MonitorPanel getMonitorPanel() {
        return monitorPanel;
    }
}
