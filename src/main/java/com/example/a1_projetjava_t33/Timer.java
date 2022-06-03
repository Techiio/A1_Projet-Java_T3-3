package com.example.a1_projetjava_t33;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.media.AudioClip;

//--------------------------------TOP - TIMER ------------------------------------------------

public class Timer {
    //--------------------------------Tuile de la fenêtre ------------------------------------------------

    public static void TimerPane(BorderPane root){
        FlowPane topBorderPane = new FlowPane();
        root.setTop(topBorderPane);

        //Bouton lorsqu'un joueur a trouvé un chemin
        Button btnTop = new Button("J'ai trouvé un chemin !");

        //Setup barre de progression
        ProgressBar bar = new ProgressBar(0);
        bar.setPrefWidth(500);

        bar.setPadding(new Insets(10, 10, 10, 10));
        //On créé une barre de progression signifiant le timer
        btnTop.setOnAction(event -> {
            Sounds.PlaySound(Sounds.ClicSound());
            launchProgressBar(btnTop, bar);
        });
        btnTop.setPadding(new Insets(10, 10, 10, 10));


        //On ajoute la partie haute à la toile
        topBorderPane.getChildren().add(btnTop);
        topBorderPane.getChildren().add(bar);
        BorderPane.setMargin(btnTop, new Insets(10, 10, 10, 10));
        BorderPane.setMargin(bar, new Insets(10, 10, 10, 10));
    }

    //--------------------------------Barre de progression ------------------------------------------------
    public static void launchProgressBar(Button btnTop, ProgressBar bar){
        btnTop.setText("Timer en cours :)");
        //Musique d'attente
        String musicFile = "elevator.mp3";
        AudioClip sound = new AudioClip(Timer.class.getResource("Sounds/"+ musicFile).toExternalForm());
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
}
