package com.example.a1_projetjava_t33;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static com.example.a1_projetjava_t33.RandomToken.Variables.*;
import static com.example.a1_projetjava_t33.Timer.Variable.btnTop;

//--------------------------------Interface de jeu ------------------------------------------------
public class Interface {

    //--------------------------------Lancement fenêtre ------------------------------------------------
    public void start(Stage stage) throws IOException {
        //--------------------------------Création de la fenêtre ------------------------------------------------
        stage.setTitle("Fruit Rangers");

        //On donne la taille de notre plateau de jeu
        int numCols = 16;
        int numRows = 16;

        //On initialise les données du plateau de jeu
        BooleanProperty[][] switches = new BooleanProperty[numCols][numRows];
        for (int x = 0; x < numCols; x++) {
            for (int y = 0; y < numRows; y++) {
                switches[x][y] = new SimpleBooleanProperty();
            }
        }

        //Setup du plateau: premier token est donc utilisé
        AtomicInteger usedToken = new AtomicInteger(1);

        //On crée notre "toile"
        BorderPane root = new BorderPane();

        root.setPadding(new Insets(15, 20, 10, 10));

        Scene scene = new Scene(root, 760, 660);
        scene.getStylesheets().add(getClass().getResource("grid-with-borders.css").toString());

        //Création de la fenêtre
        stage.setScene(scene);
        stage.show();

//--------------------------------TOP - TIMER ------------------------------------------------
        Timer.TimerPane(root);

//--------------------------------CENTER - PLATEAU DE JEU ------------------------------------------------
        Gameboard.GameboardPane(root, switches);

//--------------------------------RIGHT - RANDOM TOKEN------------------------------------------------
        RandomToken.RandomTokenPane(root);

//--------------------------------LEFT - NB PLAYERS + TURNS WIN------------------------------------------------
        Players.PlayersPane(usedToken, nbToken, btnTop, files, imgView, blacklist, root);


//--------------------------------BOTTOM  - MOVES LIST ------------------------------------------------
        MovesList.MovesListPane(root);


    }

}
