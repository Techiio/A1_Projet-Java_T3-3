package com.example.a1_projetjava_t33;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
//--------------------------------RIGHT - RANDOM TOKEN ------------------------------------------------
//Apparition d'un token de destination random et ajout à la blacklist pour ne pas retomber dessus (non fonctionnel)

public class RandomToken {
    //--------------------------------Différentes variables à retourner ------------------------------------------------
    static class Variables {
        public static int nbToken;
        public static File[] files;
        public static ImageView imgView;
        public static List<Integer> blacklist;

        Variables(int nbToken, File[] files, ImageView imgView, List<Integer> blacklist) {
            this.nbToken = nbToken;
            this.files = files;
            this.imgView = imgView;
            this.blacklist = blacklist;
        }

    }

    //--------------------------------Toile de la partie droite ------------------------------------------------

    public static Variables RandomTokenPane(BorderPane root){
        VBox rightBorderPane = new VBox();

        //Création du token aléatoire où il faut se rendre
        File dir = new File(RandomToken.class.getResource("Token/").toExternalForm().replaceAll("file:/","")); //Récupération du dossier avec la liste
        File[] files = dir.listFiles();//Création de la liste présente
        int nbToken = files.length;
        //Initialisation du champ image pour le token
        Image img = new Image(RandomToken.class.getResource("white.png").toExternalForm());
        ImageView imgView = new ImageView(img);
        //Initialisation d'une blacklist: tokens déjà utilisés
        List<Integer> blacklist = new ArrayList();
        randomToken(nbToken, files, imgView, blacklist);

        //Ajout à la fenêtre
        rightBorderPane.getChildren().addAll(new Label("Destination : "), imgView );
        root.setRight(rightBorderPane);

        return new Variables(nbToken, files, imgView, blacklist);
    }

    //--------------------------------Mise à jour des tokens et tirage au sort------------------------------------------------
    public static List<Integer> randomToken(int nbToken, File[] files, ImageView imgView, List<Integer> blacklist) {

        //Token aléatoire
        int randomIndex = (int) (Math.random() * nbToken);

        //Ajout à la blacklist pour ne pas le réutiliser: échec d'implémentation
        blacklist.add(randomIndex);

        //Récupération du lien vers le token
        String token = files[randomIndex].getAbsolutePath();

        //Affichage de la nouvelle image liée au token
        Image Newimg = new Image(token);
        imgView.setImage(Newimg);

        imgView.setFitWidth(50);
        imgView.setFitHeight(50);

        return blacklist;
    }
}
