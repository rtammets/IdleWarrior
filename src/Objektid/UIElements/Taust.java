package Objektid.UIElements;


import Objektid.Objekt;

import java.awt.*;
import java.awt.image.BufferedImage;
import static Kuva.Assets.*;
import static Main.Seisud.MangKaib.taust;
import static Objektid.mobSpawner.spawnMax;
import static Objektid.mobSpawner.spawnMin;

/**
 * Created by R on 07/01/2017.
 */
public class Taust extends Objekt {
    public static int taustanr = 1;
    public static String areaNimi = null;
    int bcdown = 0;
    public Taust( int width, int height, int drawX, int drawY) {
        super(width, height, drawX, drawY);
    }



    public static void checkLevel() {
        if (taustanr == 0){
              spawnMax = 0;
              spawnMin = 0;

            taust.areaNimi = "Kuidas siia said lol";
        }
        //määran mis "taustanumbri" ehk kohaga on seotud milline mobide vahemik ja kohanimi
        //tulevikus võiks teha nii, et igal pool võib mitme eri area mobe korraga spawnida
        //selleks teeksin Array mobide ID ehk Assetsi numbritega
        if (taustanr == 1){
            spawnMax = 0;
            spawnMin = 0;
            taust.areaNimi = "Secret Camp";
        }
        if (taustanr == 2){
            spawnMax = 14;
            spawnMin = 10;
            taust.areaNimi = "Windy Plains";
        }
        if (taustanr == 3){
            spawnMax = 19;
            spawnMin = 15;
            taust.areaNimi = "Highlands";
        }
        if (taustanr == 4){
            spawnMax = 7;
            spawnMin = 0;
            taust.areaNimi = "Cemetery of the Damned";
        }
        if (taustanr == 5){
            spawnMax = 8;
            spawnMin = 9;
            taust.areaNimi = "Geometra's Throne.";
        }
        if (taustanr == 6){
            spawnMax = 25;
            spawnMin = 20;
            taust.areaNimi = "Cave";
        }
    }


    @Override
    public void tick() {
        bcdown--;
        if (bcdown <= 0) {
            checkLevel();
            bcdown = 60;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Taustad[taustanr], x, y, width, height, null);
        //g.drawImage(Taustad[8], 401, 0, 200, 600, null);
    }
}
/*


 */