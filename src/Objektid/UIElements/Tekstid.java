package Objektid.UIElements;

import Kuva.Assets;
import Main.Seisud.MangKaib;
import Main.Sisendid.Sisendid;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import static Kuva.Assets.Nupp;
import static Main.Seisud.MangKaib.mangija;
import static Main.ajaLoendur.ajaLoendur;

/**
 * Created by R on 15/01/2017.
 */
public class Tekstid {
   // public static BufferedImage nupuke = Assets.Nupp[0];
    int storeX = 610;
    int storeY = 450;

    static LinkedList<String> chatbox = new LinkedList<>();
    public static int x;
    public static int y;
    public static int tekstiread = 0;
    static int maxRead = 11;
    static String[] randomTekstid = {
            "This is a random message!",
            "Such game, much wow!",
            "Geometra was a powerful sorceress.",
            "Geometra enhanced her magic with triangles.",
            "Lately, there have been numerous sightings of Geometra's minions in these parts.",
            "What are Black Diamonds used for?",
            "Is this real life?!",
            "The cake is a lie.",
            "You get a random song stuck in your head...",
            "Where are these monsters coming from?",
            "I wish I could buy nicer robes.",
            "Did you hear that?",
            "Life's a beach!",
            "Knock-knock...",
            "...Who's there?",
            "I wish the shop sold FOOD.",
            "Thanks for playing.",
            "Somebody walked past you and said hi, but you didn't recognize them.",
            "._.",
            "You. Are. AWESOME!"
            //There will be cake. ; That's what she said. ;
    };


    public void teeRead(){ //annab chatbox arrayle väärtused
        for (int i=0; i<maxRead; i++){
            chatbox.add(i, String.valueOf(" "));
        }
    }


    public Tekstid(int x, int y ){//nt width, int height) {
        this.x = x;
        this.y = y;
        teeRead();
        newMessage("Hello, "+ mangija.name+"! Welcome to Idle Warrior by rtammets!");
    }

    public static void newMessage(String message){
//        System.out.println(chatbox.size()+"chatbox size"); //for debug.
        if (chatbox.size()>=maxRead) chatbox.removeLast(); //tekstiread=0;
            String uus = "["+ajaLoendur()+"]"+message;
            chatbox.add(0, uus);

            tekstiread++;
    }

    public void tick() {
        if (Math.random()<0.000004){
            int i = (int)((Math.random()*(18-0))+0);
            newMessage(randomTekstid[i]);
        }
    }

    public void draw(Graphics g) {
//loogika poe nuppude kuvamiseks, rectangled tuleb eraldi luua
        for (int i=0; i<Assets.Nupp.length; i++){
//            System.out.println(Assets.Nupp.length);
            g.drawImage(Assets.Nupp[i], storeX, storeY,null);
            if (storeX < 785) {
                storeX += 35;
            }
            if (storeX >= 785) {
                storeX = 610; storeY += 35;
            }
            if (storeY >= 520) storeY = 450;
            if (i>=Assets.Nupp.length) i=0;
        }

        for (int i=0; i<chatbox.size(); i++) {
             g.drawString(chatbox.get(i), x, (y+(i*15)));
        }
    }
}
