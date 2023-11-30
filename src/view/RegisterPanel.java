package view;

import java.awt.Dimension;
import java.awt.Font;
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

        // id 입력 Box
        Box idBox = Box.createHorizontalBox();
        JLabel idLabel = new JLabel("   아이디");
        idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        idBox.add(idLabel);
        idBox.add(Box.createRigidArea(new Dimension(20, 0)));
        JTextField idTf = new JTextField("", 10);
        idTf.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        idBox.add(idTf);
        idBox.setAlignmentX(CENTER_ALIGNMENT);

        // password 입력 Box
        Box passwordBox = Box.createHorizontalBox();
        JLabel passwordLabel = new JLabel("비밀번호");
        passwordLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        passwordBox.add(passwordLabel);
        passwordBox.add(Box.createRigidArea(new Dimension(20, 0)));
        JTextField passwordTf = new JTextField("", 10);
        passwordTf.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        passwordBox.add(passwordTf);
        passwordBox.setAlignmentX(CENTER_ALIGNMENT);

        // 버튼 Box
        Box btnBox = Box.createHorizontalBox();

        // 회원가입 버튼
        JButton registerBtn = new JButton("회원가입");
        registerBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 회원가입
                String id = idTf.getText();
                String password = passwordTf.getText();
                memberRepository.save(id, password);

                // 입력값 초기화
                idTf.setText("");
                passwordTf.setText("");

                // 로그인 화면으로 이동
                mainFrame.changePanel("view.LoginPanel");
            }
        });
        btnBox.add(registerBtn);

        btnBox.add(Box.createRigidArea(new Dimension(20, 0)));

        // 뒤로가기 버튼
        JButton backBtn = new JButton("뒤로가기");
        backBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 입력값 초기화
                idTf.setText("");
                passwordTf.setText("");

                // 시작메뉴 화면으로 이동
                mainFrame.changePanel("view.StartMenuPanel");
            }
        });
        btnBox.add(backBtn);
        btnBox.setAlignmentX(CENTER_ALIGNMENT);

        add(idBox);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(passwordBox);
        add(Box.createRigidArea(new Dimension(0, 40)));
        add(btnBox);
    }

}
