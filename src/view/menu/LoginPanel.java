package view.menu;

import domain.Member;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import service.MemberService;
import view.MainFrame;

// 로그인 화면을 구성하는 패널
public class LoginPanel extends JPanel {
    // memberService 객체 가져오기
    private final MemberService memberService = MemberService.getInstance();

    public LoginPanel(MainFrame mainFrame) {
        // Box Layout으로 변경
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // border 설정
        setBorder(new EmptyBorder(300, 450, 300, 450));

        // id 입력 Box
        Box idBox = Box.createHorizontalBox();
        idBox.setAlignmentX(CENTER_ALIGNMENT);

        // id 입력 Label
        JLabel idLabel = new JLabel("   아이디");
        idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        idBox.add(idLabel);
        idBox.add(Box.createRigidArea(new Dimension(20, 0)));

        // id 입력 TextField
        JTextField idTf = new JTextField("", 10);
        idTf.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        idBox.add(idTf);

        // password 입력 Box
        Box passwordBox = Box.createHorizontalBox();
        passwordBox.setAlignmentX(CENTER_ALIGNMENT);

        // password 입력 Label
        JLabel passwordLabel = new JLabel("비밀번호");
        passwordLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        passwordBox.add(passwordLabel);
        passwordBox.add(Box.createRigidArea(new Dimension(20, 0)));

        // password 입력 TextField
        JTextField passwordTf = new JTextField("", 10);
        passwordTf.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        passwordBox.add(passwordTf);

        // 버튼 Box
        Box btnBox = Box.createHorizontalBox();
        btnBox.setAlignmentX(CENTER_ALIGNMENT);

        // 로그인 버튼
        JButton loginBtn = new JButton("로그인");
        loginBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        loginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String id = idTf.getText();
                String password = passwordTf.getText();

                // 로그인 검증
                if (memberService.isValidMember(id, password)) {
                    mainFrame.setPlayer(new Member(id, password));

                    // 입력값 초기화
                    idTf.setText("");
                    passwordTf.setText("");

                    // 게임 화면으로 이동
                    mainFrame.changePanel("view.menu.MainMenuPanel");
                } else {
                    // 로그인 실패
                    JOptionPane.showMessageDialog(null, "아이디 또는 비밀번호가 일치하지 않습니다.");
                }
            }
        });
        btnBox.add(loginBtn);

        btnBox.add(Box.createRigidArea(new Dimension(20, 0)));

        // 뒤로가기 버튼
        JButton backBtn = new JButton("뒤로가기");
        backBtn.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // 입력값 초기화
                idTf.setText("");
                passwordTf.setText("");

                // 시작메뉴 화면으로 이동
                mainFrame.changePanel("view.menu.StartMenuPanel");
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
