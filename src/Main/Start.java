package Main;



/**
 *
 * See teeb mängu akna, kutsub Main funktsiooni.
 */
public class Start {

    public static void main(String[] args) {

        System.out.println("[i200] Idle Warrior mäng - Rasmus Tammets(rtammets) AK11");
            Main game = new Main("IDLE WARRIOR", 600, 400); //loo sellise nime ja kujuga aken
            game.start();//käivita mängu Main thread
            System.out.println("Käivitan mängu...");

    }
}
//////////////////////////////////////////////////////////////////TEST

/*
        Button playButton = new Button("Play");
        playButton.setOnAction(e -> this.pressed = true, Start());
        GridPane gridLayout = new GridPane();
        gridLayout.setPadding(new Insets(10,10,10,10));
        gridLayout.setVgap(0);
        gridLayout.setHgap(10);
        gridLayout.setConstraints(playButton,1,4);

    }

    public Start(){
        if (pressed = true) {*/
/*



        Button menuNupp2 = new Button("Quit");
        menuNupp2.setOnAction(event -> {Main.class.playing = false; window.close();});

        GridPane gridLayout = new GridPane();
        gridLayout.setPadding(new Insets(10,10,10,10));
        gridLayout.setVgap(0);
        gridLayout.setHgap(10);
        gridLayout.setConstraints(playButton,1,4);
        gridLayout.setConstraints(menuNupp2,1,6);
        gridLayout.setConstraints(tervitus,1,0);
        gridLayout.getChildren().addAll(playButton,menuNupp2,tervitus);


    //Menüü ülesehitus
        //nupud

        Button tagasiNupp = new Button("Tagasi");
        tagasiNupp.setOnAction(event -> stopLoop());
        Button ryndaNupp = new Button("ATTACK!");
        ryndaNupp.setOnAction(e-> vastane1.getHit(tegelane1.dmg));
        //esimene layout
        VBox layoutMenu = new VBox(); //vbox teeb tulba muutuja on pikslite vahe
        layoutMenu.getChildren().addAll(playButton,menuNupp2);
        //Peamenüü
        GridPane gridLayout = new GridPane();
        gridLayout.setPadding(new Insets(10,10,10,10));
        gridLayout.setVgap(0);
        gridLayout.setHgap(10);
        gridLayout.setConstraints(playButton,1,4);
        gridLayout.setConstraints(menuNupp2,1,6);
        gridLayout.setConstraints(tervitus,1,0);
        gridLayout.getChildren().addAll(playButton,menuNupp2,tervitus);
        menu = new Scene(gridLayout,250,100);
        //Mängumenüü
        GridPane layoutGame = new GridPane();
        layoutGame.setPadding(new Insets(0,30,30,0));
        layoutGame.setConstraints(gameCanvas, 0,0);
        layoutGame.setConstraints(tagasiNupp, 1,1);
        layoutGame.setConstraints(ryndaNupp,0,1);
        layoutGame.setConstraints(info,2,3);
        layoutGame.setConstraints(info1,2,4);
        layoutGame.setConstraints(info2,2,5);
        layoutGame.setConstraints(info3,2,6);
        //layoutGame.setConstraints();
        layoutGame.getChildren().addAll(tagasiNupp,ryndaNupp,gameCanvas,info,info1,info2,info3);
        game = new Scene(layoutGame, 800, 600);

        //Akna üldised parameetrid
        window.setTitle("Idle Warrior v"+version+" by Rasmus");
        window.setScene(menu); //määran default akna
        window.show(); //näita akent
}//main
 */