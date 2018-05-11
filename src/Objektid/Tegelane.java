package Objektid;

import Objektid.Gear.Item;

import static Main.Seisud.MangKaib.mangija;
import static Objektid.Gear.Item.itemList;
import static Objektid.UIElements.Tekstid.newMessage;

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
    public Tegelane(int x, int y, int width, int height, int health, int damage) {
        super(width, height, x, y);
        this.health = health;
        this.damage = damage;
    }

    //Vastaste/lähivõitluse jaoks "inRange" või collision detection ka teha!
    //Lisada global Damage arvutuse funktsioon
    public boolean checkHealth () {
        if (this.health <= 0) {
            kasElus = false;
            this.health=0;
        }
        else kasElus = true;
        return kasElus;
    }

    //COLLISON CHECK!
   //if (y < y2 && y+ height < y2 + height2 && x > x2 && x < x2 + width2) newMessage("TEST");

    //passiivne tagasitulek või millegi teke (hp, raha)
    protected int generateResource(int regenAmount, int addToThis){
                addToThis += regenAmount;
                return addToThis;
    }

    //looti + exp funktsioon millegi suremisel kasutamiseks
    // IMPROVE THIS
    //
    //
    //
    public double Loot(int mobXP) {
        double i = Math.random();
        int coinDrop = (int) Math.round(Math.random()*50 + (mangija.myLevel));

//        System.out.println("rng rollis"+i);
        mangija.exp += mobXP;
        mangija.killcount +=1;
        //newMessage("Sinu XP: "+mangija.exp);
        if (i > 0.99) {
            mangija.blackDiamonds +=1;
            newMessage("Found a super rare black diamond!!");
        }
        if (i > 0.7 && i < 0.74) {
            mangija.baseDamage += 1;
            newMessage("Found damage upgrade!");
        }
        if (i > 0.75 && i < 0.79) {
            mangija.baseArmor += 1;
            newMessage("Found armor upgrade!");
        }
        if (i > 0.8 && i < 0.85 && mangija.health > mangija.maxHealth) {
            mangija.health += 1;
            newMessage("Picked up some health!");
        }
        if (i > 0.6 && i < 0.699) {
            new Item(420);
            //System.out.println(itemList.get(itemList.size()-1).getItemStats());
            mangija.equipItem(itemList.size()-1);
            //newMessage("made an item, too bad it is useless for you right now :(");
        }
        if (i < 0.5) {
            newMessage("The enemy dropped "+coinDrop+" coins.");
            mangija.coins += coinDrop;
//            System.out.println("Said nänni!");  //testlause hetkel
        }
        return i;
    }
}