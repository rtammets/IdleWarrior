package main.characters;

/**
 * Created by R on 04/10/2016.
 * Kõikide tegelaste superclass.
 */
public abstract class Character {
    String name = "";
    int health = this.health;
    int damage = this.damage;
    //siia tulevad sprite-i xy koordinaardid
    //siia tulevad sprite-i asukoha xy
    public Character (String name, int health, int damage){
        this.name = name;
        this.health = health;
        this.damage = damage;
        System.out.println("Uus character: "+name);
    }
}
