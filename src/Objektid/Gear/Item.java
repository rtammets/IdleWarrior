package Objektid.Gear;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static Main.Seisud.MangKaib.mangija;

/**
 * Created by R on 07/02/2017.
 */
public class Item {
    public String itemName = "";
    public int itemID;
    public int ilvl;
    public int vit;
    public int pow;
    public int wis;
    public int dmg;
    public int armr;
    public int life;
    public int slot = 0;
    /*
        Level ranges
                1-10 5-15
                11-20 10-25
                12-30 15-35
      */
    public static ArrayList<Item> itemList = new ArrayList<Item>();


    public Item(int iLvl) {
        //change values to DMG VIT POW WIS LIFE ARMOR
        this.slot = (int) (Math.random() * (4 - 0));
        this.vit = (int) (Math.random() * 50);
        this.pow = (int) (Math.random() * 50);
        this.wis = (int) (Math.random() * 50);
        this.dmg = (int) (Math.random() * 50);
        this.armr = (int) (Math.random() * 50);
        this.life = (int) (Math.random() * 50);
        this.itemName = getName();
        this.itemID = mangija.pItemID;

        itemList.add(this);
        mangija.pItemID ++;
        //Siit allpool testimiseks:
        //System.out.printf(String.valueOf(itemList.get(this.itemID).itemName)+"\n");
        //        System.out.printf(this.slot+this.itemName+"\n");
        ;

    }

    private String getName() {
        //add varied names
        //add names based on item slot
        String name = "";
        //weap
        String[] weapPrefix = {"The", "Worn", "", "Cursed", "Blessed", "Ultimate", "Arcane", "Mystic"};
        String[] weapSuffix = {"Light", "Dark", "Magic", "Water", "Fire", "Power", "Sages", ""};
        String[] weapSlotName = {"Staff", "Wand", "Stick", "Scepter"};

        //armr
        String[] armrPrefix = {"The", "Light", "", "Golden", "Worn", "Cursed", "Blessed", "Caster"};
        String[] armrSuffix = {"Gucci", "Fleece", "Battle", "Star", "War", "", "Magic", "Sages"};
        String[] armrSlotName = {"Body", "Robes", "Silks", "Dress"};


        //shld
        String[] shldPrefix = {"The", "Heavy", "", "Water", "Worn", "Old", "Blessed", "Cursed"};
        String[] shldSuffix = {"The", "Sage", "Eye", "War", "Fire", "Buff", "Strong", "Hawk"};
        String[] shldSlotName = {"Book", "Glove", "Orb", "Rune"};


        //jwl
        String[] jwlPrefix = {"Shining", "Magic", "Rare", "Lost", "Cursed", "Small", "", "Blessed"};
        String[] jwlSuffix = {"Focus", "Sage", "Eye", "Water", "Fire", "Buff", "Strong", "War"};
        String[] jwlSlotName = {"Charm", "Stone", "Gem", "Crystal"};
        switch (this.slot){
            case 0: name = weapPrefix[(int) (Math.random() * weapPrefix.length)] + " " + weapSlotName[(int) (Math.random() * weapSlotName.length)] + " of " + weapSuffix[(int) (Math.random() * weapSuffix.length)];
                break;
            case 1: name = armrPrefix[(int) (Math.random() * armrPrefix.length)] + " " + armrSuffix[(int) (Math.random() * armrSuffix.length)] + " " + armrSlotName[(int) (Math.random() * armrSlotName.length)];
                break;
            case 2: name = shldPrefix[(int) (Math.random() * shldPrefix.length)] + " " + shldSuffix[(int) (Math.random() * shldSuffix.length)] + " " + shldSlotName[(int) (Math.random() * shldSlotName.length)];
                break;
            case 3: name = jwlPrefix[(int) (Math.random() * jwlPrefix.length)] + " " + jwlSlotName[(int) (Math.random() * jwlSlotName.length)] + " of " + jwlSuffix[(int) (Math.random() * jwlSuffix.length)];
                break;
            default: break;

        }

        //name = prefixes[(int) (Math.random() * prefixes.length)] + suffixes[(int) (Math.random() * suffixes.length)] + slotName[this.slot];
        return name;
    }

    public String getItemStats() {
        String stats = ("" + this.itemName + " | DPS: +" + this.dmg + " | VIT: +" + this.vit + " | POW: +" + this.pow + " | WIS: +" + this.wis + " | Life: +" + this.life + " | Armor: +" + this.armr);
        return stats;
    }
}
/*
public class Item extends gearStat{
    public ArrayList<gearStat> itemStats = new ArrayList<gearStat>();

    public Item(int statAmount){
        makeItem(statAmount);
    }

    public void makeItem(int gearStats){
        for (int i=0; i<gearStats; i++){
            this.itemStats.set(i, new gearStat());
        }
        System.out.printf("Tehti uus Item"+ itemStats);
    }

}
*/