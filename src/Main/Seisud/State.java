package Main.Seisud;

import Main.Main;

import java.awt.*;
import java.lang.management.GarbageCollectorMXBean;

/**
 * Created by R on 07/01/2017.
 */
public abstract class State {

    private static State hetkeSeis = null;

    public static void muudaSeisu(State state){
        hetkeSeis = state;
    }

    public static State leiaSeis(){
        return hetkeSeis;
    }
    //
    protected Main game;
    public State(Main game){
        this.game = game;
    }
    //tick ja draw kõigile saadavaks
    public abstract void tick();
    public abstract void draw(Graphics g);
}
