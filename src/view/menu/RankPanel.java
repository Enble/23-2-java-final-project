package view.menu;

import java.awt.CardLayout;
import javax.swing.JPanel;
import view.MainFrame;
import view.rank.EasyRankPanel;
import view.rank.FireOceanRankPanel;
import view.rank.HardRankPanel;
import view.rank.NormalRankPanel;
import view.rank.RankDifficultyPanel;

// 랭킹 패널
public class RankPanel extends JPanel {
    // 카드 레이아웃
    private final CardLayout cardLayout = new CardLayout();

    // 난이도 선택 패널
    private final RankDifficultyPanel rankDifficultyPanel;

    // 쉬움 랭킹 패널
    private final EasyRankPanel easyRankPanel;
    // 보통 랭킹 패널
    private final NormalRankPanel normalRankPanel;
    // 어려움 랭킹 패널
    private final HardRankPanel hardRankPanel;
    // 불바다 랭킹 패널
    private final FireOceanRankPanel fireOceanRankPanel;

    public RankPanel(MainFrame mainFrame) {
        // 카드 레이아웃으로 설정
        setLayout(cardLayout);

        // 난이도 선택
        rankDifficultyPanel = new RankDifficultyPanel(this);
        add("view.rank.RankDifficultyPanel", rankDifficultyPanel);

        // 쉬움 랭킹
        easyRankPanel = new EasyRankPanel(this);
        add("view.rank.EasyRankPanel", easyRankPanel);

        // 보통 랭킹
        normalRankPanel = new NormalRankPanel(this);
        add("view.rank.NormalRankPanel", normalRankPanel);

        // 어려움 랭킹
        hardRankPanel = new HardRankPanel(this);
        add("view.rank.HardRankPanel", hardRankPanel);

        // 불바다 랭킹
        fireOceanRankPanel = new FireOceanRankPanel(this);
        add("view.rank.FireOceanRankPanel", fireOceanRankPanel);
    }

    // 랭킹 패널의 cardLayout을 이용하여 패널을 변경
    public void changePanel(String panelName) {
        cardLayout.show(this, panelName);
    }

    // 랭킹 패널의 랭킹을 갱신
    public void buildRankPanel() {
        easyRankPanel.buildRankPanel();
        normalRankPanel.buildRankPanel();
        hardRankPanel.buildRankPanel();
        fireOceanRankPanel.buildRankPanel();
    }

}
