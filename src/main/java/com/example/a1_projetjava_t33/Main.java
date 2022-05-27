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
        Menu javaFx = new Menu();
        javaFx.start(stage);

    }
}
