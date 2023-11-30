package controller;

import domain.Bomb;
import java.util.LinkedList;
import java.util.List;
import service.WordService;
import view.game.ItemPanel;
import view.game.MonitorPanel;
import view.game.ScorePanel;
import view.game.TypingPanel;

public class GameController {
    private final WordService wordService = new WordService();

    private final MonitorPanel monitorPanel;
    private final TypingPanel typingPanel;
    private final ScorePanel scorePanel;
    private final ItemPanel itemPanel;

    private final List<Bomb> bombs = new LinkedList<>();

    public GameController(MonitorPanel monitorPanel, TypingPanel typingPanel, ScorePanel scorePanel,
                          ItemPanel itemPanel) {
        this.monitorPanel = monitorPanel;
        this.typingPanel = typingPanel;
        this.scorePanel = scorePanel;
        this.itemPanel = itemPanel;
    }

    public void run() {
        while (true) {
            try {

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeBomb(Bomb bomb) {
        bombs.remove(bomb);
    }

    public void deleteAllBombs() {
        bombs.clear();
    }
}
