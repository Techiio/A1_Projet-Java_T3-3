package com.example.a1_projetjava_t33;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//Interface de jeu
public class Interface {
    ProgressBar bar;
    Button btnTop;
    AudioClip sound;

    //--------------------------------Lancement fenetre ------------------------------------------------
    public void start(Stage stage) throws IOException {
        stage.setTitle("Riz co sheh");

        //On donne la taille de notre plateau de jeu
        int numCols = 16;
        int numRows = 16;

        //On initialise les données du plateau
        BooleanProperty[][] switches = new BooleanProperty[numCols][numRows];
        for (int x = 0; x < numCols; x++) {
            for (int y = 0; y < numRows; y++) {
                switches[x][y] = new SimpleBooleanProperty();
            }
        }

        AtomicInteger usedToken = new AtomicInteger(1);
//--------------------------------Sons ------------------------------------------------
        //Son lors d'un clic
        String soundFile = "clic.mp3";
        AudioClip clic = new AudioClip(this.getClass().getResource("Sounds/"+ soundFile).toExternalForm());
        clic.setVolume(0.5);

        //Son lors d'une erreur
        String errorFile = "error.mp3";
        AudioClip erreur = new AudioClip(this.getClass().getResource("Sounds/"+ errorFile).toExternalForm());
        erreur.setVolume(0.5);

        //Son lors d'une manche gagnée
        String turnWinFile = "turn_win.mp3";
        AudioClip turnWin = new AudioClip(this.getClass().getResource("Sounds/"+ turnWinFile).toExternalForm());
        turnWin.setVolume(0.2);

        //Son lors d'une victoire
        String victoryFile = "victory.mp3";
        AudioClip victory = new AudioClip(this.getClass().getResource("Sounds/"+ victoryFile).toExternalForm());

        //On créer notre "toile"
        BorderPane root = new BorderPane();

        root.setPadding(new Insets(15, 20, 10, 10));

//--------------------------------TOP - REFLEXION ------------------------------------------------
        FlowPane topBorderPane = new FlowPane();
        root.setTop(topBorderPane);

        //Bouton lorsqu'un joueur a trouvé un chemin
        btnTop = new Button("J'ai trouvé un chemin !");
        //On créé une barre de progression signifiant le timer
        btnTop.setOnAction(event -> {
            clic.play();
            launchProgressBar();
        });
        btnTop.setPadding(new Insets(10, 10, 10, 10));

        bar = new ProgressBar(0);
        bar.setPrefWidth(500);

        bar.setPadding(new Insets(10, 10, 10, 10));

        //On ajoute la partie haute à la toile
        topBorderPane.getChildren().add(btnTop);
        topBorderPane.getChildren().add(bar);
        BorderPane.setMargin(btnTop, new Insets(10, 10, 10, 10));
        BorderPane.setMargin(bar, new Insets(10, 10, 10, 10));

//--------------------------------PLATEAU DE JEU ------------------------------------------------
        GridPane grid = GridPaneWithLines.createGrid(switches);
        StackPane panelPane = new StackPane(grid);
        root.setCenter(panelPane);
        BorderPane.setAlignment(panelPane, Pos.BOTTOM_CENTER);

//--------------------------------RIGHT - RANDOM TOKEN------------------------------------------------
        VBox rightBorderPane = new VBox();

        //Création du token aléatoire où il faut se rendre
        File dir = new File(this.getClass().getResource("Token/").toExternalForm().replaceAll("file:/","")); //Récupération du dossier avec la liste
        File[] files = dir.listFiles();//Création de la liste présente
        int nbToken = files.length;


        //Initialisation du champ image pour le token
        Image img = new Image(this.getClass().getResource("white.png").toExternalForm());
        ImageView imgView = new ImageView(img);

        rightBorderPane.getChildren().addAll(new Label("Destination : "), imgView );

        //Initialisation d'une blacklist: tokens déjà utilisés
        List<Integer> blacklist = new ArrayList();
        randomToken(nbToken, files, imgView, blacklist);
        root.setRight(rightBorderPane);



//--------------------------------LEFT - NB PLAYERS + TURNS WIN------------------------------------------------

        //Mise en place de la liste de joueurs

        VBox leftBorderPane = new VBox();
        //Itération pour le nombre de joueurs
        for(int i = 1; i <= Menu.nbplayer; i++) {
            Dictionary<String,Integer> nbVictoires = new Hashtable<String,Integer>(); //Dictionnaire pour associé à chaque joueur son nombre de victoires
            final int[] count = {0};
            nbVictoires.put("Joueur "+i, count[0]);
            //Création d'un bouton
            Button btn = new Button("Joueur "+ i + " : " + nbVictoires.get("Joueur "+i));
            Color playerColor = Color.color(Math.random(), Math.random(), Math.random());
            btn.setTextFill(playerColor);

            int finalI = i;
            final Integer[] decompte = {0};
            final Integer[] decomptebis = {0};
            AtomicInteger winner = new AtomicInteger();
            //A chaque fois qu'un joueur gagne une manche: il clique sur un bouton
            btn.setOnAction(event -> {
                //Ici on incrémente le score du joueur ayant gagné la manche
                count[0] = count[0] +1;
                nbVictoires.put("Joueur "+ finalI, count[0]);
                btn.setText("Joueur "+ finalI + " : " + nbVictoires.get("Joueur "+ finalI));
                //S'il reste des token la partie continue
                if(usedToken.get() < nbToken){
                    turnWin.play();
                    randomToken(nbToken, files, imgView, blacklist);
                    usedToken.set(usedToken.get() + 1);
                //Sinon on fait le décompte des points et affiche le vainqueur
                }else{
                    victory.play();
                    for(int k = 1; k < Menu.nbplayer; k++){
                        decompte[0] = nbVictoires.get("Joueur "+k);
                        decomptebis[0] = nbVictoires.get("Joueur "+(k+1));
                        if (decompte[0] == null){
                            decompte[0] = 0;
                        }
                        if (decomptebis[0] == null){
                            decomptebis[0] = 0;
                        }
                        if(decompte[0] <= decomptebis[0]){
                            winner.set(k + 1);
                        }else{
                            winner.set(k);
                        }
                    }
                    btnTop.setText("VAINQUEUR : Joueur "+ winner);
                    btnTop.setTextFill(Color.color(1, 0, 0));
                }

            });

            btn.setPadding(new Insets(5, 5, 5, 5));
            leftBorderPane.setMargin(btn, new Insets(10, 10, 10, 10));


            leftBorderPane.getChildren().add(btn);
        }

        root.setLeft(leftBorderPane);

//--------------------------------BOTTOM  - MOVES LIST ------------------------------------------------
        HBox bottomBorderPane = new HBox();

        List<String> movesList = new ArrayList<String>();
        movesList.add("Zebi");
        movesList.add("tam");


        bottomBorderPane.getChildren().addAll(new Label("Chemin parcouru : \n"), new Label(movesList.toString()));

        root.setBottom(bottomBorderPane);

//--------------------------------Creation fenetre ------------------------------------------------
        Scene scene = new Scene(root, 760, 660);
        scene.getStylesheets().add(getClass().getResource("grid-with-borders.css").toString());
        //Création de la fenêtre
        stage.setScene(scene);
        stage.show();

    }

