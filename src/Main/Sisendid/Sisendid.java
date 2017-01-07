package Main.Sisendid;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by R on 07/01/2017.
 */
public class Sisendid implements KeyListener {

    private boolean[] nupud;
    public boolean space,up,down,left,right;

    public Sisendid(){
        nupud = new boolean[256];
    }

    public void tick(){
        up = nupud[KeyEvent.VK_UP];
        down = nupud[KeyEvent.VK_DOWN];
        left = nupud[KeyEvent.VK_LEFT];
        right = nupud[KeyEvent.VK_RIGHT];
        space = nupud[KeyEvent.VK_SPACE];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        nupud[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        nupud[e.getKeyCode()] = false;
    }


}
