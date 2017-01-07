package Objektid;

import Kuva.Assets;

import java.awt.*;

/**
 * Created by R on 07/01/2017.
 */
public class Taust extends Objekt {


    public Taust(String name, int width, int height, int drawX, int drawY) {
        super(name, width, height, drawX, drawY);
    }

    @Override
    public void tick() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Assets.windyPlains, x, y, width, height, null);
    }
}
