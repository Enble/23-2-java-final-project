package view.menu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import view.MainFrame;

// 메인 메뉴 패널
public class MainMenuPanel extends JPanel {
    public MainMenuPanel(MainFrame mainFrame) {
        // BoxLayout으로 설정
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(300, 400, 0, 400));

        // 게임 시작 버튼
        JButton startBtn = new JButton("게임 시작");
        startBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        startBtn.setAlignmentX(CENTER_ALIGNMENT);
        // 마우스 클릭 시 난이도 선택 패널로 이동
        startBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.changePanel("view.menu.DifficultyPanel");
            }
        });
        add(startBtn);

        // padding
        add(Box.createRigidArea(new Dimension(0, 40)));

        // 랭킹 버튼
        JButton rankBtn = new JButton("랭킹");
        rankBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        rankBtn.setAlignmentX(CENTER_ALIGNMENT);
        // 마우스 클릭 시 랭킹 패널로 이동
        rankBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.changePanel("view.menu.RankPanel");
            }
        });
        add(rankBtn);
    }
}
