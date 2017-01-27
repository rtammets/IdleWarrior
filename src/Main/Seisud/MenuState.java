package Main.Seisud;

import java.awt.*;

import Main.Main;
import Objektid.playMenu;

/**
 *
 */
public class MenuState extends State {
    private playMenu PlayMenu;
    public MenuState(Main game){
        super(game);
        PlayMenu = new playMenu(0,1400,600,400);
    }

    public void tick() {
        PlayMenu.tick();
            }
    public void draw(Graphics g) {
        PlayMenu.draw(g);
    }
}
