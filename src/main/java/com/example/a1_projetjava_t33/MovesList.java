package com.example.a1_projetjava_t33;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;
//--------------------------------BOTTOM - MOVES LIST ------------------------------------------------
//Liste des déplacements des robots qui se met à jour et apparaît en bas de fenêtre (non fonctionnel)
//Possibilité de seulement voir dans le terminal la case sur laquelle on a cliqué
public class MovesList {
    //--------------------------------Toile de fenêtre ------------------------------------------------

    public static void MovesListPane(BorderPane root){
        HBox bottomBorderPane = new HBox();

        List<String> movesList = new ArrayList<String>();
        movesList.add("Zebi");
        movesList.add("tam");


        bottomBorderPane.getChildren().addAll(new Label("Chemin parcouru : \n"), new Label(movesList.toString()));

        root.setBottom(bottomBorderPane);
    }
}
