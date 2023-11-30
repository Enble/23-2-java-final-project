package view.menu;

import enums.DifficultyType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import view.MainFrame;

public class DifficultyPanel extends JPanel {
    public DifficultyPanel(MainFrame mainFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(150, 400, 0, 400));

        JLabel titleLabel = new JLabel("난이도 선택");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(titleLabel);

        // padding
        add(Box.createRigidArea(new Dimension(0, 60)));

        JButton easyBtn = new JButton("쉬움");
        easyBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        easyBtn.setAlignmentX(CENTER_ALIGNMENT);
        easyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setDifficultyType(DifficultyType.EASY);
                mainFrame.changePanel("view.game.GamePanel");
            }
        });
        add(easyBtn);

        // padding
        add(Box.createRigidArea(new Dimension(0, 40)));

        JButton normalBtn = new JButton("보통");
        normalBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        normalBtn.setAlignmentX(CENTER_ALIGNMENT);
        normalBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setDifficultyType(DifficultyType.NORMAL);
                mainFrame.changePanel("view.game.GamePanel");
            }
        });
        add(normalBtn);

        // padding
        add(Box.createRigidArea(new Dimension(0, 40)));

        JButton hardBtn = new JButton("어려움");
        hardBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        hardBtn.setAlignmentX(CENTER_ALIGNMENT);
        hardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setDifficultyType(DifficultyType.HARD);
                mainFrame.changePanel("view.game.GamePanel");
            }
        });
        add(hardBtn);

        // padding
        add(Box.createRigidArea(new Dimension(0, 40)));

        JButton fireOceanBtn = new JButton("불바다");
        fireOceanBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        fireOceanBtn.setAlignmentX(CENTER_ALIGNMENT);
        fireOceanBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.setDifficultyType(DifficultyType.FIRE_OCEAN);
                mainFrame.changePanel("view.game.GamePanel");
            }
        });
        add(fireOceanBtn);
    }
}
