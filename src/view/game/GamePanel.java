package view.game;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import view.MainFrame;

// 게임과 관련된 패널들의 최상위 패널
public class GamePanel extends JPanel {
    // 게임 모니터 패널
    private final MonitorPanel monitorPanel;
    // 타이핑 패널
    private final TypingLifePanel typingLifePanel;
    // 타이머 패널
    private final TimePanel timePanel;
    // 아이템 패널
    private final ItemPanel itemPanel;

    public GamePanel(MainFrame mainFrame) {
        // 패널 생성
        typingLifePanel = new TypingLifePanel(this);
        timePanel = new TimePanel(this);
        itemPanel = new ItemPanel(this);
        monitorPanel = new MonitorPanel(mainFrame, typingLifePanel, timePanel, itemPanel);

        setLayout(new BorderLayout());
        splitPanel();
    }

    // 패널 분할
    private void splitPanel() {
        // 수평 분할
        JSplitPane hPane = new JSplitPane();
        hPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        hPane.setDividerLocation(850);
        hPane.setEnabled(false);
        this.add(hPane);

        // 수직 분할 : 왼쪽
        JSplitPane vPaneLeft = new JSplitPane();
        vPaneLeft.setOrientation(JSplitPane.VERTICAL_SPLIT);
        vPaneLeft.setDividerLocation(685);
        vPaneLeft.setEnabled(false);

        vPaneLeft.setTopComponent(monitorPanel);
        vPaneLeft.setBottomComponent(typingLifePanel);

        // 수직 분할 : 오른쪽
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
