package Main.Seisud;



import Main.Main;
import Objektid.*;
import Objektid.UIElements.Taust;
import Objektid.UIElements.Tekstid;
import Objektid.UIElements.uiBox;
import Objektid.UIElements.uiSide;

import java.awt.*;

import static Kuva.Assets.Mobid;
import static Objektid.Vastane.*;
import static Objektid.mobSpawner.km;


/**
 *
 */
public class MangKaib extends State{
    public static Player mangija;
//    public static Vastane vastane1;
    public static Taust taust;
    private uiBox kLiides;
    private uiSide kLiides2;
    //    public static Magic magic;
    public static magicAttack magicAttack;
    public static mobSpawner mspawner;
    public static Tekstid uusRida;

    //temp



    public MangKaib(Main game){
        super(game);
        taust = new Taust(0,0,600,400);
        taust.checkLevel();// checkime, mis areaga tegu on peale uue tausta loomist
        mobSpawner.lisaMobInfo(); // laeme mob info.
        //game, width height draw x draw y, elud, damage, nimi, exp
        mangija = new Player(game, 97,174,50,370,100,10);

        mspawner = new mobSpawner(game, Mobid[13].getWidth(), Mobid[13].getHeight(), 450, 370, km[13].elud, km[13].dpsMin, km[13].dpsMax, km[13].mobSpeed, km[13].nimi, km[13].mobXp, km[13].mobGold,km[13].mobGold);
//        mspawner = new mobSpawner(game, 97,147,450,370,50,5,1);
        magicAttack = new magicAttack(game,(mangija.x+mangija.width-15),(mangija.y-(mangija.height/2)),40,20,1,mangija.damage);
        kLiides = new uiBox(0,400,600,200);
        kLiides2 = new uiSide(600,0,200,600);
        uusRida = new Tekstid(0,420);//420 v 590
    }

    public void tick() {
        taust.tick();
        mangija.tick();
        magicAttack.tick();
        if (Taust.taustanr>1)mspawner.tick();
        kLiides.tick();
        kLiides2.tick();
        uusRida.tick();
    }

    public void draw(Graphics g) {
        taust.draw(g);
        mangija.draw(g);
        magicAttack.draw(g);
        mspawner.draw(g);
        kLiides.draw(g);
        kLiides2.draw(g);
        uusRida.draw(g);
    }
}

//vastane1 = new Vastane(game,"ZombieBaddie",97,147,450,370,100,10,1);
//magic = new Magic (game,"tempObj",51,406,47,18,1,8);
// need on vaja kui Spawnereid ei kasuta!  ->>> magic.tick(); vastane1.tick();
//Vaja kui spawnereid ei kasuta ->>> //vastane1.draw(g);//magic.draw(g);