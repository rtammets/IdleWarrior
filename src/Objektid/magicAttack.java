package Objektid;

import java.awt.*;
import Main.Main;
import java.util.LinkedList;

import static Main.Seisud.MangKaib.mangija;
import static Main.Seisud.MangKaib.mspawner;
import static Main.Seisud.MangKaib.magicAttack;
import static Objektid.UIElements.Tekstid.newMessage;

/**
 * See vastutab kuuli tüüpi spraitide loomise eest.
 */
public class magicAttack extends Tegelane{
    private int tickCounter, bcdown = 0; // button check down tickcounter
    private int bcd1=0;
    public int x,y,width,height;
    int damage = mangija.damage; // anname damage muutujale mängija damage väärtuse, sest seda kasutab mängija.
    public  LinkedList<Magic> m = new LinkedList<Magic>();
    private boolean remove = false;
    Magic tempSprite;

    //liidame Main loopiga
    Main game;

    public magicAttack(Main game, int x, int y, int width, int height, int health, int damage) {
        super(x, y, width, height, health, damage);
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void tick(){
        bcdown --;
        bcd1 --;

        for (int i = 0; i < m.size(); i++) {
            tempSprite = m.get(i);
            tempSprite.damage = mangija.damage;
            tempSprite.tick();
        }

        //Kas kuul puutub vastast?
        if (m.size()>0) { // kontrollime ainult siis kui kuule on
            if (mspawner.enemies.size() > 0) {
                //tingimusel, et pole cooldownil, kontrollime tabamust võrreldes kuuli xy ja xy max vastase omadega.
                if (bcd1 <= 0 && magicAttack.tempSprite.y < mspawner.tempMob.y && magicAttack.tempSprite.y+ magicAttack.tempSprite.height < mspawner.tempMob.y + mspawner.tempMob.height && magicAttack.tempSprite.x > mspawner.tempMob.x && this.x < mspawner.tempMob.x + mspawner.tempMob.width) {
                    mspawner.tempMob.health = (mspawner.tempMob.health - magicAttack.tempSprite.damage);
                    removeSprite(magicAttack.tempSprite); //kuna vastane sai pihta kaob kuul ära
                    if (mspawner.tempMob.health<=0) mspawner.tempMob.health=0;
                    newMessage("Hit enemy for: " + magicAttack.tempSprite.damage);
                    //System.out.println("Vastasel elusid: " + mspawner.tempMob.health);
                    bcd1 = 35; //tuleb oodata enne järgmist damage kontrolli
                }
            }

            //delete kui mängu areaast väljas, hiljem võiks screen x min ja max muutuja järgi võtta
            if (this.tempSprite.x > 600 || this.tempSprite.x < 0) {
                this.remove = true;
                //newMessage("Oh ei, mööda läks!");
                removeSprite(magicAttack.tempSprite);
            }
        }
        //kas kasutaja vajutas nuppu
        if (game.getNupuVajutus().space && bcdown <= 0 && mangija.mana>=mangija.manaCost) {
            m.add(new Magic(game, this.x, this.y, 40, 20, 1, damage));
            mangija.mana -= mangija.manaCost;
            bcdown = 60;
        }
    }

    public void draw(Graphics g){
           for (int i=0; i<m.size(); i++){
               tempSprite = m.get(i);
               tempSprite.draw(g);
           }
    }

    public void addSprite(Magic shot){
        m.add(shot);
    }
    public void removeSprite(Magic shot){
        m.remove(shot);
    }
}

