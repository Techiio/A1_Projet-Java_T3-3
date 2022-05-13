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


public class Interface {
    ProgressBar bar;
    Button btnTop;
    AudioClip sound;

    public void start(Stage stage) throws IOException {
        stage.setTitle("Riz co sheh");

        int numCols = 16;
        int numRows = 16;
        BooleanProperty[][] switches = new BooleanProperty[numCols][numRows];
        for (int x = 0; x < numCols; x++) {
            for (int y = 0; y < numRows; y++) {
                switches[x][y] = new SimpleBooleanProperty();
            }
        }

        BorderPane root = new BorderPane();

        root.setPadding(new Insets(15, 20, 10, 10));

        // TOP
        FlowPane topBorderPane = new FlowPane();
        root.setTop(topBorderPane);

        btnTop = new Button("J'ai trouvé un chemin !");
        btnTop.setOnAction(event -> {
            launchProgressBar();
        });
        btnTop.setPadding(new Insets(10, 10, 10, 10));

        bar = new ProgressBar(0);
        bar.setPrefWidth(500);

        bar.setPadding(new Insets(10, 10, 10, 10));


        topBorderPane.getChildren().add(btnTop);
        topBorderPane.getChildren().add(bar);
        BorderPane.setMargin(btnTop, new Insets(10, 10, 10, 10));
        BorderPane.setMargin(bar, new Insets(10, 10, 10, 10));




        // LEFT
        Button btnLeft = new Button("Left");
        btnLeft.setPadding(new Insets(5, 5, 5, 5));
        root.setLeft(btnLeft);
        // Set margin for left area.
        BorderPane.setMargin(btnLeft, new Insets(10, 10, 10, 10));

        String joueur = "";
        for(int i = 1; i <= Menu.nbplayer; i++) {
            joueur = joueur + "Joueur " + i + "\n";
        }

        Label label = new Label("Joueurs : \n " + joueur);
        root.setLeft(label);

        BorderPane.setMargin(label, new Insets(20, 20, 20, 20));

        // CENTER
        GridPane grid = GridPaneWithLines.createGrid(switches);
        StackPane panelPane = new StackPane(grid);
        root.setCenter(panelPane);
        // Alignment.
        BorderPane.setAlignment(panelPane, Pos.BOTTOM_CENTER);

        // RIGHT
        Button btnRight = new Button("Right");
        btnRight.setPadding(new Insets(5, 5, 5, 5));
        root.setRight(btnRight);
        // Set margin for right area.
        BorderPane.setMargin(btnRight, new Insets(10, 10, 10, 10));

        // BOTTOM
        Button btnBottom = new Button("Bottom");
        btnBottom.setPadding(new Insets(5, 5, 5, 5));
        root.setBottom(btnBottom);
        // Alignment.
        BorderPane.setAlignment(btnBottom, Pos.TOP_RIGHT);

        // Set margin for bottom area.
        BorderPane.setMargin(btnBottom, new Insets(10, 10, 10, 10));


        Scene scene = new Scene(root, 745, 690);
        scene.getStylesheets().add(getClass().getResource("grid-with-borders.css").toString());

        stage.setScene(scene);
        stage.show();

    }

    public void launchProgressBar(){
        btnTop.setText("Timer en cours :)");
        String musicFile = "elevator.mp3";
        AudioClip sound = new AudioClip(this.getClass().getResource("Sounds/"+ musicFile).toExternalForm());
        sound.play();
        Task task = new Task<Void>() {
            @Override public Void call() {
                final int max = 1000000000;
                for (int i = 1; i <= max; i++) {
                    updateProgress(i, max);
                }
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
