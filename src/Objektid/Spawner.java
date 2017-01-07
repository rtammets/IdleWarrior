package Objektid;

import java.awt.*;
import Main.Main;
import java.util.LinkedList;

import Main.Seisud.ManguSeis;
import Objektid.mobSpawner;
import static Main.Seisud.ManguSeis.magic;
import static Main.Seisud.ManguSeis.mspawner;
import static Main.Seisud.ManguSeis.spawner;

/**
 * See vastutab kuuli tüüpi spraitide loomise eest.
 */
public class Spawner extends Tegelane{
    private int tickCounter, bcdown = 0;
    private int bcd1=0;
    public int x,y,width,height,health,damage;
    public int countKills=0;
    public  LinkedList<Magic> m = new LinkedList<Magic>();
    private boolean remove = false;
    Magic tempSprite;

    //liidame Main loopiga
    Main game;

    public Spawner(Main game, String name, int x, int y, int width, int height, int health, int damage) {
        super(name, x, y, width, height, health, damage);
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        addSprite(new Magic(game,"",x,y,width,height,health,damage));
    }

    public void tick(){
        bcdown --;
        bcd1 --;
        //damage
        if (game.getNupuVajutus().space && bcdown <= 0) {
            m.add(new Magic(game, "", this.x, this.y, 40, 20, 1, 7));
            bcdown = 60;
        }
        if (mspawner.enemies.size() > 0) {
            if (bcd1<=0 && spawner.tempSprite.x > mspawner.tempMob.x && this.x < mspawner.tempMob.x+mspawner.tempMob.width){
                mspawner.tempMob.health = (mspawner.tempMob.health - spawner.tempSprite.damage);
                removeSprite(spawner.tempSprite);
                System.out.println("Vastasel elusid: " + mspawner.tempMob.health);
                bcd1 = 50;
            }
        }
        for(int i= 0; i < m.size(); i++){
            tempSprite = m.get(i);
            tempSprite.tick();
        }

        if (tempSprite.x > 600 || remove==true){
            removeSprite(tempSprite);

            if (bcd1<=0) System.out.println("Oh ei, mööda läks!");
        }


    }

    public void hitMob(){


    }

    public void draw(Graphics g){
        for (int i=0; i<m.size(); i++){
            tempSprite = m.get(i);
            tempSprite.draw(g);
        }
    }

    public void addSprite(Magic shot){
        m.add(shot);
    }
    public void removeSprite(Magic shot){
        m.remove(shot);
    }
}

