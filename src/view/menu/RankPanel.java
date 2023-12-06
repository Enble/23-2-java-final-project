package view.menu;

import domain.Member;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import service.RankService;
import util.TimeTransfer;
import view.MainFrame;

public class RankPanel extends JPanel {
    public RankPanel(MainFrame mainFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 300, 20, 300));

        JLabel titleLabel = new JLabel("랭킹");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(titleLabel);
    }

    public void buildRankPanel() {
        this.removeAll();

        JLabel titleLabel = new JLabel("랭킹");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(titleLabel);

        // padding
        add(Box.createRigidArea(new Dimension(0, 40)));

        JPanel rankPanel = new JPanel();
        rankPanel.setLayout(new BoxLayout(rankPanel, BoxLayout.Y_AXIS));

        // 랭크 리스트 불러오기
        List<Member> rankBoard = RankService.getInstance().getRankBoard();

        // 랭크 리스트 출력
        for (int i = 0; i < rankBoard.size(); i++) {
            JLabel rankLabel = new JLabel(
                    (i + 1) + "위 : " + rankBoard.get(i).getId() + " (" +
                            TimeTransfer.durationToStringFormat(rankBoard.get(i).getMaxScore()) + ")");
            rankLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
            rankLabel.setAlignmentX(CENTER_ALIGNMENT);
            rankPanel.add(rankLabel);
        }

        JScrollPane scrollPane = new JScrollPane(rankPanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane);
    }

    public static class SettingPanel extends JPanel {
        public SettingPanel() {
            add(new JLabel("설정"));
        }
    }
}
