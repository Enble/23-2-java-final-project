package domain;

import enums.BombType;
import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Bomb {
    private Point location;
    private String word;
    private BombType bombType;

    public Bomb(Point location, String word, BombType bombType) {
        this.location = location;
        this.word = word;
        this.bombType = bombType;
    }

    public Point getLocation() {
        return location;
    }

    public String getWord() {
        return word;
    }

    public BombType getBombType() {
        return bombType;
    }

}
