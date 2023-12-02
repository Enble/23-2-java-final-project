package view.game;

import java.awt.Color;
import java.awt.Font;
import java.time.Duration;
import java.util.Timer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class TimePanel extends JPanel {
    private final GamePanel gamePanel;

    private final JLabel idLabel = new JLabel("아이디");
    private final JLabel timeLabel = new JLabel("00 : 00 : 000");

    public TimePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(10, 0, 0, 0));

        // 아이디
        idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        idLabel.setForeground(Color.blue);
        idLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(idLabel);

        // padding
        add(Box.createRigidArea(new java.awt.Dimension(0, 10)));

        // 시간
        timeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 45));
        timeLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(timeLabel);
    }

    public void setIdLabel(String id) {
        idLabel.setText(id);
    }

    public void setTimeLabel(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long secs = duration.getSeconds() % 60;
        long millis = duration.toMillis() % 1000;

        if (hours == 0) {
            timeLabel.setText(String.format("%02d : %02d : %03d", minutes, secs, millis));
        } else {
            timeLabel.setText(String.format("%02d : %02d : %02d : %03d", hours, minutes, secs, millis));
        }

        this.repaint();
    }

    public void resetTimeLabel() {
        timeLabel.setText("00 : 00 : 000");
    }
}
