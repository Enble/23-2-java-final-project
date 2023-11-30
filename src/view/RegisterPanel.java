package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import repository.MemberRepository;

public class RegisterPanel extends JPanel {
    private final MemberRepository memberRepository = MemberRepository.getInstance();

    public RegisterPanel(MainFrame mainFrame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(300, 450, 300, 450));

        // id 입력
        Box idBox = Box.createHorizontalBox();
        idBox.add(new JLabel("    아이디"));
        idBox.add(Box.createRigidArea(new Dimension(20, 0)));
        JTextField idTf = new JTextField("", 10);
        idBox.add(idTf);

        // password 입력
        Box passwordBox = Box.createHorizontalBox();
        passwordBox.add(new JLabel("비밀번호"));
        passwordBox.add(Box.createRigidArea(new Dimension(20, 0)));
        JTextField passwordTf = new JTextField("", 10);
        passwordBox.add(passwordTf);

        // 버튼
        Box btnBox = Box.createHorizontalBox();
        JButton registerBtn = new JButton("회원가입");
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 회원가입
                String id = idTf.getText();
                String password = passwordTf.getText();
                memberRepository.save(id, password);

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

        add(idBox);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(passwordBox);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(btnBox);
    }

}
