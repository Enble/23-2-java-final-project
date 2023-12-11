package view.rank;

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
import view.menu.RankPanel;

public class RankDifficultyPanel extends JPanel {
    private final RankPanel rankPanel;
    public RankDifficultyPanel(RankPanel rankPanel) {
        this.rankPanel = rankPanel;

        // BoxLayout으로 세로 정렬
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(150, 400, 0, 400));

        // title label
        JLabel titleLabel = new JLabel("난이도별 랭크 보드");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(titleLabel);

        // padding
        add(Box.createRigidArea(new Dimension(0, 60)));

        // 난이도 버튼
        addButton("쉬움", DifficultyType.EASY, rankPanel);
        addButton("보통", DifficultyType.NORMAL, rankPanel);
        addButton("어려움", DifficultyType.HARD, rankPanel);
        addButton("불바다", DifficultyType.FIRE_OCEAN, rankPanel);
    }

    // 난이도 버튼 추가
    private void addButton(String name, DifficultyType difficultyType, RankPanel rankPanel) {
        JButton btn = new JButton(name);
        btn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        btn.setAlignmentX(CENTER_ALIGNMENT);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 랭크 패널 새로고침
                rankPanel.buildRankPanel();

                // 난이도에 따라 랭크 패널 변경
                switch (difficultyType) {
                    case EASY:
                        rankPanel.changePanel("view.rank.EasyRankPanel");
                        break;
                    case NORMAL:
                        rankPanel.changePanel("view.rank.NormalRankPanel");
                        break;
                    case HARD:
                        rankPanel.changePanel("view.rank.HardRankPanel");
                        break;
                    case FIRE_OCEAN:
                        rankPanel.changePanel("view.rank.FireOceanRankPanel");
                        break;
                }
            }
        });
        add(btn);

        // padding
        add(Box.createRigidArea(new Dimension(0, 40)));
    }
}
