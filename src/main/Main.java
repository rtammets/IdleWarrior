package main;

import java.util.Scanner;
//graafika impordid JavaFX java GUI
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
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
    double version = 0.1; //versioon
    Stage window;// loon Stage window, hiljem omistan väärtuse primaryStage
    Scene game, menu;
    //launch(args) käivitab JavaFX app-i. Stage = Kogu aken, akna sisu = scene.

    public static void main(String[] args) {
        System.out.println("[i200] Idle Warrior mäng - Rasmus Tammets(rtammets) AK11");
        launch(args);
    }//psvm

    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Label tervitus = new Label("Welcome to Idle Warrior v"+version);
        //Menüü ülesehitus

        //nupud
        Button playButton = new Button("Play");
        playButton.setOnAction(e -> window.setScene(game));
        Button menuNupp2 = new Button("Quit");
        menuNupp2.setOnAction(event -> window.close());
        Button menuNupp3 = new Button("Tagasi");
        menuNupp3.setOnAction(event -> window.setScene(menu));

        //esimene layout
        VBox layoutMenu = new VBox(); //vbox teeb tulba muutuja on pikslite vahe
        layoutMenu.getChildren().addAll(playButton,menuNupp2);
        //gridPane
        GridPane gridLayout = new GridPane();
        gridLayout.setPadding(new Insets(10,10,10,10));
        gridLayout.setVgap(0);
        gridLayout.setHgap(10);
        gridLayout.setConstraints(playButton,1,4);
        gridLayout.setConstraints(menuNupp2,1,6);
        gridLayout.setConstraints(tervitus,1,0);
        gridLayout.getChildren().addAll(playButton,menuNupp2,tervitus);
        menu = new Scene(gridLayout,250,100);
        //BorderPane borderPane = new BorderPane();
        //borderPane.setTop(tervitus);
        //borderPane.setCenter(layoutMenu);
        //menu = new Scene(borderPane, 300, 300);
        //teine layout
        StackPane layoutGame = new StackPane();
        layoutGame.getChildren().addAll(menuNupp3);
        game = new Scene(layoutGame, 800, 600);




        //Akna seaded
        window.setTitle("Idle Warrior v"+version+" by Rasmus");
        window.setScene(menu); //määran default akna
        window.show(); //näita akent
    }//mainLoop

}//EOF
