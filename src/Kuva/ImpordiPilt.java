package Kuva;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by R on 07/01/2017.
 */
public class ImpordiPilt {
    public static BufferedImage laePilt(String path){
        try {
            return ImageIO.read(ImpordiPilt.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
