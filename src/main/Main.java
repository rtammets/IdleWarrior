package Main;

import Kuva.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;

import Main.Seisud.MangKaib;
import Main.Seisud.State;
import Main.Sisendid.Sisendid;
import Main.Sisendid.hiireSisend;
import Objektid.Gear.Item;
import Objektid.Player;

import static Main.Seisud.MangKaib.mangija;
import static Objektid.Gear.Item.itemList;
import static Objektid.Player.playerInfo;
import static Objektid.UIElements.Taust.areaNimi;
import static Objektid.UIElements.Tekstid.newMessage;


/**
 * i200 Idle Warrior mängu peaklass
 * Autor: Rasmus Tammets
 *
 */
public class Main implements Runnable {
    //Akna muutujad
    public static Kuva kuva;
    public int aknaWidth,aknaHeight;
    public String aknaNimi;
    //mängu threadi muutujad
    public static boolean playing = false;
    private Thread thread; //see eraldab Main faili ülejäänud mängust käimise ajal
    //drawing / jframe muutujad..
    private BufferStrategy bs; //jframe vahemälu muutuja - puhver info hoidmiseks.. ennetab graafilisi anomaaliaid
    private Graphics g;



/////////////////////////
    //Playing
    private State gameState;
    private State menuState;
    //KLAHVID
    private Sisendid nupuVajutus = new Sisendid();
    private hiireSisend hiireVajutus = new hiireSisend();

    //kasutan kuva JFrame klassi, et luua mängu aken.
    public Main(String aknaNimi, int aknaWidth, int aknaHeight){
        this.aknaWidth = aknaWidth;
        this.aknaHeight = aknaHeight;
        this.aknaNimi = aknaNimi;
    }

    ///

    public static void saveProgress (){
        String fileName = (String) mangija.name+".txt";
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileName),"utf-8"));{
                writer.write(playerInfo());
                writer.close();
                newMessage("Game Saved!");
            }
        } catch (IOException e) {
            newMessage("GAME NOT SAVED, something went wrong!");
            e.printStackTrace();
        }
    }

    public static void saveItems (){

        //save all created items on a new line
        System.out.println("Saving items in itemList...");
        String refl = "";
        Object value = "";
        for (int i = 0; i<Item.itemList.size(); i++) {
            Item itm = Item.itemList.get(i);
            if (itm != null) {
                for (Field field : itm.getClass().getDeclaredFields()){
                    field.setAccessible(true);
                    try {
                        value = field.get(itm);
                        if (!field.getName().contains("itemList")){
                            if (value != null) {
                                refl += field.getName() + "=" + value + ",";//System.out.println(field.getName() +" = "+ value);
                            }else {refl = field.getName() +"="+ value + ","; }
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            refl += System.lineSeparator();
        }

       // vali mängija, käi läbi mängija statsid ja võta pane iga stat koos väärtusega kirja.
        System.out.println("Saving player...");
        Player p = mangija;
        Object pValue = "";
        if (p != null){
            for (Field playerField : p.getClass().getDeclaredFields()){
                playerField.setAccessible(true);
                try {
                    pValue = playerField.get(p);
                    if(pValue != null){
                        refl += playerField.getName() + "=" + pValue + ",";
                    }   else {refl = playerField.getName() + "=" + pValue + ","; }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }


        String fileName = (String) p.name+"items.txt";

        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName),"utf-8"));{
                writer.write(refl);
                writer.close();
                newMessage("Saved!");
            }
        } catch (IOException e) {
            newMessage("NOT SAVED, something went wrong!");
            e.printStackTrace();
        }
    }

    public static void loadProgress (String nimi) {
        loadItems(nimi);/*
        //otsime tegelase nimega savegame...
        String fileName = (String) nimi+".txt";
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader (fileName));
            //kuvan enda loetud info
            String loetudInfo;
            String[] processedStats;
            while ((loetudInfo = reader.readLine()) != null){
                processedStats = (loetudInfo.split(","));
                setPlayerInfo(processedStats);
            }
            //Anname mängijale STATSIIID <3
            //
            reader.close();
            newMessage("Loaded Savegame!");

        } catch (IOException e) {
            newMessage("No savegame found for "+nimi+".");
            //e.printStackTrace();
        }
*/


    }


    public static void loadItems (String nimi) {

        //otsime tegelase nimega savegame...
        String fileName = (String) nimi+"items.txt";
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader (fileName));
            //kuvan enda loetud info
            String loetudInfo;
            String[] processedStats;

// EI LOADI: bcdown, posY, tickcounter, sekundid, regentime, savetime, barwidth , ! GearSlots !, !game!,
            //WHILE statement ära?
            while ((loetudInfo = reader.readLine()) != null){
                processedStats = (loetudInfo.split(","));
                for (int i = 0; i< processedStats.length; i++){
                    String[] parts = processedStats[i].split("=",2);
                    String subP1 = parts[0];
                    String subP2 = parts[1];
                    //System.out.println(subP1 + "<- SubP1 |Loaded from File for setting| SubP2 ->" + subP2);

                    Player p = mangija;
                    if (subP1 != "posY" && subP1 !="bcdown" && subP1 !="tickCounter" && subP1 !="saveTime" && subP1 !="sekundid" && subP1 !="regenTime" && subP1 !="barWidth" && subP1 !="GearSlots" && subP1 !="game") mangija.setVariable(subP1, subP2);

                    /*
                    *
                    *
                    * LOAD GEAR SCRIPT
                    *
                    *
                    *
                    * */

                }//for
            }//while

            reader.close();
            newMessage("V2 loaded!");
        } catch (IOException e) {
            newMessage("No savegame(v2) found for "+nimi+"items.");
            //e.printStackTrace();
        }
    }



    public static void devHax (){
            mangija.exp = 6666666;
            mangija.coins = 6666666;
            mangija.blackDiamonds = 666666;
            mangija.title = "The ";
            newMessage("dev hax enabled");
        }

