package Main;



import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static Main.Seisud.MangKaib.mangija;


/**
 *
 * See teeb mängu akna, kutsub Main funktsiooni.
 */
public class Start extends Application{


    double version = 0.5; //versioon
    Stage window;// loon Stage window, hiljem omistan väärtuse primaryStage
    Scene gamescene, menu;
    //    launch(args) käivitab JavaFX app-i. Stage = Kogu aken, akna sisu = scene.

    Main game = new Main("Idle Warrior", 800, 600);
    Label tervitus = new Label("Welcome to Idle Warrior" +"\nControls:\nSpace - shoot\nArrow keys - move between areas\n ");
    TextField tekstiVali = new TextField();
    public static String nameHolder = new String();


    public static void main(String[] args) {
        System.out.println("[i200] Idle Warrior mäng - Rasmus Tammets(rtammets) AK11");
        launch(args);
    }//psvm
        public void alusta (){
            game.start();
            nameHolder = tekstiVali.getText();
            window.close();
        }

        public void start(Stage primaryStage)throws Exception{
        window = primaryStage;
        //Menüü ülesehitus
        //nupud
        Button playButton = new Button("Play");
        playButton.setOnAction(e -> alusta());
        Button menuNupp2 = new Button("Quit");
        menuNupp2.setOnAction(event -> window.close());
        tekstiVali.setPromptText("Sinu nimi");
        //Button tagasiNupp = new Button("Tagasi");
        //tagasiNupp.setOnAction(event -> window.setScene(menu));

            //esimene layout
            VBox layoutMenu = new VBox(); //vbox teeb tulba muutuja on pikslite vahe
            layoutMenu.getChildren().addAll(playButton,menuNupp2);
            //Peamenüü
            GridPane gridLayout = new GridPane();
            gridLayout.setPadding(new Insets(10,10,10,10));
            gridLayout.setVgap(0);
            gridLayout.setHgap(10);
            gridLayout.setConstraints(tekstiVali, 1,1);
            gridLayout.setConstraints(playButton,1,4);
            gridLayout.setConstraints(menuNupp2,1,6);
            gridLayout.setConstraints(tervitus,1,0);
            gridLayout.getChildren().addAll(playButton,menuNupp2,tervitus,tekstiVali);
            menu = new Scene(gridLayout,250,150);
            //Akna seaded
        window.setTitle("Idle Warrior 2");
        window.setScene(menu); //määran default akna
        window.show(); //näita akent
    }//mainLoop
}
/*    public static void main(String[] args) {
        System.out.println("[i200] Idle Warrior mäng - Rasmus Tammets(rtammets) AK11");
        Main game = new Main("IDLE WARRIOR", 600, 400); //loo sellise nime ja kujuga aken
        game.start();//käivita mängu Main thread
    }
}*/