package com.example.a1_projetjava_t33;


import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Game game = new Game();
        game.playGame();
        Interface javaFx = new Interface(game);
        javaFx.start(stage);
    }
}