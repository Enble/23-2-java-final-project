package view.game;

import enums.ItemType;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import view.MainFrame;

public class GamePanel extends JPanel {
    private final MonitorPanel monitorPanel;
    private final TypingLifePanel typingLifePanel;
    private final TimePanel timePanel;
    private final ItemPanel itemPanel;

    public GamePanel(MainFrame mainFrame) {
        typingLifePanel = new TypingLifePanel(this);
        timePanel = new TimePanel(this);
        itemPanel = new ItemPanel(this);
        monitorPanel = new MonitorPanel(mainFrame, typingLifePanel, timePanel, itemPanel);

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
        vPaneLeft.setDividerLocation(685);
        vPaneLeft.setEnabled(false);

        vPaneLeft.setTopComponent(monitorPanel);
        vPaneLeft.setBottomComponent(typingLifePanel);

        JSplitPane vPaneRight = new JSplitPane();
        vPaneRight.setOrientation(JSplitPane.VERTICAL_SPLIT);
        vPaneRight.setDividerLocation(140);
        vPaneRight.setEnabled(false);

        vPaneRight.setTopComponent(timePanel);
        vPaneRight.setBottomComponent(itemPanel);

        hPane.setLeftComponent(vPaneLeft);
        hPane.setRightComponent(vPaneRight);
    }

    public MonitorPanel getMonitorPanel() {
        return monitorPanel;
    }

    public TypingLifePanel getTypingPanel() {
        return typingLifePanel;
    }

    public TimePanel getTimePanel() {
        return timePanel;
    }

    public ItemPanel getItemPanel() {
        return itemPanel;
    }
}
