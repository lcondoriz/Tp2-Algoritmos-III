package edu.fiuba.algo3.vista;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class ReproductorSonido {
    private MediaPlayer[] reproductor;

    public ReproductorSonido() {
        inicializarSonidos();
    }

    private void inicializarSonidos() {

       // Rutas a los archivos de sonido
        String[] soundFiles = {
            "src/main/java/edu/fiuba/algo3/vista/Sonidos/efectoSonido00_Dados.mp3",
            "src/main/java/edu/fiuba/algo3/vista/Sonidos/efectoSonido01_Victoria.mp3",
            "src/main/java/edu/fiuba/algo3/vista/Sonidos/efectoSonido02_InicioTablero.mp3",
            "src/main/java/edu/fiuba/algo3/vista/Sonidos/efectoSonido03_AgregarUsuarioExitoso.mp3",
            "src/main/java/edu/fiuba/algo3/vista/Sonidos/efectoSonido04_AgregarUsuarioFallido.mp3"
        };


        reproductor = new MediaPlayer[soundFiles.length];

        for (int i = 0; i < soundFiles.length; i++) {
            Media sound = new Media(new File(soundFiles[i]).toURI().toString());
            reproductor[i] = new MediaPlayer(sound);
        }
    }

    public void reproducirSonido(int soundIndex) {
        if (soundIndex >= 0 && soundIndex < reproductor.length) {
            reproductor[soundIndex].stop();
            reproductor[soundIndex].play();
        }
    }

}