package Objektid;

import Kuva.Assets;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Arrays;

import Main.Main;
import Objektid.Gear.Item;
import Objektid.UIElements.Taust;


import static Kuva.Assets.Taustad;
import static Main.Main.saveProgress;
import static Main.Seisud.MangKaib.mangija;
import static Main.Seisud.MangKaib.mspawner;
import static Main.Seisud.MangKaib.taust;
import static Main.Start.nameHolder;
import static Objektid.Gear.Item.itemList;
import static Objektid.UIElements.Taust.taustanr;
import static Objektid.UIElements.Tekstid.newMessage;


/**
 * Mängija klass
 */
public class Player extends Tegelane {
    public String name = "Player";
    public String title = "";
    public static int bcdown = 0; //buttoncheck Down
    public int posY = y - height;
//game
    private double tickCounter = 0; // autoattacki tick counter
    int sekundid = 0;
    int regenTime = 240;
    int saveTime = 60*60;
//player
    //combat

    int maxMana = 100;
    public int mana = maxMana;
    int healthRegen = 0;
    int manaRegen = 1;
    int barWidth = 100;
    //muud statsid
    int manaCost = 5;
    int expToLevel = 7;
    public static int myLevel = 1;

    //total stats
    public int maxHealth;// hp baridega seonduva jaoks
    public int damage;
    public int critch = 1;
    public int critdmg = 25;
    public int pArmor = 1;
    public int vitality = 1;
    public int power = 1;
    public int wisdom = 1;


    //base character stats
    public int baseVitality = 1;
    public int basePower = 1;
    public int baseWisdom = 1;

    public int baseDamage = 1;
    public int baseArmor = 1;
    public int baseMaxHealth = 1;
    public int baseMaxMana = 1;



    public int killcount, exp, blackDiamonds;
    public int coins = 0;


    public static int clickDamage = 4;
    public int kbDistance = 100;

    //items
    //gear bonus stats
    int vitGear, powGear, wisGear, dmgGear, armrGear, lifeGear = 0;
    //0 = weapon 1 = body 2 = shield 3 = jewellery
    public Item[] gearSlots = new Item[4];
    public int pItemID = 0;
    private int saladus = 9;

    //SHOP stuff
    public int coinRegen = 0;
    public int expPassive = 0;
    //autoattacki jaoks
    public int aaDamage = 10;
    public int aaSpeed = 140; //mitu gameticki oota enne autoattacki
    public int boughtAA = 0;
    public int boughtBTN = 0;

    private Main game;



    ///
/// Savegame jaoks
    public static String playerInfo(){
        //title name level exp exptolevel coins maxhealth maxmana killcount coinregen
        // exppassive damage clickdamage blackdiamonds int power vitality
        // aaDamage aaSpeed boughtAA
        return (
                mangija.title+","+mangija.name + ","+mangija.myLevel+","+mangija.exp +","+
                mangija.expToLevel+","+mangija.coins + ","+mangija.coinRegen+","+
                mangija.maxHealth +","+mangija.maxMana+","+mangija.killcount + ","+
                mangija.expPassive+","+mangija.damage +","+mangija.aaDamage+","+
                mangija.aaSpeed + ","+mangija.boughtAA+","+mangija.clickDamage + ","+
                mangija.blackDiamonds+","+mangija.wisdom + ","+mangija.power+","+
                mangija.vitality + "," +mangija.pArmor
        );
    }
/*
    public static void setPlayerInfo(String[] loadedStats){
        System.out.println(Arrays.toString(loadedStats));
        //lae .txt failist CSV-d ja määra mängijale.
        //tükeldame laetud info:
        mangija.title = loadedStats[0];
        mangija.name = loadedStats[1];
        mangija.myLevel = Integer.parseInt(loadedStats[2]);
        mangija.exp = Integer.parseInt(loadedStats[3]);
        mangija.expToLevel = Integer.parseInt(loadedStats[4]);
        mangija.coins = Integer.parseInt(loadedStats[5]);
        mangija.coinRegen = Integer.parseInt(loadedStats[6]);
        mangija.maxHealth = Integer.parseInt(loadedStats[7]);
        mangija.health=mangija.maxHealth;
        mangija.maxMana = Integer.parseInt(loadedStats[8]);
        mangija.mana=mangija.maxMana;
        mangija.killcount = Integer.parseInt(loadedStats[9]);
        mangija.expPassive = Integer.parseInt(loadedStats[10]);
        mangija.damage = Integer.parseInt(loadedStats[11]);
        mangija.aaDamage = Integer.parseInt(loadedStats[12]);
        mangija.aaSpeed = Integer.parseInt(loadedStats[13]);
        mangija.boughtAA = Integer.parseInt(loadedStats[14]);
        mangija.clickDamage = Integer.parseInt(loadedStats[15]);
        mangija.blackDiamonds = Integer.parseInt(loadedStats[16]);
        mangija.wisdom = Integer.parseInt(loadedStats[17]);
        mangija.power = Integer.parseInt(loadedStats[18]);
        mangija.vitality = Integer.parseInt(loadedStats[19]);
        mangija.pArmor = Integer.parseInt(loadedStats[20]);
    }
*/

