package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenuPanel extends JPanel {
    public MainMenuPanel(MainFrame mainFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(300, 450, 300, 450));

        // 버튼
        Box btnBox = Box.createHorizontalBox();
        JButton registerBtn = new JButton("회원가입");
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 로그인 화면으로 이동
                mainFrame.changePanel("view.LoginPanel");
            }
        });
        btnBox.add(registerBtn);

        btnBox.add(Box.createRigidArea(new Dimension(20, 0)));

        JButton backBtn = new JButton("뒤로가기");
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 시작메뉴 화면으로 이동
                mainFrame.changePanel("view.StartMenuPanel");
            }
        });
        btnBox.add(backBtn);

        add(btnBox);
    }
}
