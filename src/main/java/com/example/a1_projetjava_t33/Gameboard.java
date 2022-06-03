package com.example.a1_projetjava_t33;


import javafx.beans.property.BooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.concurrent.atomic.AtomicInteger;

//--------------------------------CENTER - PLATEAU DE JEU ------------------------------------------------
public class Gameboard {

    //--------------------------------Création de la toile du plateau ------------------------------------------------
    public static void GameboardPane(BorderPane root, BooleanProperty[][] switches){
        GridPane grid = Gameboard.createGrid(switches);
        StackPane panelPane = new StackPane(grid);
        root.setCenter(panelPane);
        BorderPane.setAlignment(panelPane, Pos.BOTTOM_CENTER);
    }


    //--------------------------------Création des cellules ------------------------------------------------
    private static StackPane createCell(BooleanProperty cellSwitch, String[][] sourceImage, int x, int y, String letter) {
        String cellule = letter + (x + 1);
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

        //Récupération d'un style de cellule
        cell.getStyleClass().add("cell");
        return cell;
    }

    //--------------------------------Création de la grille de jeu ------------------------------------------------
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
                switch (y) {
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
                sourceImage[x][y] = Gameboard.class.getResource("Cases/" + letter + String.format("%d", x + 1) + ".jpg").toString();
                //On ajoute la cellule sur l'emplacement où on se trouve
                grid.add(createCell(switches[x][y], sourceImage, x, y, letter), x, y);


            }
        }
//Apparition des robots dans la grille de jeu avec leurs coordonnées (les autres robots ne bougent pas donc sont déclarés plus bas)
        Image robot1 = new Image(Gameboard.class.getResource("Power rangers/Force-verte.png").toString());
        ImageView imgView1 = new ImageView(robot1);
        imgView1.setImage(robot1);
        imgView1.setFitWidth(36);
        imgView1.setFitHeight(36);
        AtomicInteger posx = new AtomicInteger(2);
        AtomicInteger posy = new AtomicInteger(2);
        grid.setConstraints(imgView1, 2, 2);
