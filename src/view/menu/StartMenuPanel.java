package view.menu;

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

public class StartMenuPanel extends JPanel {
    public StartMenuPanel(MainFrame mainFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(250, 400, 0, 400));

        JLabel titleLabel = new JLabel("폭탄을 격추하라!");
        titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(titleLabel);

        // padding
        add(Box.createRigidArea(new Dimension(0, 60)));

        JButton registerBtn = new JButton("회원가입");
        registerBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        registerBtn.setAlignmentX(CENTER_ALIGNMENT);
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.changePanel("view.menu.RegisterPanel");
            }
        });
        add(registerBtn);

        // padding
        add(Box.createRigidArea(new Dimension(0, 40)));

        JButton loginBtn = new JButton("로그인");
        loginBtn.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        loginBtn.setAlignmentX(CENTER_ALIGNMENT);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.changePanel("view.menu.LoginPanel");
            }
        });
        add(loginBtn);
    }
}
