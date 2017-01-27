package Main;

import java.util.concurrent.TimeUnit;

/**
 * Created by R on 19/01/2017.
 */
public class ajaLoendur {
    //sekundites, seega jagan 1 000 000 000-ga
    static double algusAeg = System.nanoTime();

    //Annab kulunud aja sekundites
    public static String ajaLoendur(){
        double aeg =  ((System.nanoTime()-algusAeg));
        long seconds = TimeUnit.MINUTES.SECONDS.convert((long)aeg, TimeUnit.NANOSECONDS);
        long minutes = seconds/60;
        long hours = minutes/60;
        seconds = seconds-(60*minutes);
        minutes = minutes-(60*hours);

        return hours+":"+minutes+":"+seconds;
    }

}
