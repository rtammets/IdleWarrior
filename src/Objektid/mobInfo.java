package Objektid;

/**
 * Created by R on 25/01/2017.
 */
public class mobInfo {


    //mobnr järgi: nimi mobLvl mobspeed elud dpsMin dpsMax xp gold isSpecial
    public int mobLvl, mobSpeed, dpsMin, dpsMax, mobXp, mobGold, elud;
    public String nimi;
    //public boolean isSpecial;
    //int health, int damage, int damageMax, int mobSpeed, String name, int mobXP, int mobGold
    mobInfo(int elud, int dpsMin, int dpsMax, int mobSpeed, String nimi, int mobXp, int mobGold, int mobLvl) {
        this.elud=elud;
        this.mobGold=mobGold;
        this.mobXp=mobXp;
        this.dpsMin=dpsMin;
        this.dpsMax=dpsMax;
        this.nimi=nimi;
        this.mobLvl=mobLvl;
        this.mobSpeed=mobSpeed;
    }
}
