package Objektid;

import Kuva.Assets;
import Main.Main;
import Main.Seisud.ManguSeis;
import java.awt.*;




/**
 *
 */
public class Vastane extends Tegelane {
        public String name;
        private boolean knockback = false;
        public int kiirus;
        public int posY = y - height;
        Main game;

    public Vastane(Main game, String name, int x, int y, int width, int height, int health, int damage, int kiirus) {
        super(name, x, y, width, height, health, damage);
        this.game = game;
        this.name = name;
        this.kiirus = kiirus;
    }

    private void hitPlayer (){
        if (this.x <= ManguSeis.mangija.x + ManguSeis.mangija.width) {
            ManguSeis.mangija.health -= this.damage;
            System.out.println("Said viga, elusid järel: " + ManguSeis.mangija.health);
        }
    }

    public int Move (int kiirus) { //mängija suunas liikumine
     if (this.knockback && this.x < ManguSeis.mangija.x + ManguSeis.mangija.width+100){
         return (this.x+(6*this.kiirus));
        } if (this.knockback && this.x > ManguSeis.mangija.x + ManguSeis.mangija.width+100) {
            this.knockback = false;
            return (this.x-this.kiirus);
        }

        if (this.x > ManguSeis.mangija.x+ManguSeis.mangija.width && !this.knockback){
            return (this.x-kiirus);
        }
        if (this.x <= ManguSeis.mangija.x+ManguSeis.mangija.width && !this.knockback) {
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
            g.drawImage(Assets.zombie, this.x, this.posY, this.width, this.height, null);;
        }
        /*public int getHit(int enemyDmg) {
            return 0;
        }
        */
}
