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
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Interface de jeu
public class Interface {
    ProgressBar bar;
    Button btnTop;
    AudioClip sound;

    //Lancement de la fenêtre
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

        //On créer notre "toile"
        BorderPane root = new BorderPane();

        root.setPadding(new Insets(15, 20, 10, 10));

        //Partie haute de la "toile"
        FlowPane topBorderPane = new FlowPane();
        root.setTop(topBorderPane);
        //Bouton lorsqu'un joueur a trouvé un chemin
        btnTop = new Button("J'ai trouvé un chemin !");
        //On créé une barre de progression signifiant le timer
        btnTop.setOnAction(event -> {
            launchProgressBar();
        });
        btnTop.setPadding(new Insets(10, 10, 10, 10));

        bar = new ProgressBar(0);
        bar.setPrefWidth(500);

        bar.setPadding(new Insets(10, 10, 10, 10));

        //On ajoute les deux composants à la partie haute de la "toile"
        topBorderPane.getChildren().add(btnTop);
        topBorderPane.getChildren().add(bar);
        BorderPane.setMargin(btnTop, new Insets(10, 10, 10, 10));
        BorderPane.setMargin(bar, new Insets(10, 10, 10, 10));




        //Partie gauche de la toile
        Button btnLeft = new Button("Left");
        btnLeft.setPadding(new Insets(5, 5, 5, 5));
        root.setLeft(btnLeft);
        BorderPane.setMargin(btnLeft, new Insets(10, 10, 10, 10));

        //Mise en place de la liste de joueurs
        String joueur = "";
        for(int i = 1; i <= Menu.nbplayer; i++) {
            joueur = joueur + "Joueur " + i + "\n";
        }

        Label label = new Label("Joueurs : \n " + joueur);
        root.setLeft(label);

        BorderPane.setMargin(label, new Insets(20, 20, 20, 20));

        //Centre de la "toile": plateau de jeu
        GridPane grid = GridPaneWithLines.createGrid(switches);
        StackPane panelPane = new StackPane(grid);
        root.setCenter(panelPane);
        BorderPane.setAlignment(panelPane, Pos.BOTTOM_CENTER);

        //Partie droite de la "toile"
        Button btnRight = new Button("Right");
        btnRight.setPadding(new Insets(5, 5, 5, 5));
        root.setRight(btnRight);
        BorderPane.setMargin(btnRight, new Insets(10, 10, 10, 10));

        //Partie basse de la "toile"
        Button btnBottom = new Button("Bottom");
        btnBottom.setPadding(new Insets(5, 5, 5, 5));
        root.setBottom(btnBottom);
        BorderPane.setAlignment(btnBottom, Pos.TOP_RIGHT);

        BorderPane.setMargin(btnBottom, new Insets(10, 10, 10, 10));

        //Création de la scène
        Scene scene = new Scene(root, 745, 690);
        scene.getStylesheets().add(getClass().getResource("grid-with-borders.css").toString());
        //Création de la fenêtre
        stage.setScene(scene);
        stage.show();

    }
    //Barre de progression
    public void launchProgressBar(){
        btnTop.setText("Timer en cours :)");
        //Musique d'attente
        String musicFile = "elevator.mp3";
        AudioClip sound = new AudioClip(this.getClass().getResource("Sounds/"+ musicFile).toExternalForm());
        sound.play();
        //Evolution de la barre de progression
        Task task = new Task<Void>() {
            @Override public Void call() {
                final int max = 1000000000;
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

}
