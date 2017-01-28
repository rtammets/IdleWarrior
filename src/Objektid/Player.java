package Objektid;

import Kuva.Assets;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Main.Main;
import Main.Sisendid.hiireSisend;
import Objektid.UIElements.Taust;

import static Kuva.Assets.Taustad;
import static Main.Main.kuva;
import static Main.Main.saveProgress;
import static Main.Seisud.MangKaib.mangija;
import static Main.Seisud.MangKaib.mspawner;
import static Main.Seisud.MangKaib.taust;
import static Main.Start.nameHolder;
import static Objektid.UIElements.Taust.taustanr;
import static Objektid.UIElements.Tekstid.newMessage;



/**
 * Created by R on 04/10/2016.
 * Tegelaste klass
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
    int healthRegen = (2);
    int manaRegen = (maxMana/100*5);
    int barWidth = 100;

    //
    public static int clickDamage = 5;
    //autoattacki jaoks
    public int aaDamage = 10;
    private double aaSpeed = 140; //mitu gameticki oota enne autoattacki
    public boolean boughtAA = false;

    //skill mana costs
    int manaCost = 5;

    int expToLevel = 5;
    //
    int vitality = 1;
    int power = 1;
    int wisdom = 1;

    public int killcount, exp;
    int blackDiamonds = 10;
    public int coins = 60000;
    public int myLevel = 1;
    public int coinRegen = 0;
    public int expPassive = 0;
    public int damage;


    private Main game;



    ///
/// Savegame jaoks
    public static String playerInfo(){

        return ( mangija.title+mangija.name + ","+ mangija.myLevel+","+mangija.coins);
    }


    ///

    public Player(Main game, int x, int y, int width, int height, int health, int damage) {
        super(x, y, width, height, health, damage);
        this.damage = damage;
        this.game = game;
        maxHealth = (int) this.health;
        this.name = nameHolder;
    }

    public void misNimi(){

    }
    //et Player käiks gametickidega kaasas ja teeks midagi
    public void tick() {

        bcdown--;
        if (this.checkHealth()) {
            //
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
                        //System.out.println(taustanr);
                    }
                }
                if (game.getNupuVajutus().right) {
                    if (taustanr > 0 && taustanr < (Taustad.length - 3)) {
                        Taust.taustanr += 1;
                        bcdown = 60;
                        //System.out.println(taustanr);
                    }
                    if (taustanr == 4 && mangija.blackDiamonds < 5) {
                        bcdown = 60;
                        newMessage("Come back when you've found enough black diamonds...");
                    }
                    if (taustanr == 4 && mangija.blackDiamonds >= 5) {
                        mangija.blackDiamonds -= 5;
                        Taust.taustanr += 1;
                        bcdown = 60;
                        newMessage("Three of your black diamonds turn to dust.");
                        newMessage("You have entered Geometra's throne. Good luck!");
                    }
                }
            }
            if (game.getNupuVajutus().a && !this.boughtAA && this.coins >= 50) {
                this.coins -= 50;
                this.boughtAA = true;
                saveProgress();
            }
            if (game.getNupuVajutus().d && this.coins >= 10) {
                this.coins -= 10;
                this.damage += 5;
            }
            if (game.getNupuVajutus().h && this.coins >= 25) {
                this.coins -= 25;
                this.health += 25;
            }
            if (game.getNupuVajutus().i && this.coins >= 40) {
                this.coins -= 40;
                this.expPassive += 1;
                this.coinRegen += 1;
            }

            taust.checkLevel();
        }
        if (boughtAA) autoAttack(aaDamage);


        if (!this.checkHealth()) {
            newMessage("Sa said surma!");
            this.health = this.maxHealth;
            taustanr = 1;
            //mangija.checkHealth();//Main.playing = false; // VANA GAMEOVER
        }
        sekundid++;
        if (sekundid > regenTime) sekundid = 0;
    }

    private void autoAttack(int aaDamage){
        if (tickCounter >= this.aaSpeed && mspawner.enemies.size() > 0){
            mspawner.tempMob.health = (mspawner.tempMob.health-aaDamage);
            tickCounter=0;
            newMessage("Autoattack hit enemy for " +aaDamage +" damage.");
            //System.out.println("Autoattack damage: "+aaDamage);
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
    /// /Mängija leveli arvutamine olemasoleva xp põhjal,
    private void checkLevel (){
        if (exp>=expToLevel){
            myLevel += 1;
            Math.round (expToLevel *= 1.5);
            newMessage("Congratulations, you are now level " +this.myLevel+". Exp to next level: "+this.expToLevel+".");
            this.maxHealth += Math.round((this.myLevel+10)*2);
            this.maxMana += this.myLevel*0.4;
            this.vitality +=1;
            this.power +=1;
            this.wisdom +=1;
            /*	this.lifeRegen = (this.vitality*0.1);
            	this.manaRegen += (this.wisdom*0.1);
	            this.baseMana += this.wisdom;*/
            this.mana = this.maxMana;
		    this.health = this.maxHealth;
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