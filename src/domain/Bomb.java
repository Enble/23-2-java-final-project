package domain;

import enums.BombType;
import java.awt.Point;

// 폭탄 객체
public class Bomb {
    // 폭탄의 위치
    private Point location;
    // 폭탄의 단어
    private String word;
    // 폭탄의 종류
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
