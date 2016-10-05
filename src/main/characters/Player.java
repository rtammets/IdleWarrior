package main.characters;

/**
 * Created by R on 04/10/2016.
 * Mängija klass.
 */
public class Player extends Character{
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

    //konstruktor - superklassi asjad + lisamuutujad
    public Player(String name, int health, int damage) {
        super(name, health, damage);
    }
    //siia tulevad inputi asjad?
    //siia tulevad liikumise asjad? (tulevik)
    //siia tulevad skillid jne?
    //siia tuleb leveli calc?
    //siia tuleb levelup statside calc?
}
