package Objektid;

import Main.Seisud.ManguSeis;
/**
 * Created by R on 04/10/2016.
 * Tegelaste klass
 */
public abstract class Tegelane extends Objekt {
    public String name;
    public int health;
    boolean kasElus = true;
    public int damage;




    //konstruktor - superklassi asjad + lisamuutujad
    public Tegelane(String name, int x, int y, int width, int height, int health, int damage) {
        super(name, width, height, x, y);
        this.health = health;
        this.damage = damage;
    }

    //Vastaste/lähivõitluse jaoks "inRange" või collision detection ka teha!



    /*public int getHit(int enemyDmg) {
        int arv = this.health;
        if (this.kasElus) {
            this.health = this.health - enemyDmg;
            if (!this.checkHealth()) this.Loot();
            System.out.println("Vastase elud: " + this.health);
        }
        return arv;
    } */

    public boolean checkHealth () {
        if (this.health <= 0) {
            kasElus = false;
            this.health=0;
        }
        return kasElus;
    }

    public int Loot(){
        int i = 1;
        System.out.println("Said nänni!");
        return i;
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