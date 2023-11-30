package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartMenuPanel extends JPanel {
    public StartMenuPanel(GameFrame gameFrame) {
        add(new JLabel("view.StartMenuPanel"));

        JButton registerBtn = new JButton("회원가입");
        registerBtn.setBounds(100, 100, 100, 100);
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.changePanel("view.RegisterPanel");
            }
        });
        add(registerBtn);

        JButton loginBtn = new JButton("로그인");
        loginBtn.setBounds(100, 200, 100, 100);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameFrame.changePanel("view.LoginPanel");
            }
        });
        add(loginBtn);
    }
}
