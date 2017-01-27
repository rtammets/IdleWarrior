package Main;

import Kuva.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

import Main.Seisud.MangKaib;
import Main.Seisud.State;
import Main.Sisendid.Sisendid;
import Main.Sisendid.hiireSisend;

import static Main.Seisud.MangKaib.mangija;
import static Objektid.UIElements.Taust.areaNimi;


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
    //Mänguseis
    private State gameState;
    private State menuState;
    //KLAHVID
    private Sisendid nupuVajutus = new Sisendid();
    //private hiireSisend hiireVajutus = new hiireSisend();
    private hiireSisend hiireVajutus = new hiireSisend();

    //kasutan kuva JFrame klassi, et luua mängu aken.
    public Main(String aknaNimi, int aknaWidth, int aknaHeight){
        this.aknaWidth = aknaWidth;
        this.aknaHeight = aknaHeight;
        this.aknaNimi = aknaNimi;
        nupuVajutus = new Sisendid();
    }

    ///
    //TEST


//////////////////////
    private void init(){ //valmistab ette uue mängu.
        kuva = new Kuva(aknaNimi,aknaWidth,aknaHeight);
        kuva.getFrame().addKeyListener(nupuVajutus);
        kuva.getFrame().addMouseListener(hiireVajutus);
        kuva.getFrame().addMouseMotionListener(hiireVajutus);
        Assets.init();
        gameState = new MangKaib(this);
        State.muudaSeisu(gameState);
    }

    private void tick(){
        nupuVajutus.tick(); //main game tick nupuvajutusi jälgima
        if (State.leiaSeis() !=null)
                State.leiaSeis().tick();
    }

    private void draw(){//joonistab kaadri sisse pilte
        bs = kuva.getCanvas().getBufferStrategy(); // vt bs muutuja selgitust
        if (bs == null){
            kuva.getCanvas().createBufferStrategy(3);//bs puhvrite arv.. vb vähem?
            return;
        }
        g = bs.getDrawGraphics();// g on canvase muutuja, omistan talle puhvri sisu
        g.clearRect(0,0,aknaWidth,aknaHeight); //puhastab mänguakna enne uue framega täitmist
        if(State.leiaSeis() != null) State.leiaSeis().draw(g); //joonistab mänguaknasse spraidid
        //setcolor ja kujundite joonistamine. neid kasutan hiljem, et teha hp bare jne....
        g.setFont(new Font("Arial", Font.BOLD, 13));
        g.drawString("Elud: "+String.valueOf(mangija.health),610,40);
        g.drawString("Mana: "+String.valueOf(mangija.mana),610,55);
        g.drawString("Level: "+String.valueOf(mangija.myLevel)+" at "+ String.valueOf(mangija.exp) + " EXP",610,70);
        g.drawString("Coins: "+String.valueOf(mangija.coins),610,85);
        g.drawString("Kills: " + String.valueOf(mangija.killcount),610,100);
        g.drawString("Area: "+areaNimi,610,115);
        /*g.setColor(Color.red);        g.fillRect(200,200,50,50);        g.setColor(Color.GRAY);        g.drawRect(250,250,50,50);*/
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
