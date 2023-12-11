package view.game;

import enums.DifficultyType;
import enums.ItemType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// 체력과 타이핑 바를 표시하는 패널
public class TypingLifePanel extends JPanel {
    // 타이핑을 입력하는 텍스트 필드
    private final JTextField typingField;
    // 체력을 표시하는 라벨
    private final JLabel lifeLabel;
    // 현재 체력
    private int life = 0;

    public TypingLifePanel(GamePanel gamePanel) {
        // 체력 정보
        JLabel lifeInfoLabel = new JLabel("체력 : ");
        lifeInfoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        add(lifeInfoLabel);

        // 체력 라벨
        lifeLabel = new JLabel();
        lifeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        add(lifeLabel);

        // padding
        add(Box.createRigidArea(new Dimension(20, 0)));

        // 타이핑 필드
        typingField = new JTextField(30);
        typingField.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        // 리스너 등록
        typingField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 타이핑 필드에 입력된 문자열을 가져온다.
                String input = typingField.getText();
                // 타이핑 필드를 비운다.
                typingField.setText("");

                // 입력된 문자열을 모니터 패널에서 처리한다.
                if (gamePanel.getMonitorPanel().removeBomb(input)) {
                    // 코인을 10 증가시킨다.
                    gamePanel.getItemPanel().increaseCoin(10);
                }
            }
        });
        add(typingField);

        // 아이템 사용을 위한 키 리스너
        typingField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    // F1 키를 누르면 구급상자를 구매하고 사용한다.
                    case KeyEvent.VK_F1:
                        gamePanel.getItemPanel().purchaseAndUseItem(ItemType.MEDKIT);
                        break;
                    // F2 키를 누르면 역풍을 구매하고 사용한다.
                    case KeyEvent.VK_F2:
                        gamePanel.getItemPanel().purchaseAndUseItem(ItemType.HEADWIND);
                        break;
                    // F3 키를 누르면 EMP를 구매하고 사용한다.
                    case KeyEvent.VK_F3:
                        gamePanel.getItemPanel().purchaseAndUseItem(ItemType.EMP);
                        break;
                }
            }
        });

        // 패널에 포커스를 준다.
        setFocusable(true);
        typingField.requestFocus();
    }

    // 타이핑 필드에 포커스 요청
    public void requestTypingFieldFocus() {
        typingField.requestFocus();
    }

    // 난이도에 따라 체력을 설정한다.
    public void setLifeBasedOnDifficulty(DifficultyType difficultyType) {
        if (difficultyType == DifficultyType.EASY) {
            life = 7;
        } else if (difficultyType == DifficultyType.NORMAL) {
            life = 5;
        } else if (difficultyType == DifficultyType.HARD) {
            life = 3;
        } else {
            life = 1;
        }
        lifeLabel.setText(String.valueOf(life));
    }

    // 체력이 0이면 true를 반환한다.
    public boolean isDead() {
        return life <= 0;
    }

    // 체력을 감소시킨다.
    public void decreaseLife(int damage) {
        life -= damage;
        if (life < 0) {
            life = 0;
        }
        lifeLabel.setText(String.valueOf(life));
    }

    // 체력을 증가시킨다.
    public void increaseLife(int heal) {
        life += heal;
        lifeLabel.setText(String.valueOf(life));
    }

}
