package Objektid;

import Kuva.Assets;

import java.awt.*;

/**
 * Created by R on 07/01/2017.
 */
public class playMenu extends Objekt {


    public playMenu(String name, int width, int height, int drawX, int drawY) {
        super(name, width, height, drawX, drawY);
    }

    @Override
    public void tick() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Assets.playWindow, x, y, width, height, null);
    }
}
