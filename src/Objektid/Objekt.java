package Objektid;


import java.awt.*;

/**
 * Created by R on 04/10/2016.
 * Kõikide tegelaste superclass.
 */
public abstract class Objekt{
    String name = "";
    public int width, height, x, y = 0;

    public Objekt(String name, int x, int y, int width, int height){
        this.name = name;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        //System.out.println("Uus objekt: "+name);
    }

    public abstract void tick();
    public abstract void draw(Graphics g);





}
