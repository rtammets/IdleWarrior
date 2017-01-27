package Objektid.UIElements;

import Objektid.Objekt;

import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.*;
import java.util.LinkedList;

import static Kuva.Assets.uiKonsool;

//import static Kuva.Assets.userInterface;

/**
 * Created by R on 14/01/2017.
 */
public class uiBox extends Objekt {

    public uiBox(int x, int y, int width, int height) {
        super(x, y, width, height);
    }


    @Override
    public void tick() {
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(uiKonsool, x, y, width, height, null);
    }
}