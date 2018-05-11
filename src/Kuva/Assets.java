package Kuva;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;
import java.util.Arrays;

import static Objektid.UIElements.Tekstid.newMessage;


/**
 * Created by R on 07/01/2017.
 */
public class Assets {
    public static BufferedImage player, magic, magicAA, uiKonsool,uiSideBar;
    public static BufferedImage[] Taustad = new BufferedImage[7];//{};//{playWindow,secretCamp,windyPlains};
    public static BufferedImage[] Mobid = new BufferedImage[26];//{};//{zombie,player};
    public static BufferedImage[] Nupp = new BufferedImage[10];
    public static Rectangle[] bounds = new Rectangle[Nupp.length];

    //public static BufferedImage[] userInterface = new BufferedImage[3]; // UI

    public static void init(){

        tooSprait spraidid = new tooSprait(ImpordiPilt.laePilt("/images/sprites.png"));
        player = spraidid.crop(0,1200,97,174);
        Taustad[0] = spraidid.crop(0,1400,600,400); //playwindow
        Taustad[1] = spraidid.crop(600,4121,600,400); //secretCamp
        Taustad[2] = spraidid.crop(0,0,600,400); //windyPlains
        Taustad[3] = spraidid.crop(600,2870,600,400); //highlands
        Taustad[4] = spraidid.crop(600,2450,600,400); //cemetery of the damned
        Taustad[6] = spraidid.crop(600,3300,600,400); //the cave
        Taustad[5] = spraidid.crop(600,0,600,400); //Geometra's Throne.

        Nupp[0] = spraidid.crop(600,2100,31,31);//DPS
        Nupp[1] = spraidid.crop(600,2140,31,31);//AA
        Nupp[2] = spraidid.crop(152,1062,30,30);//HEAL
        Nupp[3] = spraidid.crop(760,2140,31,31);//IDLE
        Nupp[4] = spraidid.crop(720,2180,31,31);//knockback
        Nupp[5] = spraidid.crop(640,2140,31,31);//AAspeed
        Nupp[6] = spraidid.crop(680,2140,30,30);//AAdamage
        Nupp[7] = spraidid.crop(680,2220,31,31);//Button game
        Nupp[8] = spraidid.crop(640,2100,31,31);//Crit%
        Nupp[9] = spraidid.crop(680,2100,31,31);//Crit Dmg%

        uiKonsool = spraidid.crop(200,900,595,100); //UI-Box
        uiSideBar = spraidid.crop(0,500,200,500); //UI-BOTTOM
        //
        //interface
      //  userInterface[0] = spraidid.crop(0,500,200,400); //UI-LEFT
       // userInterface[1] = spraidid.crop(200,900,400,200); //UI-BOTTOM
        //Mobs damned
        Mobid[0] = spraidid.crop(200,1200,97,147); // weak zombie
        //System.out.println(Mobid[0]);
        Mobid[1] = spraidid.crop(200, 1200, 49, 147); // half a zombie
        Mobid[2] = spraidid.crop(450, 1335, 177, 59); // crawling torso
        Mobid[3] = spraidid.crop(100, 1200, 97, 147); // potato-Z
        Mobid[4] = spraidid.crop(810, 1600, 190, 342); //colossus skeleton
        Mobid[5] = spraidid.crop(810, 1955, 186, 200); //skeletorso
        Mobid[6] = spraidid.crop(1045, 1600, 220, 296); //headless
        Mobid[7] = spraidid.crop(1000, 1335, 152, 252); //undead necromancer
        //triboss
        Mobid[8] = spraidid.crop(451, 1050, 231, 199); // //Illuminato AKA triangle boss ▲ Illuminato ▲
        Mobid[9] = spraidid.crop(451, 1050, 231, 199); // Illuminato minion
        //windy
        Mobid[10] = spraidid.crop(625, 1275, 27, 27); //[err.00x1_a.bug]
        Mobid[11] = spraidid.crop(820, 1375, 126, 123); //rabid beast
        Mobid[12] = spraidid.crop(1000, 1195, 159, 132); //ravencrow
        Mobid[13] = spraidid.crop(1000, 850, 165, 86); //huge frog
        Mobid[14] = spraidid.crop(1000, 580, 150, 245); //carnivorous plant
        //
        Mobid[15] = spraidid.crop(450, 1280, 144, 43); //dalmatian shark
        Mobid[16] = spraidid.crop(750, 1050, 199, 299); //ent
        Mobid[17] = spraidid.crop(0, 2810, 97, 174); //anti-you
        Mobid[18] = spraidid.crop(1045, 1905, 92, 171); //bandit
        Mobid[19] = spraidid.crop(1165, 1345, 140, 246); //▲ Priestess
        //cave
        Mobid[20] = spraidid.crop(1165, 1195, 160, 141); //cave bat
        Mobid[21] = spraidid.crop(1150, 1905, 120, 253); //undead miner
        Mobid[22] = spraidid.crop(1280, 1905, 125, 246); //lost explorer
        Mobid[23] = spraidid.crop(1275, 2160, 171, 305); //unknown mineral
        Mobid[24] = spraidid.crop(1450, 2160, 127, 257); //unknown mineral
        Mobid[25] = spraidid.crop(1450, 2420, 127, 257); //unknown mineral

        magic = spraidid.crop(51,406,47,18); //player attack
        magicAA = spraidid.crop(0,451,33,11); //Autoattack attack


        makeRects();
        System.out.println(Arrays.toString(bounds));
    }

    public static void makeRects() {
        //X ja Y asukoha placeholder
        int nuppX = 610;
        int nuppY = 450;
        //Rectangle array loomine Nuppude arvu järgi
        for (int i = 0; i < Nupp.length; i++){
            System.out.println("Nupp length: "+ Nupp.length+ " i: " + i);
            bounds[i] = new Rectangle(nuppX, nuppY, Nupp[i].getHeight(), Nupp[i].getHeight());

                if (nuppX < 785) {
                    nuppX += 35;
                }
                if (nuppX >= 785) {
                    nuppX = 610; nuppY += 35;
                }
                if (nuppY >= 520) nuppY = 450;
           //     if (i>=Assets.Nupp.length) i=0;




        }
    }

}
