package com.example.a1_projetjava_t33;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Text;

import java.io.IOException;

//Menu du jeu
public class Menu {
    Game game;
    public static Integer nbplayer;

    public Menu(Game game) {
        this.game = game;
    }

    public Integer start(Stage stage) throws IOException {
        nbplayer = 1;

//--------------------------------Creation fenetre ------------------------------------------------
        stage.setTitle("Menu Principal");
        stage.setResizable(false);
        Pane root = new Pane();
        root.setPrefSize(760, 660);

        //Musique du menu
        String musicFile = "theme_song.mp3";
        AudioClip sound = new AudioClip(this.getClass().getResource("Sounds/"+ musicFile).toExternalForm());
        sound.setVolume(0.05);
        sound.play();

        //Son lors d'un clic
        String soundFile = "clic.mp3";
        AudioClip clic = new AudioClip(this.getClass().getResource("Sounds/"+ soundFile).toExternalForm());
        clic.setVolume(0.5);

        //Son lors d'une erreur
        String errorFile = "error.mp3";
        AudioClip erreur = new AudioClip(this.getClass().getResource("Sounds/"+ errorFile).toExternalForm());
        erreur.setVolume(0.5);

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
        btnplay.setOnAction(event -> {
                    Interface jeu = new Interface();
            try {
                clic.play();
                sound.stop();
                jeu.start(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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
        //Diminution du nombre de joueurs
        btnleft.setOnAction(event -> {
            if(nbplayer > 1) {
                clic.play();
                nbplayer = nbplayer - 1;
                center.setText(nbplayer.toString());
            }else{
                erreur.play();
            }
        });

        Button btnright = new Button(">");
        btnright.setTranslateX(460);// pixel par rapport au "mur" de gauche
        btnright.setTranslateY(550);// pixel par rapport au "plafond"
        btnright.setScaleX(3.5);
        btnright.setScaleY(3);
        root.getChildren().add(btnright);
        //Augmentation du nombre de joueurs
        btnright.setOnAction(event -> {
            if(nbplayer < 8) {
                clic.play();
                nbplayer = nbplayer + 1;
                center.setText(nbplayer.toString());
            }else{
                erreur.play();
            }

        });


//--------------------------------Construction de la fenetre ------------------------------------------------
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        return nbplayer;
    }

}

