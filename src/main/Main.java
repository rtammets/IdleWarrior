package main;

import java.util.Scanner;
//graafika impordid JavaFX java GUI
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Created by R on 02/10/2016.
 *
 * Player defineerida
 * Vastane defineerida
 * Input nupud defineerida
 * Kuvamine & menüü - JavaFX?
 *
 * Play loop (hoiab mängu töös, main loop).
 * Graphics loop (Draw GUI, vastased, mängija, kuulid, taust jne).
 * Kuva mängija, kuva Vastane
 * Spawn loop (loob vastaseid vastavalt areale, võtab  vastaste poolist infot).
 * Tekstikasti Loop + aeguv tekst
 * Game tickid - sekundite loendur
 * Input
 * Savegame
 * Area muutus
 * HP bar
 *
 * mõtteid:
 * Dropid(mouseover)
 * Uued spellud
 * Damage numbrid
 * Pood
 * Kõik hitid mingi knockbackiga, aka mingi lihtne physics
 */

public class Main extends Application{
    //Deklareerin muutujaid
    Button menuNupp1, menuNupp2;
    double version = 0.1; //versioon
    //launch(args) käivitab JavaFX app-i. Stage = Kogu aken, akna sisu = scene.
    public static void main(String[] args) {
            launch(args);
    }//psvm

    public void start(Stage primaryStage) throws Exception {
        //Menüü nupud
        menuNupp1 = new Button("Play");
        //
        menuNupp2 = new Button("Sule");
        //Menüü ülesehitus
        StackPane layout = new StackPane();
        layout.getChildren().addAll(menuNupp1,menuNupp2);
        Scene menu = new Scene(layout, 800, 600);
        //Akna seaded
        primaryStage.setTitle("Idle Warrior v"+version);
        primaryStage.setScene(menu); //määran default akna
        primaryStage.show(); //näita akent
    }//mainLoop

}//EOF
