package Objektid;

import Main.Main;
import java.awt.*;
import java.util.LinkedList;

import static Main.Seisud.ManguSeis.mangija;
import static Main.Seisud.ManguSeis.mspawner;
import static Main.Seisud.ManguSeis.spawner;

/**
 * Created by R on 07/01/2017.
 */
public class mobSpawner extends Tegelane {
    Main game;
    Vastane tempMob;
    int kiirus;
    int bcdown = 0;
    int tot = 0;
    public int maxEnemies = 1;
    public LinkedList<Vastane> enemies = new LinkedList<Vastane>();
    public mobSpawner(Main game, String name, int x, int y, int width, int height, int health, int damage, int kiirus) {
        super(name, x, y, width, height, health, damage);
        this.game = game;
        this.name = name;
        this.kiirus = kiirus;
    }

    @Override
    public void tick() {
        bcdown --;
         if (enemies.size()<=maxEnemies-1){
             enemies.add(new Vastane(game,"Zombie"+tot,97,147,450,370,100,10,1));
             bcdown = 60;
         }
            for (int i = 0; i < enemies.size(); i++) {
                tempMob = enemies.get(i);
                tempMob.tick();
            }
//muidu crashib.. tühja hulka ei delete
        if (enemies.size() > 0 && tempMob.health<=0) removeMob(tempMob);
    //outofbounds loogika
        if (tempMob.x > 600 || tempMob.x < 0){
            removeMob(tempMob);
       }
        if (!this.checkHealth()) removeMob(tempMob);
    }


    @Override
    public void draw(Graphics g) {
        for(int i= 0; i < enemies.size(); i++){
            tempMob = enemies.get(i);
            tempMob.draw(g);
        }

    }

    public void addSprite(Vastane tempMob){
        enemies.add(tempMob);
    }
    public void removeMob(Vastane tempMob){
        enemies.remove(tempMob);
        mangija.aaDamage++;
    }
}
