package com.example.a1_projetjava_t33;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Interface {
    Game game;

    public Interface(Game game) {
        this.game = game;
    }

    public void start(Stage stage) throws IOException {
        stage.setTitle("Riz co sheh");

        int numCols = 16 ;
        int numRows = 16 ;
        BooleanProperty[][] switches = new BooleanProperty[numCols][numRows];
        for (int x = 0 ; x < numCols ; x++) {
            for (int y = 0 ; y < numRows ; y++) {
                switches[x][y] = new SimpleBooleanProperty();
            }
        }


        BorderPane root = new BorderPane();

        root.setPadding(new Insets(15, 20, 10, 10));

        // TOP
        Button btnTop = CustomButtonFactory.createButton("J'ai trouvé un chemin ! ", "BOOM Timer");

        btnTop.setPadding(new Insets(10, 10, 10, 10));
        root.setTop(btnTop);
        // Set margin for top area.
        BorderPane.setMargin(btnTop, new Insets(10, 10, 10, 10));


        // LEFT
        Button btnLeft = new Button("Left");
        btnLeft.setPadding(new Insets(5, 5, 5, 5));
        root.setLeft(btnLeft);
        // Set margin for left area.
        BorderPane.setMargin(btnLeft, new Insets(10, 10, 10, 10));

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


        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add(new File("C:\\Users\\techi\\Documents\\GitHub\\A1_Projet-Java_T3-3\\src\\main\\resources\\com\\example\\a1_projetjava_t33\\grid-with-borders.css").toURI().toURL().toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}