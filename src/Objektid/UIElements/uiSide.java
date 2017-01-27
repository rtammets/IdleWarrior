package Objektid.UIElements;

import Objektid.Objekt;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import static Kuva.Assets.uiSideBar;

//import static Kuva.Assets.userInterface;

/**
 * Created by R on 14/01/2017.
 */
public class uiSide extends Objekt {
    JPanel jp;
    JButton jb;
    JLabel jl;
    //ehitan siia sisse paneeli, kus kuvatakse statse jne.


    public uiSide(int x, int y, int width, int height) {
        super(x, y, width, height);
    }


    public void uiStats(){
        jp = new JPanel();
        jl = new JLabel();
        jp.add(jl);

    }

    @Override
    public void tick() {

    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(uiSideBar, x, y, width, height, null);
    }
}
