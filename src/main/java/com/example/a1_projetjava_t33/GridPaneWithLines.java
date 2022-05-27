package com.example.a1_projetjava_t33;

import javafx.beans.property.BooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

//Plateau de jeu
public class GridPaneWithLines {
    //Création des cellules
    private static StackPane createCell(BooleanProperty cellSwitch, String[][] sourceImage, int x, int y, String letter) {
        String cellule = letter + (x+1);
        //Création de la cellulle
        StackPane cell = new StackPane();

        //On place l'image dans la cellule
        Image img = new Image(sourceImage[x][y]);
        ImageView imgView = new ImageView(img);
        cell.getChildren().add(imgView);

        //Renvoie le nom de la cellule lorsque l'on clique dessus
        cell.setOnMouseClicked(e -> {
            cellSwitch.set(!cellSwitch.get());
            System.out.println(cellule);
        });
        //Créer un cercle lorsque l'on clique sur cette cellule
        Circle circle = new Circle(10, Color.CORNFLOWERBLUE);
        circle.visibleProperty().bind(cellSwitch);
        cell.getChildren().add(circle);

        //Récupération d'un style de cellule
        cell.getStyleClass().add("cell");
        return cell;
    }

    //Création de la grille de jeu
    static GridPane createGrid(BooleanProperty[][] switches) {

        //Initialisation de la taille du plateau
        int numCols = switches.length;
        int numRows = switches[0].length;
        //Création de la grille
        GridPane grid = new GridPane();
        //Mise en place des colonnes
        for (int x = 0; x < numCols; x++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }
        //Mise en place des lignes
        for (int y = 0; y < numRows; y++) {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }
        //Mise en place de l'emplacement
        for (int x = 0; x < numCols; x++) {
            for (int y = 0; y < numRows; y++) {
                String[][] sourceImage = new String[16][16];
                String letter = "";
                //Remplacement du numéro de la ligne par un numéro
                switch(y){
                    case 15:
                        letter = "A";
                        break;

                    case 14:
                        letter = "B";
                        break;

                    case 13:
                        letter = "C";
                        break;

                    case 12:
                        letter = "D";
                        break;

                    case 11:
                        letter = "E";
                        break;

                    case 10:
                        letter = "F";
                        break;

                    case 9:
                        letter = "G";
                        break;

                    case 8:
                        letter = "H";
                        break;

                    case 7:
                        letter = "I";
                        break;

                    case 6:
                        letter = "J";
                        break;

                    case 5:
                        letter = "K";
                        break;

                    case 4:
                        letter = "L";
                        break;

                    case 3:
                        letter = "M";
                        break;

                    case 2:
                        letter = "N";
                        break;

                    case 1:
                        letter = "O";
                        break;

                    case 0:
                        letter = "P";
                        break;
                }
                //On récupère l'adresse vers l'image associée à l'emplacement sur laquelle on se place
                sourceImage[x][y] =  GridPaneWithLines.class.getResource("Cases/" + letter + String.format("%d",x+1) +".jpg").toString();
                //On ajoute la cellule sur l'emplacement où on se trouve
                grid.add(createCell(switches[x][y], sourceImage, x, y, letter), x, y);



            }
        }
        //Récupération d'un style de grille
        grid.getStyleClass().add("grid");
        return grid;
    }

}
