package Objektid;

import static Kuva.Assets.*;
import static Objektid.UIElements.Tekstid.newMessage;

import Main.Main;
import Main.Seisud.MangKaib;

import java.awt.*;
import java.awt.event.MouseEvent;


/**
 *
 */
public class Vastane extends Tegelane {
        public static int mobiNr = 0;
        int drawHeight = (int)Math.round(this.height);
        int drawWidth =  (int) Math.round(this.width);
        private boolean knockback = false;
        public int kiirus;
        public int posX = (int) x - drawWidth;
        public int posY = (int) y - drawHeight;
        int barWidth = 100;
        int maxMobHealth, damageMin,damageMax,mobXP,mobGold,mobLvl;

        Main game;

    public Vastane(Main game, int x, int y, int width, int height, int health, int damage, int damageMax, int mobSpeed, String name, int mobXP, int mobGold, int mobLvl) {
        super(x, y, width, height, health, damage);
        this.game = game;
        this.kiirus = mobSpeed;
        this.maxMobHealth = health;
        this.name = name;
        this.mobXP=mobXP;
        this.mobGold=mobGold;
        this.damageMin=damage;
        this.damageMax=damageMax;
        this.mobLvl=mobLvl;
    }

    private void hitPlayer (){
        if (this.x <= MangKaib.mangija.x + MangKaib.mangija.width) {
            //damage roll
            int hitDamage = (int)Math.round(Math.random()*(this.damageMax-damageMin)+damageMin);
            //armor reduction
            hitDamage= hitDamage-hitDamage*(MangKaib.mangija.pArmor/(MangKaib.mangija.pArmor+10*hitDamage));
            MangKaib.mangija.health -= hitDamage;
            newMessage("You got hit for: " + hitDamage+".");
           // System.out.println("Said viga, elusid järel: " + MangKaib.mangija.health);
        }
    }

    public int Move (int kiirus) { //mängija suunas liikumine
     if (this.knockback && this.x < MangKaib.mangija.x + MangKaib.mangija.width+MangKaib.mangija.kbDistance){
         return (this.x+(6*this.kiirus));
        } if (this.knockback && this.x > MangKaib.mangija.x + MangKaib.mangija.width+100) {
            this.knockback = false;
            return (this.x-this.kiirus);
        }

        if (this.x > MangKaib.mangija.x+ MangKaib.mangija.width && !this.knockback){
            return (this.x-kiirus);
        }
        if (this.x <= MangKaib.mangija.x+ MangKaib.mangija.width && !this.knockback) {
            this.knockback = true;
            return (this.x + (6 * this.kiirus));
        }
        return (this.x-this.kiirus);
    }

        public void tick() {
           if (this.checkHealth()) {
               this.x = Move(this.kiirus);
               hitPlayer();
           }



        }
    public void draw(Graphics g) {
        g.drawImage(Mobid[mobiNr], this.x, this.posY, drawWidth, drawHeight, null);
        g.setColor(Color.red);
        g.fillRect(this.x,(this.posY-15),100,10);
        g.setColor(Color.green);
        this.barWidth = Math.round((100*this.health)/this.maxMobHealth);
        g.fillRect(this.x,(this.posY-15),(this.barWidth),10);
        g.setColor(Color.darkGray);
        g.drawString(this.name, this.x, this.posY-20);
        }
}
