package com.example.a1_projetjava_t33;

import javafx.scene.media.AudioClip;

//--------------------------------Sons ------------------------------------------------

public class Sounds {

    //Son lors d'un clic
    public static AudioClip ClicSound(){
        String soundFile = "clic.mp3";
        AudioClip clic = new AudioClip(Sounds.class.getResource("Sounds/"+ soundFile).toExternalForm());
        clic.setVolume(0.5);
        return clic;
    }

    //Son lors d'une erreur
    public static AudioClip ErrorSound(){

        String errorFile = "error.mp3";
        AudioClip erreur = new AudioClip(Sounds.class.getResource("Sounds/"+ errorFile).toExternalForm());
        erreur.setVolume(0.5);
        return erreur;
    }

    //Son lors d'une manche gagnée
    public static AudioClip TurnWinSound(){
        String turnWinFile = "turn_win.mp3";
        AudioClip turnWin = new AudioClip(Sounds.class.getResource("Sounds/"+ turnWinFile).toExternalForm());
        turnWin.setVolume(0.2);
        return turnWin;
    }

    //Son lors d'une victoire
    public static AudioClip VictorySound(){
        String victoryFile = "victory.mp3";
        AudioClip victory = new AudioClip(Sounds.class.getResource("Sounds/"+ victoryFile).toExternalForm());
        return victory;
    }

    //Musique du menu
    public static  AudioClip ThemeSound(){
        String musicFile = "theme_song.mp3";
        AudioClip sound = new AudioClip(Sounds.class.getResource("Sounds/"+ musicFile).toExternalForm());
        sound.setVolume(0.1);
        sound.play();
        return sound;
    }

    //Play
    public static void PlaySound(AudioClip sound){
        sound.play();
    }

    //Stop
    public static void StopSound(AudioClip sound){
        sound.stop();
    }
}
