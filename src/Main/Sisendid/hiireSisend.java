package Main.Sisendid;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static Kuva.Assets.bounds;
import static Main.Seisud.MangKaib.mangija;
import static Objektid.UIElements.Tekstid.*;
import static Main.Seisud.MangKaib.mspawner;


/**
 * Created by R on 19/01/2017.
 */
public class hiireSisend implements MouseListener, MouseMotionListener {

    int dmgPrice = 10;
    int aaDprice = 40;
    int aaSprice = 50;
    int idlePrice = 50;
    int critPrice = 50;
    int critDprice = 50;
    int kbPrice = 50;
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

        for (int i = 0; i < bounds.length; i++) {
            if (bounds[i].contains(clicked)) {
                //newMessage("Vajutasid nupule nr " + i + "."); // target image was clicked
                //POOD / clickables?
                switch (i) {
                    //DMG
                    case 0: if (mangija.coins >= dmgPrice) {mangija.coins -=dmgPrice; mangija.damage +=5; dmgPrice +=10; newMessage("Bought more damage for " + dmgPrice+ " coins!");}
                        break;
                    //AA
                    case 1: if (mangija.boughtAA < 1 && mangija.coins >= 50) {mangija.coins -=50; mangija.boughtAA = 1; newMessage("Bought autoattack ability.");}
                        break;
                    //Heal
                    case 2: if (mangija.coins >= (mangija.maxHealth/9) && mangija.health != mangija.maxHealth) {mangija.coins -=(mangija.maxHealth/9); mangija.health += (mangija.maxHealth/9); if (mangija.health >= mangija.maxHealth) mangija.health = mangija.maxHealth; newMessage("Healed some health.");}
                        break;
                    //idle gains
                    case 3: if (mangija.coins >= idlePrice) {mangija.coins -=idlePrice; mangija.expPassive += 1; mangija.coinRegen += 1; idlePrice +=30; newMessage("Upgraded passive incomes for" + idlePrice+ " coins!");}
                        break;
                    //knockback
                    case 4: if (mangija.kbDistance < 300 && mangija.coins >= kbPrice) {mangija.coins -=kbPrice; mangija.kbDistance += 5; newMessage("Upgraded knockback for " + kbPrice + " coins!");}
                        break;
                    //AA speed
                    case 5: if (mangija.aaSpeed > 70 && mangija.coins >= aaSprice) {mangija.coins -=aaSprice; mangija.aaSpeed -= 1; aaSprice += 25; newMessage("Upgraded autoattack speed for " + aaSprice+ " coins!");}
                        break;
                    //AA deeps
                    case 6: if (mangija.coins >= aaDprice) {mangija.coins -=aaDprice; mangija.aaDamage += 1; aaDprice += 10; newMessage("Upgraded autoattack damage for " + aaDprice + " coins!");}
                        break;
                    //Button game
                    case 7: if (mangija.boughtBTN < 1 && mangija.coins >= 100) {mangija.coins -= 100; mangija.boughtBTN = 1; newMessage("Wanna play a little (button) game?");}
                        break;
                    //Crit %
                    case 8: if (mangija.critch < 100 && mangija.coins >= critPrice) {mangija.coins -=critPrice; mangija.critch += 1; critPrice += 25; newMessage("Bought Crit Chance upgrade for " + critPrice + " coins!");}
                        break;
                    //Crit damage %
                    case 9: if (mangija.critdmg < 250 && mangija.coins >= critDprice) {mangija.coins -=critDprice; mangija.critdmg += 1; critDprice += 25; newMessage("Bought Crit Multi upgrade for " + critDprice + " coins!");}
                        break;

                    default: newMessage("button error " + i);
                }

                //saaks teha clicked X ja playeri / poe klassi all tegevuse teha
            }
        }
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