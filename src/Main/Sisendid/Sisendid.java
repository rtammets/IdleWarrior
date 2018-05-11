package Main.Sisendid;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;

/**
 * Created by R on 07/01/2017.
 */
public class Sisendid implements KeyListener {

    private boolean[] nupud;
    public boolean space,up,down,left,right,d,a,h,i,k,q,w,b,e,r;

    public Sisendid(){
        nupud = new boolean[256];
    }

    public void tick(){
        up = nupud[KeyEvent.VK_UP];
        down = nupud[KeyEvent.VK_DOWN];
        left = nupud[KeyEvent.VK_LEFT];
        right = nupud[KeyEvent.VK_RIGHT];
        space = nupud[KeyEvent.VK_SPACE];
        d = nupud[KeyEvent.VK_D]; //damage
        i = nupud[KeyEvent.VK_I]; //idle
        h = nupud[KeyEvent.VK_H]; //health
        a = nupud[KeyEvent.VK_A]; //autoattack
        k = nupud[KeyEvent.VK_A]; //knockback
        q = nupud[KeyEvent.VK_A]; //auto atk time
        w = nupud[KeyEvent.VK_A]; //auto atk dps
        b = nupud[KeyEvent.VK_A]; //button game
        e = nupud[KeyEvent.VK_A]; //crit%
        r = nupud[KeyEvent.VK_A]; //crit chance

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
