package Objektid.UIElements;

import Kuva.Assets;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by R on 27/01/2017.
 */
public class uiNupp implements MouseListener {

    public int x, y, w, h, pildiNr;

    public uiNupp(int pildiNr, int x, int y, int w, int h) {
        super();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.pildiNr = pildiNr;
    }

    public void tick() {
        System.out.println("test");
    }

    public void draw(Graphics g) {
        System.out.println("test");
        g.drawImage(Assets.Nupp[pildiNr], x, y, null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseclicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.printf("mousepressed");
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

}

/*
*
        Point clicked = e.getPoint();
//loo ui button, äkki tsükliga kõiki nuppe läbi käima?
        //declare rectangle array
        Rectangle[] bounds = new Rectangle[Nupp.length];
        //populate rectangle array with values from Nupp array
        for (int i = 0; i<Nupp.length; i++){
            bounds[i] = new Rectangle(Nupp[i].getMinX(),Nupp[i].getMinY(), Assets.Nupp[i].getWidth(), Assets.Nupp[i].getHeight());
            newMessage(String.valueOf(bounds[i]));
        }

        //Rectangle bounds = new Rectangle (610,300, Assets.Nupp[0].getWidth(), Assets.Nupp[0].getHeight());
        for (int i = 0; i< bounds.length; i++) {
            if (bounds[i].contains(clicked)) {
        //if (bounds.contains(clicked)) {
//            newMessage(""+Assets.Nupp[0].getTileGridXOffset()+Assets.Nupp[0].getMinTileX());
                newMessage("HIIRE ASJAD ON NÕMEDAD (nr for debug: )"); // target image was clicked
            }
        }
* */