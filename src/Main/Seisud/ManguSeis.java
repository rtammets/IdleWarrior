package Main.Seisud;



import Main.Main;
import Objektid.*;

import java.awt.*;

/**
 *
 */
public class ManguSeis extends State{
    public static Player mangija;
    public static Vastane vastane1;
    private Taust taust;
    public static Magic magic;
    public static Spawner spawner;
    public static mobSpawner mspawner;

    public ManguSeis(Main game){
        super(game);
        taust = new Taust("Windy Plains",0,0,600,400);
        mangija = new Player(game,"Idler",97,174,50,370,100,10);
        spawner = new Spawner(game,"bullet",(mangija.x+mangija.width-15),(mangija.y-(mangija.height/2)),40,20,1,6);
        mspawner = new mobSpawner(game,"autozomb",97,147,450,370,50,5,1);
    }

    public void tick() {
        taust.tick();
        mangija.tick();
        //vastane1.tick();
        //magic.tick();
        spawner.tick();
        mspawner.tick();
    }

    public void draw(Graphics g) {
        taust.draw(g);
        mangija.draw(g);
        //vastane1.draw(g);
        //magic.draw(g);
        spawner.draw(g);
        mspawner.draw(g);
    }
}

//vastane1 = new Vastane(game,"ZombieBaddie",97,147,450,370,100,10,1);
//magic = new Magic (game,"tempObj",51,406,47,18,1,8);
