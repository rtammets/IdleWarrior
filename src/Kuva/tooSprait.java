package Kuva;

import java.awt.image.BufferedImage;

/**
 * Created by R on 07/01/2017.
 */
public class tooSprait {

    private BufferedImage spraidid;

    public tooSprait(BufferedImage spraidid){ //mis muutujale spraiti tuua...
        this.spraidid = spraidid;
    }

    public BufferedImage crop(int x, int y, int width, int height) {//see fn tagastab spritesheetilt pildi
        return spraidid.getSubimage(x,y,width,height);
    }

}
