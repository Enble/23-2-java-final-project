package view.rank;

import domain.RankMember;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import service.RankService;
import service.TimeTransfer;
import view.menu.RankPanel;

// 쉬움 랭킹 패널
public class EasyRankPanel extends JPanel {
    private final RankPanel rankPanel;

    public EasyRankPanel(RankPanel rankPanel) {
        this.rankPanel = rankPanel;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(20, 300, 20, 300));

        JLabel titleLabel = new JLabel("쉬움 랭킹");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(titleLabel);
    }

    // 랭킹 패널 빌드
    public void buildRankPanel() {
        this.removeAll();

        // 타이틀 라벨
        JLabel titleLabel = new JLabel("쉬움 랭킹");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(titleLabel);

        // padding
        add(Box.createRigidArea(new Dimension(0, 40)));

        // 랭크 리스트 패널
        JPanel rankScrollPane = new JPanel();
        rankScrollPane.setLayout(new BoxLayout(rankScrollPane, BoxLayout.Y_AXIS));

        // 랭크 리스트 불러오기
        List<RankMember> rankBoard = RankService.getInstance().getEasyRankBoard();

        // 랭크 리스트 출력
        for (int i = 0; i < rankBoard.size(); i++) {
            JLabel rankLabel = new JLabel(
                    (i + 1) + "위 : " + rankBoard.get(i).getId() + " (" +
                            TimeTransfer.durationToStringFormat(rankBoard.get(i).getScore()) + ")");
            rankLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
            rankLabel.setAlignmentX(CENTER_ALIGNMENT);
            rankScrollPane.add(rankLabel);
        }

        // 스크롤 패널 붙이기
        JScrollPane scrollPane = new JScrollPane(rankScrollPane, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);

        // padding
        add(Box.createRigidArea(new Dimension(0, 40)));

        // 뒤로가기 버튼
        JButton backButton = new JButton("뒤로가기");
        backButton.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        // 뒤로가기 버튼 액션 리스너
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 난이도 선택 패널로 이동
                rankPanel.changePanel("view.rank.RankDifficultyPanel");
            }
        });
        add(backButton);
    }

}
