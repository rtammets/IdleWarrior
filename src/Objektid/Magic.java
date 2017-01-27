package Objektid;

import Kuva.Assets;
import Main.Main;

import java.awt.*;

/**
 * maagia
 */
public class Magic extends Tegelane {
    int kiirus = 3;
//    public int posX = x + MangKaib.mangija.x+50;
//    public int posY = y + MangKaib.mangija.y+50;
    private Main game;

    public Magic(Main game, int x, int y, int width, int height, int health, int damage) {
        super( x, y, width, height, health, damage);
        this.game = game;
        this.x=x;
        this.y=y;
        this.width = width;
        this.height = height;
    }

    public void tick() {
        this.x += kiirus;//Move(this.kiirus);
    }

    public void draw(Graphics g) {
        g.drawImage(Assets.magicAA, x, y, width, height, null);
    }
}
