package view.game;

import enums.DifficultyType;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TypingPanel extends JPanel {
    private final JLabel lifeLabel;
    private final JTextField typingField;
    private int life = 0;

    public TypingPanel(GamePanel gamePanel) {
        JLabel lifeInfoLabel = new JLabel("체력 : ");
        lifeInfoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        add(lifeInfoLabel);

        lifeLabel = new JLabel();
        lifeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        add(lifeLabel);

        add(Box.createRigidArea(new Dimension(10, 0)));

        typingField = new JTextField(30);
        typingField.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        typingField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = typingField.getText();
                typingField.setText("");

                gamePanel.getMonitorPanel().removeBomb(input);
            }
        });
        add(typingField);
    }

    public void setLifeBasedOnDifficulty(DifficultyType difficultyType) {
        System.out.println(difficultyType);
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

    public void decreaseLife(int damage) {
        life -= damage;
        lifeLabel.setText(String.valueOf(life));
    }

}
