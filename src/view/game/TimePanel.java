package view.game;

import java.awt.Color;
import java.awt.Font;
import java.time.Duration;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import service.TimeTransfer;

// 유저의 아이디와 게임 타이머를 표시하는 패널
public class TimePanel extends JPanel {
    private final GamePanel gamePanel;

    // 아이디와 시간을 표시하는 라벨
    private final JLabel idLabel = new JLabel("아이디");
    private final JLabel timeLabel = new JLabel("00 : 00 : 000");

    public TimePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        // BoxLayout으로 세로 정렬
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 0, 0, 0));

        // 아이디 라벨
        idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        idLabel.setForeground(Color.blue);
        idLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(idLabel);

        // padding
        add(Box.createRigidArea(new java.awt.Dimension(0, 10)));

        // 시간 라벨
        timeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 45));
        timeLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(timeLabel);
    }

    // 시간 라벨 갱신
    public void setTimeLabel(Duration duration) {
        String result = TimeTransfer.durationToStringFormat(duration);
        timeLabel.setText(result);

        this.repaint();
    }

    // 시간 라벨  초기화
    public void resetTimeLabel() {
        timeLabel.setText("00 : 00 : 000");
    }

    public void setIdLabel(String id) {
        idLabel.setText(id);
    }
}