//Activation du déplacement du robot quand on lui clique dessus
        imgView1.setOnMouseClicked(e -> {
//définition des contraintes de déplacement pour faire apparaitre ou non les boutons directionnels en fonction de la position du robot sur le plateau
// ex: ne pas faire apparaitre le bouton de déplacement vers le haut si le robot se situe déjà sur la partie haute du plateau
            
//Bord gauche
            if (posx.get() == 0 && posy.get() != 0 && posy.get() != 15) {
                Button up = new Button("^");
                grid.add(up, posx.get(), posy.get() - 1);
                Button down = new Button("v");
                grid.add(down, posx.get(), posy.get() + 1);
                Button right = new Button(">");
                grid.add(right, posx.get() + 1, posy.get());
                up.setOnAction(event -> {
                    moveUp(grid, imgView1, posx.get(), posy.get());
                    posy.set(0);
                    grid.getChildren().remove(up);
                    grid.getChildren().remove(down);
                    grid.getChildren().remove(right);
                });
                down.setOnAction(event -> {
                    moveDown(grid, imgView1, posx.get(), posy.get());
                    posy.set(15);
                    grid.getChildren().remove(up);
                    grid.getChildren().remove(down);
                    grid.getChildren().remove(right);
                });
                right.setOnAction(event -> {
                    moveRight(grid, imgView1, posx.get(), posy.get());
                    posx.set(15);
                    grid.getChildren().remove(up);
                    grid.getChildren().remove(down);
                    grid.getChildren().remove(right);
                });
//Coin Nord Ouest                 
            } else if (posx.get() == 0 && posy.get()==0) {
                Button down = new Button("v");
                grid.add(down, posx.get(), posy.get() + 1);
                Button right = new Button(">");
                grid.add(right, posx.get() + 1, posy.get());
                down.setOnAction(event -> {
                    moveDown(grid, imgView1, posx.get(), posy.get());
                    posy.set(15);
                    grid.getChildren().remove(down);
                    grid.getChildren().remove(right);
                });
                right.setOnAction(event -> {
                    moveRight(grid, imgView1, posx.get(), posy.get());
                    posx.set(15);
                    grid.getChildren().remove(down);
                    grid.getChildren().remove(right);
                });
// Coin Nord Est                
            } else if ( posx.get() == 0 && posy.get() == 15) {
                Button up = new Button("^");
                grid.add(up, posx.get(), posy.get() - 1);
                Button right = new Button(">");
                grid.add(right, posx.get() + 1, posy.get());
                up.setOnAction(event -> {
                    moveUp(grid, imgView1, posx.get(), posy.get());
                    posy.set(0);
                    grid.getChildren().remove(up);
                    grid.getChildren().remove(right);
                });
                right.setOnAction(event -> {
                    moveRight(grid, imgView1, posx.get(), posy.get());
                    posx.set(15);
                    grid.getChildren().remove(up);
                    grid.getChildren().remove(right);
                });
// Coin Sud Ouest                
            } else if (posx.get() == 15 && posy.get() == 0) {
                Button down = new Button("v");
                grid.add(down, posx.get(), posy.get() + 1);
                Button left = new Button("<");
                grid.add(left, posx.get() - 1, posy.get());
                down.setOnAction(event -> {
                    moveDown(grid, imgView1, posx.get(), posy.get());
                    posy.set(15);
                    grid.getChildren().remove(down);
                    grid.getChildren().remove(left);
                });
                left.setOnAction(event -> {
                    moveLeft(grid, imgView1, posx.get(), posy.get());
                    posx.set(0);
                    grid.getChildren().remove(down);
                    grid.getChildren().remove(left);
                });
 // Coin Sud Est               
            } else if (posx.get() == 15 && posy.get() == 15) {
                Button up = new Button("^");
                grid.add(up, posx.get(), posy.get() - 1);
                Button left = new Button("<");
                grid.add(left, posx.get() - 1, posy.get());
                up.setOnAction(event -> {
                    moveUp(grid, imgView1, posx.get(), posy.get());
                    posy.set(0);
                    grid.getChildren().remove(up);
                    grid.getChildren().remove(left);
                });
                left.setOnAction(event -> {
                    moveLeft(grid, imgView1, posx.get(), posy.get());
                    posx.set(0);
                    grid.getChildren().remove(up);
                    grid.getChildren().remove(left);
                });
// Bord Droit
            } else if (posx.get() == 15 && posy.get() != 0 && posy.get() != 15) {
                Button up = new Button("^");
                grid.add(up, posx.get(), posy.get() - 1);
                Button down = new Button("v");
                grid.add(down, posx.get(), posy.get() + 1);
                Button left = new Button("<");
                grid.add(left, posx.get() - 1, posy.get());
                up.setOnAction(event -> {
                    moveUp(grid, imgView1, posx.get(), posy.get());
                    posy.set(0);
                    grid.getChildren().remove(up);
                    grid.getChildren().remove(down);
                    grid.getChildren().remove(left);
                });
                down.setOnAction(event -> {
                    moveDown(grid, imgView1, posx.get(), posy.get());
                    posy.set(15);
                    grid.getChildren().remove(up);
                    grid.getChildren().remove(down);
                    grid.getChildren().remove(left);
                });
                left.setOnAction(event -> {
                    moveLeft(grid, imgView1, posx.get(), posy.get());
                    posx.set(0);
                    grid.getChildren().remove(up);
                    grid.getChildren().remove(down);
                    grid.getChildren().remove(left);
                });
//Bord Haut
            }else if (posy.get() == 0 && posx.get() != 0 && posx.get() != 15){
                    Button down = new Button("v");
                    grid.add(down, posx.get(), posy.get() +1);
                    Button left = new Button("<");
                    grid.add(left, posx.get() -1, posy.get());
                    Button right = new Button(">");
                    grid.add(right, posx.get() +1, posy.get());
                    down.setOnAction(event -> {
                        moveDown(grid, imgView1, posx.get(), posy.get());
                        posy.set(15);
                        grid.getChildren().remove(down);
                        grid.getChildren().remove(left);
                        grid.getChildren().remove(right);
                    });
                    left.setOnAction(event -> {
                        moveLeft(grid, imgView1, posx.get(), posy.get());
                        posx.set(0);
                        grid.getChildren().remove(down);
                        grid.getChildren().remove(left);
                        grid.getChildren().remove(right);
                    });
                    right.setOnAction(event -> {
                        moveRight(grid, imgView1, posx.get(), posy.get());
                        posx.set(15);
                        grid.getChildren().remove(down);
                        grid.getChildren().remove(left);
                        grid.getChildren().remove(right);
                    });

//Bord Bas

            } else if(posy.get() == 15 && posx.get() != 0 && posx.get() != 15){
                Button up = new Button("^");
                grid.add(up, posx.get(), posy.get() -1);
                Button left = new Button("<");
                grid.add(left, posx.get() -1, posy.get());
                Button right = new Button(">");
                grid.add(right, posx.get() +1, posy.get());
                up.setOnAction(event -> {
                    moveUp(grid, imgView1, posx.get(), posy.get());
                    posy.set(0);
                    grid.getChildren().remove(up);
                    grid.getChildren().remove(left);
                    grid.getChildren().remove(right);
                });
                left.setOnAction(event -> {
                    moveLeft(grid, imgView1, posx.get(), posy.get());
                    posx.set(0);
                    grid.getChildren().remove(up);
                    grid.getChildren().remove(left);
                    grid.getChildren().remove(right);
                });
                right.setOnAction(event -> {
                    moveRight(grid, imgView1, posx.get(), posy.get());
                    posx.set(15);
                    grid.getChildren().remove(up);
                    grid.getChildren().remove(left);
                    grid.getChildren().remove(right);
                });
//Centre du plateau
            } else{
                Button up = new Button("^");
                grid.add(up, posx.get(), posy.get() -1);
                Button down = new Button("v");
                grid.add(down, posx.get(), posy.get() +1);
                Button left = new Button("<");
                grid.add(left, posx.get() -1, posy.get());
                Button right = new Button(">");
                grid.add(right, posx.get() +1, posy.get());
                up.setOnAction(event -> {
                    moveUp(grid, imgView1, posx.get(), posy.get());
                    posy.set(0);
                    grid.getChildren().remove(up);
                    grid.getChildren().remove(down);
                    grid.getChildren().remove(left);
                    grid.getChildren().remove(right);
                });
                down.setOnAction(event -> {
                    moveDown(grid, imgView1, posx.get(), posy.get());
                    posy.set(15);
                    grid.getChildren().remove(up);
                    grid.getChildren().remove(down);
                    grid.getChildren().remove(left);
                    grid.getChildren().remove(right);
                });
                left.setOnAction(event -> {
                    moveLeft(grid, imgView1, posx.get(), posy.get());
                    posx.set(0);
                    grid.getChildren().remove(up);
                    grid.getChildren().remove(down);
                    grid.getChildren().remove(left);
                    grid.getChildren().remove(right);
                });
                right.setOnAction(event -> {
                    moveRight(grid, imgView1, posx.get(), posy.get());
                    posx.set(15);
                    grid.getChildren().remove(up);
                    grid.getChildren().remove(down);
                    grid.getChildren().remove(left);
                    grid.getChildren().remove(right);
                });

            }

        });
        grid.getChildren().add(imgView1);

