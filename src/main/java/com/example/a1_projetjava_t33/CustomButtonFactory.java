package com.example.a1_projetjava_t33;

import javafx.scene.control.Button;
// Code de bouton personnalisable présent dans un des TP
public class CustomButtonFactory {
    private Button button;

    private CustomButtonFactory(String old, String nouv) {
        this.button = new Button(old);
        this.button.setOnMouseClicked((event) ->
                button.setText(nouv));
    }

    public static Button createButton(String old, String nouv) {
        return new CustomButtonFactory(old, nouv).button;
    }
}
