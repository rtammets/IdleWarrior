package Main.Sisendid;

import Kuva.Assets;
import Main.Seisud.MangKaib;
import Objektid.UIElements.Tekstid;
import Objektid.mobSpawner;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static Main.Seisud.MangKaib.mangija;
import static Objektid.UIElements.Tekstid.*;
import static Main.Seisud.MangKaib.mspawner;

/**
 * Created by R on 19/01/2017.
 */
public class hiireSisend implements MouseListener, MouseMotionListener {




    public void mouseClicked(MouseEvent e){

       // newMessage("KEEGI VAJUTAS MIDAGI: " +e.getX()+" " + e.getY());
    }
    public static int[] getCoords(MouseEvent e){
        int[] xy = {e.getX(),e.getY()};
        return xy;
    }


    public boolean checkClicked (int mX, int mY, int x, int w, int y, int h){
        if (mX > x && mX < (x+w) && mY < y && mY > (y-h))return true;
        else return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mX = e.getX();
        int mY = e.getY();
// Vastase ründamine hiireklõpsu abil!

        if (mspawner.enemies.size() > 0) {
            if (checkClicked(mX, mY, mspawner.tempMob.x, mspawner.tempMob.width, mspawner.tempMob.y, mspawner.tempMob.height)) mspawner.tempMob.health -= mangija.clickDamage;
                //newMessage(""+nupuke.getMinX()+nupuke.getWidth()+nupuke.getMinY()+nupuke.getHeight());
        }


        Point clicked = e.getPoint();
        Rectangle bounds = new Rectangle (610,300, Assets.Nupp[0].getWidth(), Assets.Nupp[0].getHeight());
        if (bounds.contains(clicked)) {
//            newMessage(""+Assets.Nupp[0].getTileGridXOffset()+Assets.Nupp[0].getMinTileX());
           newMessage("HIIRE ASJAD ON NÕMEDAD"); // target image was clicked
        }


      /*  for (int i = 0; i< Assets.Nupp.length; i++){
            int x =Assets.Nupp[i].getMinX();
            int y =Assets.Nupp[i].getMinY();
            int w =Assets.Nupp[i].getWidth();
            int h =Assets.Nupp[i].getHeight();
            if (checkClicked(mX, mY, x, y, w, h)) {
                switch (i){
                    case 0://DAMAGE NUPP
                        if (mangija.coins >= 10){
                            mangija.coins -= 10;
                            mangija.damage +=5;
                        }
                        break;
                    case 1://AA
                        if (!mangija.boughtAA && mangija.coins >=50){
                            mangija.coins -=50;
                            mangija.boughtAA = true;
                        }
                        break;
                    case 2://HEAL
                        if (mangija.coins >= 25 && (mangija.health-25)<mangija.maxHealth){
                            mangija.coins -=25;
                            mangija.health +=25;
                        }
                        break;
                    case 3://IDLE GAINS
                        if (mangija.coins >= 40){
                            mangija.coins -=40;
                            mangija.expPassive +=1;
                            mangija.coinRegen +=1;

                        }
                        break;
                    default: newMessage("midagi läks valesti lol");
                    break;
                }

            }


        }*/
    }


    @Override
    public void mouseReleased(MouseEvent e) {
       // newMessage("mouseReleased");
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        //newMessage("MouseEntered");
    }
    @Override
    public void mouseExited(MouseEvent e) {
        //newMessage("mouseExited");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    //    newMessage("mouseDragged");

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        newMessage("mouseMoved");

    }


}