    ///

    public Player(Main game, int x, int y, int width, int height, int health, int damage) {
        super(x, y, width, height, health, damage);
        this.damage = damage;
        this.game = game;
        maxHealth = (int) this.health;
        this.name = nameHolder;
    }

    //et Player käiks gametickidega kaasas ja teeks midagi
    //mängijaga seotud tegevused
    //pood


    public void tick() {
//clueLess();
        bcdown--;
        saveTime--;
        if (this.checkHealth()) {
            //generate resources
            this.calcAttributes();
            if (sekundid >= regenTime) checkResources();



    //areate vahel liikumine
            if (bcdown <= 0 && mspawner.enemies.size() <= 0) {
                if (game.getNupuVajutus().up) {
                    if (taustanr == 3) {
                        taustanr = 6;
                        bcdown = 60;
                    }

                    if (taustanr == 1 && mangija.blackDiamonds >= 5) {
                        mangija.blackDiamonds -= 5;
                        Taust.taustanr = 5;
                        bcdown = 60;
                        newMessage("Five of your black diamonds turn to dust.");
                        newMessage("You have entered Geometra's throne. Good luck!");
                    }
                    if (taustanr == 1) {
                        bcdown = 60;
                        newMessage("Come back when you've found enough black diamonds...");
                    }
                }
                if (game.getNupuVajutus().down) {
                    if (taustanr == 6) {
                        taustanr = 3;
                        bcdown = 60;
                    }  Main.saveItems(); //Main.reflMeth();
                }
                if (game.getNupuVajutus().left) {
                    if (taustanr > 1 && taustanr < (Taustad.length - 2)) {
                        Taust.taustanr -= 1;
                        bcdown = 60;
                    }
                }
                if (game.getNupuVajutus().right) {
                    if (taustanr > 0 && taustanr < (Taustad.length - 3)) {
                        Taust.taustanr += 1;
                        bcdown = 60;
                    }

                }
            }
            //mida see teeb
            taust.checkLevel();
        }

        if (boughtAA>0) autoAttack(aaDamage);

        //kontrolli kas oled surnud, kui surnud pane elud täis ja respawni
        if (!this.checkHealth()) {
            newMessage("Sa said surma!");
            this.health = this.maxHealth;
            taustanr = 1;
        }
        sekundid++;
        if (sekundid > regenTime) sekundid = 0;
        if (saveTime<=0) {
            saveTime=60*60;
            saveProgress();
        }
    }



    private void autoAttack(int aaDamage){
        if (tickCounter >= this.aaSpeed && mspawner.enemies.size() > 0){
            mspawner.tempMob.health = (mspawner.tempMob.health-aaDamage);
            tickCounter=0;
            //newMessage("Autoattacked enemy for " +aaDamage +" damage.");
        }
        else tickCounter++;
    }

    private void checkRegens(){
        //kui statsid on madalamad siis regeni juurde
       //if (this.maxMana>=(this.mana+this.manaRegen))
        //System.out.println("manaRegen" + this.manaRegen);
        //if (this.maxHealth>=(this.health+this.healthRegen))
        this.mana = generateResource(this.manaRegen, this.mana);
        this.health = generateResource(this.healthRegen, this.health);
        this.coins   = generateResource(this.coinRegen, this.coins);
        this.exp     = generateResource(this.expPassive, this.exp);
    }
    //kontrollib et elusid v manat poleks üle capi
    private void checkResources(){
        this.checkRegens();
        if (this.mana>=this.maxMana)this.mana = this.maxMana;
        if (this.health>=this.maxHealth)this.health = this.maxHealth;

    }

    /// /Mängija leveli arvutamine olemasoleva xp põhjal + stats calc
    private void checkLevel (){

        if (exp>=expToLevel){
            myLevel += 1;
            Math.round (expToLevel *= 1.35);
            newMessage("Congratulations, you are now level " + this.myLevel + ". Exp to next level: " +(this.expToLevel-this.exp)+".");
            resetHealth();
         }
    }

    private void resetHealth(){
        this.mana = this.maxMana;
        this.health = this.maxHealth;
    }

