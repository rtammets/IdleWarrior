package Kuva;

import java.awt.image.BufferedImage;

/**
 * Created by R on 07/01/2017.
 */
public class Assets {

    public static BufferedImage player, zombie, secretCamp, windyPlains, magic, playWindow;
    public static void  init(){
        tooSprait spraidid = new tooSprait(ImpordiPilt.laePilt("/images/sprites.png"));
        player = spraidid.crop(0,1200,97,174);
        zombie = spraidid.crop(200,1200,97,147);
        secretCamp = spraidid.crop(600,4121,600,400);
        windyPlains = spraidid.crop(0,0,600,400);
        playWindow = spraidid.crop(0,1400,600,400);
        magic = spraidid.crop(51,406,47,18);
    }
}