//Apparition des autres robots non déplaçable
        Image robot2 = new Image(Gameboard.class.getResource("Power rangers/Force-rouge.png").toString());
        ImageView imgView2 = new ImageView(robot2);
        imgView2.setImage(robot2);
        imgView2.setFitWidth(36);
        imgView2.setFitHeight(36);
        grid.setConstraints(imgView2, 12, 3);
        grid.getChildren().add(imgView2);

        Image robot3 = new Image(Gameboard.class.getResource("Power rangers/Force-jaune.png").toString());
        ImageView imgView3 = new ImageView(robot3);
        imgView3.setImage(robot3);
        imgView3.setFitWidth(36);
        imgView3.setFitHeight(36);
        grid.setConstraints(imgView3, 13, 14);
        grid.getChildren().add(imgView3);

        Image robot4 = new Image(Gameboard.class.getResource("Power rangers/Force-bleue.png").toString());
        ImageView imgView4 = new ImageView(robot4);
        imgView4.setImage(robot4);
        imgView4.setFitWidth(36);
        imgView4.setFitHeight(36);
        grid.setConstraints(imgView4, 5, 8);
        grid.getChildren().add(imgView4);

        //Récupération d'un style de grille
        grid.getStyleClass().add("grid");
        return grid;
    }

    //--------------------------------Mouvements des robots ------------------------------------------------

    public static int moveUp(GridPane grid, ImageView img, int x, int y){
        grid.getChildren().remove(img);
        int posx = x;
        int posy = 0;
        grid.setConstraints(img, posx, posy);
        grid.getChildren().add(img);
        return posy;

    }
    public static int moveDown(GridPane grid, ImageView img, int x, int y){
        grid.getChildren().remove(img);
        int posx = x;
        int posy = 15;
        grid.setConstraints(img, posx, posy);
        grid.getChildren().add(img);
        return posy;


    }
    public static int moveLeft(GridPane grid, ImageView img, int x, int y){
        grid.getChildren().remove(img);
        int posx = 0;
        int posy = y;
        grid.setConstraints(img, posx, posy);
        grid.getChildren().add(img);
        return posx;

    }
    public static int moveRight(GridPane grid, ImageView img, int x, int y){
        grid.getChildren().remove(img);
        int posx = 15;
        int posy = y;
        grid.setConstraints(img, posx, posy);
        grid.getChildren().add(img);
        return posx;

    }

}

