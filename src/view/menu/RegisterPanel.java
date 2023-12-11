package view.menu;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import service.MemberService;
import view.MainFrame;

// 회원가입 화면
public class RegisterPanel extends JPanel {
    // memberService 객체 가져오기
    private final MemberService memberService = MemberService.getInstance();

    public RegisterPanel(MainFrame mainFrame) {
        // BoxLayout으로 세로 정렬
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(300, 450, 300, 450));

        // id 입력 Box
        Box idBox = Box.createHorizontalBox();
        // id 입력 라벨
        JLabel idLabel = new JLabel("   아이디");
        idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        idBox.add(idLabel);
        idBox.add(Box.createRigidArea(new Dimension(20, 0)));
        // id 입력 필드
        JTextField idTf = new JTextField("", 10);
        idTf.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        idBox.add(idTf);
        idBox.setAlignmentX(CENTER_ALIGNMENT);

        // password 입력 Box
        Box passwordBox = Box.createHorizontalBox();
        // password 입력 라벨
        JLabel passwordLabel = new JLabel("비밀번호");
        passwordLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        passwordBox.add(passwordLabel);
        passwordBox.add(Box.createRigidArea(new Dimension(20, 0)));
        // password 입력 필드
        JTextField passwordTf = new JTextField("", 10);
        passwordTf.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        passwordBox.add(passwordTf);
        passwordBox.setAlignmentX(CENTER_ALIGNMENT);

        // 버튼 Box
        Box btnBox = Box.createHorizontalBox();

        // 회원가입 버튼
        JButton registerBtn = new JButton("회원가입");
        registerBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        // 회원가입 버튼 클릭 시 회원가입 수행 후 로그인 화면으로 이동
        registerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String id = idTf.getText();
                String password = passwordTf.getText();
                // 회원가입
                memberService.save(id, password);

                // 입력값 초기화
                idTf.setText("");
                passwordTf.setText("");

                // 로그인 화면으로 이동
                mainFrame.changePanel("view.menu.LoginPanel");
            }
        });
        btnBox.add(registerBtn);

        // padding
        btnBox.add(Box.createRigidArea(new Dimension(20, 0)));

        // 뒤로가기 버튼
        JButton backBtn = new JButton("뒤로가기");
        backBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        // 뒤로가기 버튼 클릭 시 시작메뉴 화면으로 이동
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 입력값 초기화
                idTf.setText("");
                passwordTf.setText("");

                // 시작메뉴 화면으로 이동
                mainFrame.changePanel("view.menu.StartMenuPanel");
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
