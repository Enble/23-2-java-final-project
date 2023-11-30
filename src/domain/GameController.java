package domain;

import java.util.ArrayList;
import java.util.List;
import view.ingame.GameGround;
import view.ingame.ItemPanel;
import view.ingame.ScorePanel;
import view.ingame.TypingBar;

public class GameController {
    private final List<Bomb> bombs = new ArrayList<>();

    private final GameGround gameGround;
    private final TypingBar typingBar;
    private final ScorePanel scorePanel;
    private final ItemPanel itemPanel;

    public GameController(GameGround gameGround, TypingBar typingBar, ScorePanel scorePanel, ItemPanel itemPanel) {
        this.gameGround = gameGround;
        this.typingBar = typingBar;
        this.scorePanel = scorePanel;
        this.itemPanel = itemPanel;
    }

    public void addBomb(Bomb bomb) {
        bombs.add(bomb);
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public void deleteAllBombs() {
        bombs.clear();
    }
}
