package com.example.a1_projetjava_t33;

import javafx.beans.property.BooleanProperty;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GridPaneWithLines{
    private static StackPane createCell(BooleanProperty cellSwitch) {
        StackPane cell = new StackPane();
        cell.setOnMouseClicked(e -> cellSwitch.set(! cellSwitch.get() ));
        Circle circle = new Circle(10, Color.CORNFLOWERBLUE);
        circle.visibleProperty().bind(cellSwitch);
        cell.getChildren().add(circle);
        cell.getStyleClass().add("cell");
        return cell;
    }
    static GridPane createGrid(BooleanProperty[][] switches) {
        int numCols = switches.length ;
        int numRows = switches[0].length ;
        GridPane grid = new GridPane();
        for (int x = 0 ; x < numCols ; x++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(cc);
        }
        for (int y = 0 ; y < numRows ; y++) {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(rc);
        }
        for (int x = 0 ; x < numCols ; x++) {
            for (int y = 0 ; y < numRows ; y++) {
                grid.add(createCell(switches[x][y]), x, y);
            }
        }
        grid.getStyleClass().add("grid");
        return grid;
    }
}