    public void equipItem(int id){
        if (itemList.size()>0) {
            int i = itemList.get(id).slot;
            switch (i) {
                case 0:
                    newMessage("New Gear: " + itemList.get(id).getItemStats()+"\n");
                    gearSlots[i] = itemList.get(id);
                    //
                    break;
                case 1:
                    newMessage("New Gear: " + itemList.get(id).getItemStats()+"\n");
                    gearSlots[i] = itemList.get(id);
                    //
                    break;
                case 2:
                    newMessage("New Gear: " + itemList.get(id).getItemStats()+"\n");
                    gearSlots[i] = itemList.get(id);
                    //
                    break;
                case 3:
                    newMessage("New Gear: " + itemList.get(id).getItemStats()+"\n");
                    gearSlots[i] = itemList.get(id);
                    //
                    break;
                default:
                    newMessage("Item slot id invalid");
            }
        }
    }





public void calcAttributes (){

    this.checkLevel();
        //arvutame statsid base + gear + muu bonus põhjal
    this.loadGearStats();

    //calcing base stats
    this.baseVitality = 2*myLevel;
    this.basePower = 1*myLevel;
    this.baseWisdom = 1*myLevel;
    //calcing sum attrib
    this.vitality = this.vitGear + this.baseVitality;
    this.power = this.powGear + this.basePower;
    this.wisdom = this.wisGear + this.baseWisdom;

    this.baseMaxHealth = Math.round(50+(this.vitality*25));
    this.baseMaxMana = (int) Math.round(100+(this.wisdom*0.3));
    this.baseDamage = 1 + (this.power/2);
    this.clickDamage = 1*myLevel;


    //calcing total stats
    this.damage = 10 + this.dmgGear + this.baseDamage;
    //System.out.println("damage value: " + this.damage);
    this.pArmor = this.armrGear + this.baseArmor;
    this.maxHealth = this.lifeGear + this.baseMaxHealth;
    this.maxMana = this.baseMaxMana;//+this.manaGear
    this.healthRegen = (int) (this.vitality*0.28);
    this.manaRegen = (int)1 + (this.wisdom/125);

}

public void loadGearStats(){
    //newMessage(String.valueOf( "Pre reset" + vitGear + " " + powGear + " " + wisGear + " " + dmgGear + " " + armrGear + " " + lifeGear));
    resetGear();
    //newMessage(String.valueOf( "Post reset" + vitGear + " " + powGear + " " + wisGear + " " + dmgGear + " " + armrGear + " " + lifeGear));
    for (Item gear : gearSlots){
        if (gear != null){
            vitGear += gear.vit;
            powGear += gear.pow;
            wisGear += gear.wis;
            dmgGear += gear.dmg;
            armrGear += gear.armr;
            lifeGear += gear.life;
        }
        //vaatame mis total tuleb
    }
    //newMessage(String.valueOf("Set stats to" + vitGear + " " + powGear + " " + wisGear + " " + dmgGear + " " + armrGear + " " + lifeGear));

}

public void setVariable(String variable, String value){
        //get type of local variable and convert string to variable
    try {
        String dataType = String.valueOf((this.getClass().getDeclaredField(variable).getType()));
        //System.out.println(dataType);
        this.getClass().getDeclaredField(variable).setAccessible(true);
        try {
            if (dataType.equals("int")) {
                int vInt = Integer.parseInt(value);
                //System.out.println("selected integer");
                this.getClass().getDeclaredField(variable).set(this, vInt);
            }
            if (dataType.equals("double")) {
                double vDbl = Double.parseDouble(value);
                //System.out.println("selected Double");
                this.getClass().getDeclaredField(variable).set(this, vDbl);
            }
            if (dataType.equals("class java.lang.String")) {
                //System.out.println("selected String");
                this.getClass().getDeclaredField(variable).set(this, value);
            }

        } catch (IllegalAccessException e){ e.printStackTrace();}
    } catch (NoSuchFieldException e) {
        e.printStackTrace();
    }
    resetHealth();
}

    public void resetGear(){
        vitGear = 0;
        powGear = 0;
        wisGear = 0;
        dmgGear = 0;
        armrGear = 0;
        lifeGear = 0;
    }
    public void draw(Graphics g) {
        g.drawImage(Assets.player, x, posY, width, height, null);
        g.setColor(Color.red);
        g.fillRect(this.x,(this.posY-15),100,10);
        g.setColor(Color.green);
        mangija.barWidth = Math.round((100*mangija.health)/mangija.maxHealth);
        g.fillRect(mangija.x,(mangija.posY-15),(mangija.barWidth),10);
        g.setColor(Color.darkGray);
        g.drawString((this.title + this.name), this.x, this.posY-20);
    }
}