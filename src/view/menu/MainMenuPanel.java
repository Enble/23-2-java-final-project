package view.menu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import view.MainFrame;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(MainFrame mainFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(300, 400, 0, 400));

        JButton startBtn = new JButton("게임 시작");
        startBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        startBtn.setAlignmentX(CENTER_ALIGNMENT);
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.changePanel("view.menu.DifficultyPanel");
            }
        });
        add(startBtn);

        add(Box.createRigidArea(new Dimension(0, 40)));

        JButton rankBtn = new JButton("랭킹");
        rankBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        rankBtn.setAlignmentX(CENTER_ALIGNMENT);
        rankBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.changePanel("view.menu.RankPanel");
            }
        });
        add(rankBtn);
    }
}
