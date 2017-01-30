package Kuva;

import Main.Sisendid.hiireSisend;

import javax.swing.*;
import java.awt.*;

/**
 *
 * Graafika põhiklass. Kasutan JFrame
 */
public class Kuva {
    //loon JFRame akna tegemise jaoks vajalikud muutujad
        private JFrame frame;
        private String aknaNimi;
        private int aknaWidth, aknaHeight;
        private Canvas canvas; //siia sisse hakkan kuvama mänguelemente.
    //TEST

    /////

    public Kuva(String aknaTitle, int aknaWidth, int aknaHeight){
        this.aknaNimi = aknaTitle;
        this.aknaWidth = aknaWidth;
        this.aknaHeight = aknaHeight;
        looKuva(); //tekitame funktsioonile söödeetud infoga uue akna.
    }

    private void looKuva(){
        frame = new JFrame(aknaNimi);//pealkirja määramine
        frame.setSize(aknaWidth,aknaHeight);//akna suuruse määramine
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Mängu "viisakas" sulgemiskäitumine

        //
        //frame.setLayout(new FlowLayout());
        //JButton javaNupp = new JButton("butts");
        //frame.add(javaNupp);
        //
        frame.setResizable(false); //akna suurust ei saa muuta
        frame.setLocationRelativeTo(null); //uus aken tekib alati ekraani keskele
        frame.setVisible(true); //muudan akna nähtavaks
        canvas = new Canvas(); //uue kuva sisse canvas mänguelementidele
        canvas.addMouseListener(new hiireSisend());
        canvas.setPreferredSize(new Dimension(aknaWidth,aknaHeight));//akna suurus
        canvas.setMaximumSize(new Dimension(aknaWidth,aknaHeight));//akna max suurus
        canvas.setMinimumSize(new Dimension(aknaWidth,aknaHeight));//akna min suurus
        canvas.setFocusable(false); //et mäng saaks oma seise jälgida
        frame.add(canvas);
//        frame.add(canvas); //liidan jframe akna sisse oma canvase
        frame.pack();
    }

    public Canvas getCanvas(){
        return canvas;
    }
    public JFrame getFrame(){ return frame; }
}
