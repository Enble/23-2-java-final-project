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

public class TypingLifePanel extends JPanel {
    private final JLabel lifeLabel;
    private final JTextField typingField;
    private int life = 0;

    public TypingLifePanel(GamePanel gamePanel) {
        JLabel lifeInfoLabel = new JLabel("체력 : ");
        lifeInfoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        add(lifeInfoLabel);

        lifeLabel = new JLabel();
        lifeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        add(lifeLabel);

        // padding
        add(Box.createRigidArea(new Dimension(20, 0)));

        typingField = new JTextField(30);
        typingField.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        typingField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = typingField.getText();
                typingField.setText("");

                gamePanel.getMonitorPanel().removeBomb(input);
                gamePanel.getItemPanel().increaseCoin(10);
            }
        });
        add(typingField);

        // 아이템 사용을 위한 키 리스너
        typingField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_F1:
                        gamePanel.getItemPanel().purchaseAndUseItem(ItemType.MEDKIT);
                        break;
                    case KeyEvent.VK_F2:
                        gamePanel.getItemPanel().purchaseAndUseItem(ItemType.HEADWIND);
                        break;
                    case KeyEvent.VK_F3:
                        gamePanel.getItemPanel().purchaseAndUseItem(ItemType.EMP);
                        break;
                }
            }
        });

        setFocusable(true);
        typingField.requestFocus();
    }

    public void requestTypingFieldFocus() {
        typingField.requestFocus();
    }

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

    public boolean isDead() {
        return life <= 0;
    }

    public void decreaseLife(int damage) {
        life -= damage;
        if (life < 0) {
            life = 0;
        }
        lifeLabel.setText(String.valueOf(life));
    }

    public void increaseLife(int heal) {
        life += heal;
        lifeLabel.setText(String.valueOf(life));
    }

}
