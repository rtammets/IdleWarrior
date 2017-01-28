package Objektid.UIElements;

import Kuva.Assets;
import java.awt.*;

/**
 * Created by R on 27/01/2017.
 */
public class uiNupp{

    int pildiNr, nx, ny, nw, nh;

      public  uiNupp(int pildiNr) {
        super ();
        this.nx = Assets.Nupp[pildiNr].getMinX();
        this.ny = Assets.Nupp[pildiNr].getMinY();
        this.nw = Assets.Nupp[pildiNr].getWidth();
        this.nh = Assets.Nupp[pildiNr].getHeight();
        this.pildiNr = pildiNr;
    }

    public void tick(){

    }

    public void draw (Graphics g){
     //   g.drawImage(Assets.Nupp[pildiNr], (610+(35*pildiNr)),300,null);

    }
}
