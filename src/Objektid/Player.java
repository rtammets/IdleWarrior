package Objektid;

import Kuva.Assets;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import Main.Main;
import Main.Sisendid.hiireSisend;
import Objektid.UIElements.Taust;

import static Kuva.Assets.Taustad;
import static Main.Main.kuva;
import static Main.Main.loadProgress;
import static Main.Main.saveProgress;
import static Main.Seisud.MangKaib.mangija;
import static Main.Seisud.MangKaib.mspawner;
import static Main.Seisud.MangKaib.taust;
import static Main.Start.nameHolder;
import static Objektid.UIElements.Taust.taustanr;
import static Objektid.UIElements.Tekstid.newMessage;


/**
 * Mängija klass
 */
public class Player extends Tegelane {
    public String name = "Player";
    public String title = "";
    private int bcdown = 0; //buttoncheck Down
    public int posY = y - height;

    private double tickCounter = 0; // autoattacki tick counter
    int sekundid = 0;
    int regenTime = 240;
    //Mängija muutujad

    //combat
    public int maxHealth;// hp baridega seonduva jaoks
    int maxMana = 100;
    public int mana = maxMana;
    int healthRegen = 0;
    int manaRegen = 1;
    int barWidth = 100;

    //

    //autoattacki jaoks
    public int aaDamage = 10;
    private double aaSpeed = 140; //mitu gameticki oota enne autoattacki
    public boolean boughtAA = false;

    //skill mana costs
    int manaCost = 5;

    int expToLevel = 7;
    public static int myLevel = 1;
    //
    int vitality = 1;
    int power = 1;
    int wisdom = 1;

    public int killcount, exp, blackDiamonds;
    public int coins = 55455450;

    public int coinRegen = 0;
    public int expPassive = 0;
    public int damage;
    public static int clickDamage = 4;


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
                mangija.vitality
        );
    }

    public static void setPlayerInfo(String loadedStats){
        //lae .txt failist CSV-d ja määra mängijale.
        //tükeldame laetud info:
        String[] pieces = loadedStats.split(",");
    }


    ///

    public Player(Main game, int x, int y, int width, int height, int health, int damage) {
        super(x, y, width, height, health, damage);
        this.damage = damage;
        this.game = game;
        maxHealth = (int) this.health;
        this.name = nameHolder;
    }

    //et Player käiks gametickidega kaasas ja teeks midagi
    public void tick() {

        bcdown--;
        if (this.checkHealth()) {
            //generate resources
            if (sekundid >= regenTime) checkResources();
            this.checkLevel();


            if (bcdown <= 0 && mspawner.enemies.size() <= 0) {
                if (game.getNupuVajutus().up) {
                    if (taustanr == 3) {
                        taustanr = 6;
                        bcdown = 60;
                    }

                }
                if (game.getNupuVajutus().down) {
                    if (taustanr == 6) {
                        taustanr = 3;
                        bcdown = 60;
                    }
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
                    if (taustanr == 4 && mangija.blackDiamonds >= 5) {
                        mangija.blackDiamonds -= 5;
                        Taust.taustanr += 1;
                        bcdown = 60;
                        newMessage("Five of your black diamonds turn to dust.");
                        newMessage("You have entered Geometra's throne. Good luck!");
                    }
                    if (taustanr == 4 && mangija.blackDiamonds < 5) {
                        bcdown = 60;
                        newMessage("Come back when you've found enough black diamonds...");
                    }
                }
            }
            if (bcdown <= 0) {
                if (game.getNupuVajutus().a && !this.boughtAA && this.coins >= 50) {
                    this.coins -= 50;
                    this.boughtAA = true;
                }
                if (game.getNupuVajutus().d && this.coins >= 10) {
                    this.coins -= 10;
                    this.damage += 5;
                    loadProgress();
                }
                if (game.getNupuVajutus().h && this.coins >= 25 && this.health != this.maxHealth) {
                    this.coins -= 25;
                    this.health += 25;
                    if (this.health>=this.maxHealth)this.health = this.maxHealth;
                }
                if (game.getNupuVajutus().i && this.coins >= 40) {
                    this.coins -= 40;
                    this.expPassive += 1;
                    this.coinRegen += 1;
                    saveProgress();
                }
                bcdown=60;
            }
            taust.checkLevel();
        }

        if (boughtAA) autoAttack(aaDamage);

        if (!this.checkHealth()) {//mangija.checkHealth();//Main.playing = false; // VANA GAMEOVER
            newMessage("Sa said surma!");
            this.health = this.maxHealth;
            taustanr = 1;
        }
        sekundid++;
        if (sekundid > regenTime) sekundid = 0;
    }

    private void autoAttack(int aaDamage){
        if (tickCounter >= this.aaSpeed && mspawner.enemies.size() > 0){
            mspawner.tempMob.health = (mspawner.tempMob.health-aaDamage);
            tickCounter=0;
            newMessage("Autoattack hit enemy for " +aaDamage +" damage.");
        }
        else tickCounter++;
    }

    private void checkRegens(){
       if (this.maxMana>=(this.mana+this.manaRegen)) this.mana = generateResource(this.manaRegen, this.mana);
       if (this.maxHealth>=(this.health+this.healthRegen)) this.health = generateResource(this.healthRegen, this.health);
        this.coins =generateResource(this.coinRegen, this.coins);
        this.exp = generateResource(this.expPassive, this.exp);
    }
    private void checkResources(){
        this.checkRegens();
        if (this.mana>=this.maxMana)this.mana = this.maxMana;
        if (this.health>=this.maxHealth)this.health = this.maxHealth;

    }
    /// /Mängija leveli arvutamine olemasoleva xp põhjal + stats calc
    private void checkLevel (){
        if (exp>=expToLevel){
            myLevel += 1;
            Math.round (expToLevel *= 1.5);
            newMessage("Congratulations, you are now level " +this.myLevel+". Exp to next level: "+this.expToLevel+".");
            this.vitality +=1;
            this.power +=1;
            this.wisdom +=1;
            this.maxHealth = Math.round(100+(this.vitality*5));
            this.maxMana += this.wisdom*0.3;
            this.healthRegen = (int) (this.vitality*0.2);
            this.manaRegen = (int)(maxMana/100);
            this.mana = this.maxMana;
		    this.health = this.maxHealth;
		    //newMessage("manarege"+this.manaRegen);
		    ///TEMP
            this.damage +=(this.power/2);
         }
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