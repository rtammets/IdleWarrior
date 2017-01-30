package Objektid.UIElements;

import Kuva.Assets;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by R on 27/01/2017.
 */
public class uiNupp implements MouseListener {

    int x,y,w,h, pildiNr;

      public uiNupp(int pildiNr, int x, int y, int w, int h) {
        super ();
          this.x = x;
          this.y = y;
          this.w = w;
          this.h = h;
      this.pildiNr = pildiNr;
    }

    public void tick(){

    }

    public void draw (Graphics g){
        g.drawImage(Assets.Nupp[pildiNr], x, y ,null);

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
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