//////////////////////
    private void init(){ //valmistab ette uue mängu.
        kuva = new Kuva(aknaNimi,aknaWidth,aknaHeight);
        kuva.getFrame().addKeyListener(nupuVajutus);
        kuva.getFrame().addMouseListener(hiireVajutus);
        kuva.getFrame().addMouseMotionListener(hiireVajutus);
        Assets.init();
        gameState = new MangKaib(this);
        State.muudaSeisu(gameState);
        loadProgress(mangija.name);
        if (mangija.name.equals("dev"))devHax();




        //reflection test
/*        try{
            //toob selle @object asja..
            Field field = mangija.getClass().getDeclaredField("name");
            field.setAccessible(true);
            System.out.println(field.get(mangija));
        } catch (Exception e){e.printStackTrace();}

        try{
            Field field = Player.class.getDeclaredField("kasElus");
            field.setAccessible(true);
            System.out.println(field.get(null));
        }catch(Exception e){e.printStackTrace();}
*/
    }

    public static void reflMeth(){
//THIS WORKS :)
        //LOADIMISEKS: this. või mangija.$FIELD = $VALUE
        //ja kogu dump ongi laetud...
        //siia save jaoks return method
 /*       System.out.println("itemlist 0");
        String refl = null;
        Item itm = Item.itemList.get(0);
        if (itm != null) {
            for (Field field : itm.getClass().getDeclaredFields()){
                field.setAccessible(true);
                Object value = null;
                try {
                    value = field.get(itm);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (value != null){
                    refl += field.getName() +" = "+ value;//System.out.println(field.getName() +" = "+ value);
                }
            }

        } //System.out.println(itm);
*/
/*
        System.out.println("player invy 0");

        Item itm1 = mangija.gearSlots[0];
        if (itm1 != null) {
            for (Field field : itm1.getClass().getDeclaredFields()){
                field.setAccessible(true);
                Object value = null;
                try {
                    value = field.get(itm1);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (value != null){
                    System.out.println(field.getName() +" = "+ value);
                }
            }

        }*/
        //print mängija statsid
        /**/

/*
    //itemlist?
        System.out.println("itemList: \n");
        for (Field field : itemList.getClass().getDeclaredFields()){
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(itemList);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            if (value != null){
                System.out.println(field.getName() +" = "+ value);
            }
        }
*/


    }


    private void tick(){
        nupuVajutus.tick(); //main game tick nupuvajutusi jälgima
        if (State.leiaSeis() !=null)
                State.leiaSeis().tick();
    }

    //GUI KOGU INFOGA
    private void draw() {//joonistab kaadri sisse pilte
        bs = kuva.getCanvas().getBufferStrategy(); // vt bs muutuja selgitust
        if (bs == null) {
            kuva.getCanvas().createBufferStrategy(3);//bs puhvrite arv.. vb vähem?
            return;
        }
        g = bs.getDrawGraphics();// g on canvase muutuja, omistan talle puhvri sisu
        g.clearRect(0, 0, aknaWidth, aknaHeight); //puhastab mänguakna enne uue framega täitmist
        if (State.leiaSeis() != null) State.leiaSeis().draw(g); //joonistab mänguaknasse spraidid
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("Name: " + String.valueOf(mangija.title + mangija.name), 610, 25);
        g.drawString("HP: " + String.valueOf(mangija.health), 610, 40);
        g.drawString("Mana: " + String.valueOf(mangija.mana), 610, 55);
        g.drawString("Level: " + String.valueOf(mangija.myLevel) + " at " + String.valueOf(mangija.exp) + " EXP", 610, 70);
        g.drawString("Coins: " + String.valueOf(mangija.coins), 610, 85);
        g.drawString("Kills: " + String.valueOf(mangija.killcount), 610, 100);
        g.drawString("Area: " + areaNimi, 610, 115);
        if (mangija.blackDiamonds > 0) g.drawString("Black Diamonds: " + mangija.blackDiamonds, 610, 130);
        g.drawString("Armor: " + String.valueOf(mangija.pArmor), 610, 145);
        g.drawString("AVG DMG: " + String.valueOf(mangija.damage/2), 610, 160);
        if (mangija.boughtAA > 0) g.drawString("Auto DPS: " + String.valueOf(mangija.aaDamage), 610, 175);
        if (mangija.boughtAA > 0) g.drawString("Auto timer: " + String.valueOf(mangija.aaSpeed), 610, 190);
        g.drawString("VIT: " + String.valueOf(mangija.vitality), 610, 205);
        g.drawString("POW: " + String.valueOf(mangija.power), 610, 220);
        g.drawString("WIS: " + String.valueOf(mangija.wisdom), 610, 235);

        if (mangija.gearSlots[0] != null )g.drawString("Weapon: " + String.valueOf(mangija.gearSlots[0].itemName), 610, 250);
        if (mangija.gearSlots[1] != null )g.drawString("Body: " + String.valueOf(mangija.gearSlots[1].itemName), 610, 265);
        if (mangija.gearSlots[2] != null )g.drawString("Shield: " + String.valueOf(mangija.gearSlots[2].itemName), 610, 280);
        if (mangija.gearSlots[3] != null )g.drawString("Jewel: " + String.valueOf(mangija.gearSlots[3].itemName), 610, 295);

        bs.show();//muudab pildi nähtavaks
        g.dispose();//viskab vana frame minema
    }




    public void run() { //Siin elab mängu loop
        //State.muudaSeisu(menuState); //test Menüü seisu jaoks...
        init();
        int fps = 60; //game tickrate/fps muutuja
        double timePerTick = 1000000000 / fps;
        double ajamuut = 0;
        double hetkeAeg;
        double eelmineAeg = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(playing){ //kui mäng käib.. mäng käib ;)
            hetkeAeg = System.nanoTime();
            ajamuut += (hetkeAeg-eelmineAeg)/timePerTick;
            timer += hetkeAeg-eelmineAeg;
            eelmineAeg = hetkeAeg;
            if (ajamuut >= 1) {
                tick();
                draw();
                ajamuut--;
                ticks++;
            }
            if (timer >= 1000000000){
//                System.out.println("Minu FPS:"+ticks); //kui vaja teada kui suur fps on.
                timer=0;
                ticks=0;
            }
        }
        stop();//kui ei mängi, siis stop gracefully
    }

//////////////////////
    //Sisendite jaoks
    public Sisendid getNupuVajutus(){
        return nupuVajutus;
    }

    //synchronized on mängu threadi jaoks
    public synchronized void start(){
        if(playing) return; // loogika check, kui mäng käib ei pea käima tõmbama
        playing = true; //Mängime!
        thread = new Thread(this);
        thread.start(); //käivitab "run" funktsiooni, vajalik kui threadi kasutan
        System.out.println("Mäng algas.");
    }
    public synchronized void stop(){
        if (!playing) return;//loogika check, et peatatud mängu peatada ei üritataks
        playing = false; //katkestame mängu loopi
        System.out.println("Sulgen mängu...");
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
