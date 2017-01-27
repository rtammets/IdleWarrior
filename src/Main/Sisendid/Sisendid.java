package Main.Sisendid;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

/**
 * Created by R on 07/01/2017.
 */
public class Sisendid implements KeyListener {

    private boolean[] nupud;
    public boolean space,up,down,left,right,d,a,h,i;

    public Sisendid(){
        nupud = new boolean[256];
    }

    public void tick(){
        up = nupud[KeyEvent.VK_UP];
        down = nupud[KeyEvent.VK_DOWN];
        left = nupud[KeyEvent.VK_LEFT];
        right = nupud[KeyEvent.VK_RIGHT];
        space = nupud[KeyEvent.VK_SPACE];
        d = nupud[KeyEvent.VK_D];
        i = nupud[KeyEvent.VK_I];
        h = nupud[KeyEvent.VK_H];
        a = nupud[KeyEvent.VK_A];
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
