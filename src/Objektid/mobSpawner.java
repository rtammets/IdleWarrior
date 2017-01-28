package Objektid;

import Kuva.Assets;
import Main.Main;
import Objektid.UIElements.Taust;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import static Kuva.Assets.Mobid;
import static Main.Seisud.MangKaib.mangija;
import static Objektid.UIElements.Tekstid.newMessage;
import static Objektid.Vastane.mobiNr;

/**
 * See klass vastutab vastaste loomise eest.
 */
public class mobSpawner extends Tegelane {
    Main game;
    public Vastane tempMob; //muutuja kuvatava vastase objekti jaoks
    int kiirus;
    int bcdown = 0;
    //int totalSpawned = 0; mdea äkki on huvitav kunagi
    public int maxEnemies = 1;
    public static int spawnMin, spawnMax = 0;


//    public mobSpawner(Main game, int x, int y, int width, int height, int health, int damage, int kiirus) {

    public static LinkedList<Vastane> enemies = new LinkedList<Vastane>();
    public mobSpawner(Main game, int x, int y, int width, int height, int health, int damage, int damageMax, int mobSpeed, String name, int mobXP, int mobGold, int mobLvl) {
        super(x, y, width, height, health, damage);
        this.game = game;
        this.kiirus = kiirus;
//        if (mobiNr > 0) this.width +=3000;
    }


    @Override
    public void tick() {
        bcdown --;//delay muutujast võetakse iga tickiga üks maha (vt. bcdown)
        //kui vastaseid ei ole, tee uus
        if (Taust.taustanr > 1) {//kui pole safeZones
            if (enemies.size() <= maxEnemies - 1 && bcdown <=0) {

                //hangib area apawntyped ja genereerib randomiga nende seast mobi mida spawnida
                //checkSpawnTypes(){
                //
                mobiNr = (int) Math.round(Math.random()*(spawnMax-spawnMin)+spawnMin);
                // }
                //                  Main game, int x, int y, int width, int height, int health, int damage, int damageMax, int mobSpeed, String name, int mobXP, int mobGold, int mobLvl                 ||||enemies.add(new Vastane(game, 97, 147, 450, 370, 100, 10, 1)); ///SIIA MUUTUJA VASTASE NIME NUMBRIGA VMS
                enemies.add(new Vastane(game, Mobid[mobiNr].getWidth(), Mobid[mobiNr].getHeight(), 450, 370, km[mobiNr].elud, km[mobiNr].dpsMin, km[mobiNr].dpsMax, km[mobiNr].mobSpeed, km[mobiNr].nimi, km[mobiNr].mobXp, km[mobiNr].mobGold,km[mobiNr].mobGold)); ///SIIA MUUTUJA VASTASE NIME NUMBRIGA VMS
                bcdown = 140; //delay gametickides enne järgmist kontrolli
            }
            for (int i = 0; i < enemies.size(); i++) { //tee uus vastane ja pane ta tiksuma
                tempMob = enemies.get(i);
                tempMob.tick();
            }
// Kontrollime kas vastane elab veel, jooksutame on death koodiread.
// NB! checki kõigepealt kas vastaseid on, muidu crashib.. tühja hulka ei delete
            if (enemies.size() > 0 && tempMob.health <= 0) {
                Loot(tempMob.mobXP); // testime loot funktsiooni!
                newMessage(tempMob.name+ " died.");
                removeMob(tempMob);
                bcdown = 140;
            }
            //outofbounds loogika
            if (tempMob.x > 600 || tempMob.x < 0) {
                removeMob(tempMob);
                bcdown = 140;
            }
            if (!this.checkHealth() || !mangija.checkHealth()) {
                removeMob(tempMob);
                bcdown = 140;
            }
        }
    }

    @Override
    public void draw(Graphics g) { //joonistame vastaseid
        for(int i= 0; i < enemies.size(); i++){
            tempMob = enemies.get(i);
            tempMob.draw(g);
        }

    }
//Add/remove funktsioonid
    public void addSprite(Vastane tempMob){
        enemies.add(tempMob);
    }
    public void removeMob(Vastane tempMob){
        enemies.remove(tempMob);
        mangija.aaDamage++;
    }

    //Kõikide mobide info
    //teen array mobide data jaoks, sama pikk kui Mobide spraitide array suurus
    //km = kõik mobid
    public static mobInfo[] km = new mobInfo[Assets.Mobid.length];
    //                      health, dMin dMax, ms, name,        XP, G, LVL
    public static void lisaMobInfo() {
        km[0] = new mobInfo(3650,   25,250,2,"Weak Zombie",     350,280,38);
        km[1] = new mobInfo(3470,   25,250,2,"Half a Zombie",   350,280,37);
        km[2] = new mobInfo(3540,   25,250,2,"Crawling Torso",  250,280,39);
        km[3] = new mobInfo(3800,   25,250,2,"Potato-Z",        250,280,43);

        km[4] = new mobInfo(4500,   50,450,2,"Colossus Skeleton",550,540,50);
        km[5] = new mobInfo(3666,   30,250,3,"Skeletorso",      350,350,42);
        km[6] = new mobInfo(3400,   25,250,2,"Headless",        350,300,41);
        km[7] = new mobInfo(3400,   25,200,2,"Undead Necromancer",400,350,45);

        km[8] = new mobInfo(50000,  350,2000,1,"▲ Illuminato ▲", 15000,15000,69);
        km[9] = new mobInfo(1000,   200,500,5,"▲ Minion ▲",     650,500,69);
//                          health, dMin dMax, ms, name,        XP, G, LVL
        km[10] = new mobInfo(40,    0,1,3,"[err.00x1_a.bug]",   2,2,2);
        km[11] = new mobInfo(80,    0,10,2,"Rabid Beast",       5,5,7);
        km[12] = new mobInfo(70,    0,10,2,"Ravencrow",         5,5,6);
        km[13] = new mobInfo(100,   0,10,2,"Huge Frog",         6,6,8);
        km[14] = new mobInfo(90,    0,15,2,"Carnivorous Plant", 8,8,10);

        km[15] = new mobInfo(900,   0,50,2, "Dalmatian Shark", 15,25,15);
        km[16] = new mobInfo(1450,  10,125,2, "Ent",          50,60,25);
        km[17] = new mobInfo(1150,  0,55,2, "Anti-You",       30,40,20);
        km[18] = new mobInfo(1050,  5,45,2, "Bandit",         25,35,18);
        km[19] = new mobInfo(1100,  0,40,2, "▲ Priestess",   25,35,17);

        km[20] = new mobInfo(1900,  25,250,2,"Cave Bat",        75,50,18);
        km[21] = new mobInfo(2100,  25,250,2,"Undead Miner",    75,50,21);
        km[22] = new mobInfo(1900,  25,250,2,"Lost Explorer",   75,50,20);
        km[23] = new mobInfo(1500,  0,0,0,"Unknown Mineral",    400,205,16);
        km[24] = new mobInfo(100,   0,0,0,"Unknown Mineral",    305,305,16);
        km[25] = new mobInfo(1500,  0,0,0,"Unknown Mineral",    175,450,16);
        }
}
