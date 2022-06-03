package com.example.a1_projetjava_t33;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.File;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

//--------------------------------LEFT - NB PLAYERS + TURN WINS ------------------------------------------------

public class Players {
    //--------------------------------Tuile de la fenêtre ------------------------------------------------

    public static void PlayersPane(AtomicInteger usedToken, int nbToken, Button btnTop, File[] files, ImageView imgView, List<Integer> blacklist, BorderPane root) {
        //Mise en place de la liste de joueurs

        VBox leftBorderPane = new VBox();
        //Itération pour le nombre de joueurs
        for (int i = 1; i <= Menu.nbplayer; i++) {
            Dictionary<String, Integer> nbVictoires = new Hashtable<String, Integer>(); //Dictionnaire pour associé à chaque joueur son nombre de victoires
            final int[] count = {0};
            nbVictoires.put("Joueur " + i, count[0]);
            //Création d'un bouton
            Button btn = new Button("Joueur " + i + " : " + nbVictoires.get("Joueur " + i));
            Color playerColor = Color.color(Math.random(), Math.random(), Math.random());
            btn.setTextFill(playerColor);

            int finalI = i;
            final Integer[] decompte = {0};
            final Integer[] decomptebis = {0};
            AtomicInteger winner = new AtomicInteger();
            //A chaque fois qu'un joueur gagne une manche: il clique sur un bouton
            btn.setOnAction(event -> {
                //Ici on incrémente le score du joueur ayant gagné la manche
                count[0] = count[0] + 1;
                nbVictoires.put("Joueur " + finalI, count[0]);
                btn.setText("Joueur " + finalI + " : " + nbVictoires.get("Joueur " + finalI));
                //S'il reste des token la partie continue
                if (usedToken.get() < nbToken) {
                    Sounds.PlaySound(Sounds.TurnWinSound());
                    RandomToken.randomToken(nbToken, files, imgView, blacklist);
                    usedToken.set(usedToken.get() + 1);
                    //Sinon on fait le décompte des points et affiche le vainqueur
                } else {
                    Sounds.PlaySound(Sounds.VictorySound());
                    for (int k = 1; k < Menu.nbplayer; k++) {
                        decompte[0] = nbVictoires.get("Joueur " + k);
                        if (decompte[0] == null) {
                            decompte[0] = 0;
                        }
                        if (decompte[0] >= decomptebis[0]) {
                            winner.set(k);
                            decomptebis[0] = decompte[0];
                        }
                    }
                    btnTop.setText("VAINQUEUR : Joueur " + winner);
                    btnTop.setTextFill(Color.color(1, 0, 0));
                }

            });

            btn.setPadding(new Insets(5, 5, 5, 5));
            leftBorderPane.setMargin(btn, new Insets(10, 10, 10, 10));


            leftBorderPane.getChildren().add(btn);


        }

        root.setLeft(leftBorderPane);
    }
}
