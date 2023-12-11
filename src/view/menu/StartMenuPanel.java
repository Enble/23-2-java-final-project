package view.menu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import view.MainFrame;

// 시작메뉴 화면
public class StartMenuPanel extends JPanel {
    public StartMenuPanel(MainFrame mainFrame) {
        // BoxLayout으로 세로 정렬
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(250, 400, 0, 400));

        // title 라벨
        JLabel titleLabel = new JLabel("폭탄을 격추하라!");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(titleLabel);

        // padding
        add(Box.createRigidArea(new Dimension(0, 60)));

        // 회원가입 버튼
        JButton registerBtn = new JButton("회원가입");
        registerBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        registerBtn.setAlignmentX(CENTER_ALIGNMENT);
        // 회원가입 버튼 클릭 시 회원가입 화면으로 전환
        registerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.changePanel("view.menu.RegisterPanel");
            }
        });
        add(registerBtn);

        // padding
        add(Box.createRigidArea(new Dimension(0, 40)));

        // 로그인 버튼
        JButton loginBtn = new JButton("로그인");
        loginBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        loginBtn.setAlignmentX(CENTER_ALIGNMENT);
        // 로그인 버튼 클릭 시 로그인 화면으로 전환
        loginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.changePanel("view.menu.LoginPanel");
            }
        });
        add(loginBtn);
    }
}