    //--------------------------------Barre de progression ------------------------------------------------
    public void launchProgressBar(){
        btnTop.setText("Timer en cours :)");
        //Musique d'attente
        String musicFile = "elevator.mp3";
        AudioClip sound = new AudioClip(this.getClass().getResource("Sounds/"+ musicFile).toExternalForm());
        sound.stop();
        sound.play();
        //Evolution de la barre de progression
        Task task = new Task<Void>() {
            @Override public Void call() {
                final int max = 100000000;
                for (int i = 1; i <= max; i++) {
                    updateProgress(i, max);
                }
                //Evenement lorsque la barre a fini sa progression
                Platform.runLater(new Runnable(){
                    @Override
                    public void run() {
                        btnTop.setText("Fin de la réflexion !");
                        sound.stop();

                    }
                });
                return null;
            }
        };
        bar.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
    }

    //--------------------------------Mise à jour des tokens ------------------------------------------------
    public static List<Integer> randomToken(int nbToken, File[] files, ImageView imgView, List<Integer> blacklist) {

        //Token aléatoire
        int randomIndex = (int) (Math.random() * nbToken);

        //Ajout à la blacklist pour ne pas le réutiliser: échec d'implémentation
        blacklist.add(randomIndex);

        //Récupération du lien vers le token
        String token = files[randomIndex].getAbsolutePath();

        //Affichage de la nouvelle image liée au token
        Image Newimg = new Image(token);
        imgView.setImage(Newimg);

        imgView.setFitWidth(50);
        imgView.setFitHeight(50);

        return blacklist;
    }

}
