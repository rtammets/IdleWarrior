package Objektid;

import Kuva.Assets;

import java.awt.*;

import Main.Main;
import Main.Seisud.ManguSeis;

//import static Main.Seisud.ManguSeis.magic;
import static Main.Seisud.ManguSeis.spawner;
import static Main.Seisud.ManguSeis.mspawner;
import static Main.Seisud.ManguSeis.mangija;


/**
 * Created by R on 04/10/2016.
 * Tegelaste klass
 */
public class Player extends Tegelane {
    public String name;
    public int posY = y - height;
    //autoattacki jaoks
    public int aaDamage = 8;
    private int aaSpeed = 70; //mitu gameticki oota enne autoattacki
    private int tickCounter = 0;
    private int bcdown = 0;
    private Main game;

    public Player(Main game, String name, int x, int y, int width, int height, int health, int damage) {
        super(name, x, y, width, height, health, damage);
        this.game = game;
    }

    public void tick() {
        bcdown --;
        /*if(game.getNupuVajutus().space && bcdown<=0) {
//            autoAttack(5);

                bcdown = 60;
                hitFoe(this.damage);
                System.out.println("hit enemy!");
            }*/
        if (this.checkHealth()){
               autoAttack(aaDamage);
            }
        if (!this.checkHealth()) {
            System.out.println("Mäng läbi!");
            Main.playing = false;
        }
        }


    private void autoAttack(int aaDamage){
        if (tickCounter >= this.aaSpeed){
            //mspawner.tempMob.health = (mspawner.tempMob.health-aaDamage);
            tickCounter=0;
            //System.out.println("autoattack damage: "+aaDamage);
        }
        else tickCounter++;
    }

    private void hitFoe (int damage){
            //spawner.tempSprite.x = mangija.x;
            //spawner.tempSprite.y = (mangija.posY-(mangija.height/2));
            //if (spawner.tempSprite.x>= mspawner.tempMob.x) {
            //    mspawner.tempMob.health = (mspawner.tempMob.health - damage);}
    }


    public void draw(Graphics g) {
        g.drawImage(Assets.player, x, posY, width, height, null);
    }

}
/*
    //siia tulevad inputi asjad?
    //siia tulevad liikumise asjad? (tulevik)
    //siia tulevad skillid jne?
    //siia tuleb leveli calc?
    //siia tuleb levelup statside calc?

    //mängija statsid
    double exp;
    double money;
    int level;

    int attMax;
    int attMin;
    int critChance;
    int blackDiamonds;
    int lifeRegen;
    int manaRegen;

    int vitality;
    int power;
    int wisdom;

    int buttonClicks;
    int timesDied;
    int totalGold;

    //shop/upgrade staatused:
 */