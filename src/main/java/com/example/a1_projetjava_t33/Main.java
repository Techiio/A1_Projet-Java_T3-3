package com.example.a1_projetjava_t33;


import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        //Lancement du jeu
        Game game = new Game();
        game.playGame();
        //Lancement de l'interface
        Menu javaFx = new Menu(game);
        javaFx.start(stage);

    }
}
