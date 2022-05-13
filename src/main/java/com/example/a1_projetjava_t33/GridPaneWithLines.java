package com.example.a1_projetjava_t33;

import javafx.beans.property.BooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GridPaneWithLines {
    private static StackPane createCell(BooleanProperty cellSwitch, String[][] sourceImage, int x, int y, String letter) {
        String selectImage = letter + y;
        StackPane cell = new StackPane();

        Image img = new Image(sourceImage[x][y]);
        ImageView imgView = new ImageView(img);
        cell.getChildren().add(imgView);


        cell.setOnMouseClicked(e -> {
            cellSwitch.set(!cellSwitch.get());
            System.out.println(selectImage);
        });
        Circle circle = new Circle(10, Color.CORNFLOWERBLUE);
        circle.visibleProperty().bind(cellSwitch);
        cell.getChildren().add(circle);

        cell.getStyleClass().add("cell");
        return cell;
    }

    static GridPane createGrid(BooleanProperty[][] switches) {

        int numCols = switches.length;
        int numRows = switches[0].length;
        GridPane grid = new GridPane();
        for (int x = 0; x < numCols; x++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }
        for (int y = 0; y < numRows; y++) {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }
        for (int x = 0; x < numCols; x++) {
            for (int y = 0; y < numRows; y++) {
                String[][] sourceImage = new String[16][16];
                String letter = "";
                switch(x){
                    case 0:
                        letter = "A";
                        break;

                    case 1:
                        letter = "B";
                        break;

                    case 2:
                        letter = "C";
                        break;

                    case 3:
                        letter = "D";
                        break;

                    case 4:
                        letter = "E";
                        break;

                    case 5:
                        letter = "F";
                        break;

                    case 6:
                        letter = "G";
                        break;

                    case 7:
                        letter = "H";
                        break;

                    case 8:
                        letter = "I";
                        break;

                    case 9:
                        letter = "J";
                        break;

                    case 10:
                        letter = "K";
                        break;

                    case 11:
                        letter = "L";
                        break;

                    case 12:
                        letter = "M";
                        break;

                    case 13:
                        letter = "N";
                        break;

                    case 14:
                        letter = "O";
                        break;

                    case 15:
                        letter = "P";
                        break;
                }
                sourceImage[x][y] =  "C:\\Users\\techi\\Documents\\GitHub\\A1_Projet-Java_T3-3\\src\\main\\resources\\com\\example\\a1_projetjava_t33\\Cases\\"+letter+ String.format("%d",y+1) +".png";
                grid.add(createCell(switches[x][y], sourceImage, x, y, letter), x, y);

            }
        }
        grid.getStyleClass().add("grid");
        return grid;
    }

}