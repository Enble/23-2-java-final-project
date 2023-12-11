package view.game;

import enums.ItemType;
import java.awt.Font;
import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ItemPanel extends JPanel {
    // 구급상자 아이템 이미지
    private static final String MEDKIT_ICON_PATH = "src/images/medkit.png";
    private static final String MEDKIT_LABEL_TEXT = "구급상자";

    // 맞바람 아이템 이미지
    private static final String HEADWIND_ICON_PATH = "src/images/headwind.png";
    private static final String HEADWIND_LABEL_TEXT = "맞바람";

    // EMP 아이템 이미지
    private static final String EMP_ICON_PATH = "src/images/emp.png";
    private static final String EMP_LABEL_TEXT = "EMP";

    // 코인 이미지
    private static final String COIN_ICON_PATH = "src/images/coin.png";

    // 게임 패널
    private final GamePanel gamePanel;

    // 코인 라벨
    private final JLabel coinLabel = new JLabel("0");
    // 보유 코인 수
    private int coin = 0;

    public ItemPanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        // 박스 레이아웃
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 코인 박스 :  코인 정보 라벨 + 코인 이미지
        Box coinBox = Box.createHorizontalBox();
        JLabel coinInfoLabel = new JLabel("보유 코인 : ");
        coinInfoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        coinBox.add(coinInfoLabel);

        JLabel coinImageLabel = new JLabel(createImageIcon(COIN_ICON_PATH, 30));
        coinBox.add(coinImageLabel);

        // padding
        coinBox.add(Box.createHorizontalStrut(5));

        // 코인 라벨
        coinLabel.setFont(new Font("맑은 고딕", Font.BOLD, 30));
        coinBox.add(coinLabel);
        add(coinBox);

        // padding
        add(Box.createVerticalStrut(10));

        add(createItemBox(MEDKIT_ICON_PATH, MEDKIT_LABEL_TEXT, ItemType.MEDKIT));
        add(createItemBox(HEADWIND_ICON_PATH, HEADWIND_LABEL_TEXT, ItemType.HEADWIND));
        add(createItemBox(EMP_ICON_PATH, EMP_LABEL_TEXT, ItemType.EMP));
    }

    // 아이템 박스 생성
    private Box createItemBox(String iconPath, String labelText, ItemType itemType) {
        Box itemBox = Box.createVerticalBox();

        Box box = Box.createHorizontalBox();
        // 아이템 이미지
        JLabel itemImageLabel = new JLabel(createImageIcon(iconPath, 50));
        box.add(itemImageLabel);

        // padding
        box.add(Box.createHorizontalStrut(20));

        // 아이템 이름
        JLabel itemNameLabel = new JLabel(labelText);
        itemNameLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        box.add(itemNameLabel);

        // padding
        box.add(Box.createHorizontalStrut(20));

        // 코인 이미지
        JLabel coinImageLabel = new JLabel(createImageIcon(COIN_ICON_PATH, 20));
        box.add(coinImageLabel);

        // padding
        box.add(Box.createHorizontalStrut(5));

        // 아이템 비용
        JLabel itemPriceLabel = new JLabel(String.valueOf(itemType.getPrice()));
        itemPriceLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        box.add(itemPriceLabel);

        itemBox.add(box);

        // padding
        itemBox.add(Box.createVerticalStrut(10));

        // 아이템 설명
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText(itemType.getDescription());
        itemBox.add(textArea);

        // padding
        add(Box.createVerticalStrut(10));

        return itemBox;
    }

    // 이미지 아이콘 생성
    private ImageIcon createImageIcon(String path, int iconDimension) {
        ImageIcon imageIcon = new ImageIcon(path);
        Image image = imageIcon.getImage().getScaledInstance(iconDimension, iconDimension, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    // 아이템 구매 및 사용
    public void purchaseAndUseItem(ItemType itemType) {
        // 코인 부족시 구매 불가
        if (itemType.getPrice() > coin) {
            return;
        }

        // 코인 감소
        decreaseCoin(itemType.getPrice());

        // 아이템 효과
        switch (itemType) {
            // 구급상자
            case MEDKIT:
                // 체력 1 회복
                gamePanel.getTypingPanel().increaseLife(1);
                break;
            // 맞바람
            case HEADWIND:
                // 5초동안 폭탄이 정지함
                gamePanel.getMonitorPanel().pauseBomb();
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        gamePanel.getMonitorPanel().resumeBomb();
                    }
                }, 5000);
                break;
            // EMP
            case EMP:
                // 모든 폭탄 제거
                gamePanel.getMonitorPanel().removeAllBomb();
                break;
        }
    }

    // 코인 증가
    public void increaseCoin(int coin) {
        this.coin += coin;
        coinLabel.setText(String.valueOf(this.coin));
    }

    // 코인 감소
    public void decreaseCoin(int coin) {
        this.coin -= coin;
        coinLabel.setText(String.valueOf(this.coin));
    }
}
