package domain;

import java.util.ArrayList;
import java.util.List;
import service.DictionaryService;
import view.ingame.MonitorPanel;
import view.ingame.ItemPanel;
import view.ingame.ScorePanel;
import view.ingame.TypingPanel;

public class GameController {
    private final DictionaryService dictionaryService = new DictionaryService();

    private final MonitorPanel monitorPanel;
    private final TypingPanel typingPanel;
    private final ScorePanel scorePanel;
    private final ItemPanel itemPanel;

    private final List<Bomb> bombs = new ArrayList<>();

    public GameController(MonitorPanel monitorPanel, TypingPanel typingPanel, ScorePanel scorePanel, ItemPanel itemPanel) {
        this.monitorPanel = monitorPanel;
        this.typingPanel = typingPanel;
        this.scorePanel = scorePanel;
        this.itemPanel = itemPanel;
    }

    public void removeBomb(Bomb bomb) {
        bombs.remove(bomb);
    }

    public void deleteAllBombs() {
        bombs.clear();
    }
}
