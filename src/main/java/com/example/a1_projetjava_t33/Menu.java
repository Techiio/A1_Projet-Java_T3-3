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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Menu {
    Game game;
    private Integer nbplayer;

    public Menu(Game game) {
        this.game = game;
    }

    public void start(Stage stage) throws IOException {

        nbplayer = 0;

//--------------------------------Creation fenetre ------------------------------------------------
        stage.setTitle("Menu Principal");
        stage.setResizable(false);
        Pane root = new Pane();
        root.setPrefSize(745, 750);

//--------------------------------Image de fond ------------------------------------------------
        Image backimg = new Image("https://tetu.com/wp-content/uploads/2021/09/powerrangers-1280x720.jpeg");
        BackgroundImage bImg = new BackgroundImage(backimg,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bGround = new Background(bImg);
        root.setBackground(bGround);

        root.getChildren().addAll();
//--------------------------------Bouton JOUER ------------------------------------------------
        Button btnplay = new Button("Jouer");
        btnplay.setTranslateX(350);// pixel par rapport au "mur" de gauche
        btnplay.setTranslateY(200);// pixel par rapport au "plafond"
        btnplay.setScaleX(4);
        btnplay.setScaleY(3.5);
        root.getChildren().add(btnplay);

//--------------------------------Selection nombre de joueurs ------------------------------------------------
        Text txtnbjrs = new Text("Nombre de Joueurs");
        txtnbjrs.setTranslateX(230);// pixel par rapport au "mur" de gauche
        txtnbjrs.setTranslateY(500);// pixel par rapport au "plafond"
        txtnbjrs.setStyle("-fx-font-weight: bold;-fx-font-size: 30;-fx-stroke: black;-fx-stroke-width: 2");
        txtnbjrs.setFill(Color.WHITE);
        root.getChildren().add(txtnbjrs);

        Button center = new Button(nbplayer.toString());
        center.setTranslateX(360);// pixel par rapport au "mur" de gauche
        center.setTranslateY(550);// pixel par rapport au "plafond"
        center.setScaleX(3.5);
        center.setScaleY(3);
        root.getChildren().add(center);

        Button btnleft = new Button("<");
        btnleft.setTranslateX(260);// pixel par rapport au "mur" de gauche
        btnleft.setTranslateY(550);// pixel par rapport au "plafond"
        btnleft.setScaleX(3.5);
        btnleft.setScaleY(3);
        root.getChildren().add(btnleft);
        btnleft.setOnAction(event -> {
            nbplayer  = nbplayer - 1;
            center.setText(nbplayer.toString());
        });

        Button btnright = new Button(">");
        btnright.setTranslateX(460);// pixel par rapport au "mur" de gauche
        btnright.setTranslateY(550);// pixel par rapport au "plafond"
        btnright.setScaleX(3.5);
        btnright.setScaleY(3);
        root.getChildren().add(btnright);
        btnright.setOnAction(event -> {
            nbplayer = nbplayer + 1;
            center.setText(nbplayer.toString());

        });


//--------------------------------Construction de la fenetre ------------------------------------------------
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}

