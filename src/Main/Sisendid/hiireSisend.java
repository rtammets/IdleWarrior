package Main.Sisendid;

import Main.Seisud.MangKaib;
import Objektid.UIElements.Tekstid;
import Objektid.mobSpawner;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import static Objektid.UIElements.Tekstid.*;
import static Main.Seisud.MangKaib.mspawner;

/**
 * Created by R on 19/01/2017.
 */
public class hiireSisend implements MouseListener, MouseMotionListener {

    public void mouseClicked(MouseEvent e){

       // newMessage("KEEGI VAJUTAS MIDAGI: " +e.getX()+" " + e.getY());
    }



    public boolean checkClicked (int mX, int mY, int x, int w, int y, int h){
        if (mX > x && mX < (x+w) && mY < y && mY > (y-h))return true;
        else return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
// Vastase ründamine hiireklõpsu abil!
        if (mspawner.enemies.size() > 0) {
            if (checkClicked(e.getX(), e.getY(), mspawner.tempMob.x, mspawner.tempMob.width, mspawner.tempMob.y, mspawner.tempMob.height)) mspawner.tempMob.health -= 5;
            //newMessage(""+nupuke.getMinX()+nupuke.getWidth()+nupuke.getMinY()+nupuke.getHeight());
        }

      //  if (checkClicked(e.getX(),e.getY(),nupuke.getMinX(),nupuke.getWidth(),nupuke.getMinY(),nupuke.getHeight()))newMessage("tere maailm");
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

