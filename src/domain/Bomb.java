package domain;

import enums.BombType;
import java.awt.Point;

public class Bomb {
    private Point location;
    private String word;
    private BombType type;

    public Bomb(Point location, String word, BombType type) {
        this.location = location;
        this.word = word;
        this.type = type;
    }

    public Point getLocation() {
        return location;
    }

    public String getWord() {
        return word;
    }

    public BombType getType() {
        return type;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setType(BombType type) {
        this.type = type;
    }
}
