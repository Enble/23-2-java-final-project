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
    // 기준 속도 (초기값)
    public DifficultyPanel(MainFrame mainFrame) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(150, 400, 0, 400));

        JLabel titleLabel = new JLabel("난이도 선택");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(titleLabel);

        // padding
        add(Box.createRigidArea(new Dimension(0, 60)));

        addButton("쉬움", DifficultyType.EASY, mainFrame);
        addButton("보통", DifficultyType.NORMAL, mainFrame);
        addButton("어려움", DifficultyType.HARD, mainFrame);
        addButton("불바다", DifficultyType.FIRE_OCEAN, mainFrame);
    }

    private void addButton(String name, DifficultyType difficultyType, MainFrame mainFrame) {
        JButton btn = new JButton(name);
        btn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        btn.setAlignmentX(CENTER_ALIGNMENT);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 난이도 설정
                mainFrame.setDifficultyType(difficultyType);

                // MonitorPanel 설정
                // 체력 설정
                mainFrame.getGamePanel().getTypingPanel().setLifeBasedOnDifficulty(mainFrame.getDifficultyType());
                // 쓰레드의 sleep 시간 설정
                mainFrame.getGamePanel().getMonitorPanel()
                        .setSleepTime(difficultyType.getSpeedCoefficient());
                // startGame() 호출
                mainFrame.getGamePanel().getMonitorPanel().startGame();

                // TypingLifePanel 설정
                // 체력 설정
                mainFrame.getGamePanel().getTypingPanel().setLifeBasedOnDifficulty(mainFrame.getDifficultyType());

                // TimePanel 설정
                // 아이디 설정
                mainFrame.getGamePanel().getTimePanel().setIdLabel(mainFrame.getPlayer().getId());

                // 패널 전환
                mainFrame.changePanel("view.game.GamePanel");
            }
        });
        add(btn);

        // padding
        add(Box.createRigidArea(new Dimension(0, 40)));
    }

}
