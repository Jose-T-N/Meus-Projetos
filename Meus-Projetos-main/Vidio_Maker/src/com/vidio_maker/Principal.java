/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidio_maker;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author JosNeto
 */
public class Principal extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
       Parent root = FXMLLoader.load(getClass().getResource("controller/fxml/principal.fxml"));
       
       Scene scene = new Scene(root);

       primaryStage.initStyle(StageStyle.UNDECORATED);
       
       primaryStage.setScene(scene);
       primaryStage.show();
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }    
    
    public static String ffmpeg_caminho(){
        String caminho_ffmpeg = new File(".").getAbsolutePath();
        caminho_ffmpeg = caminho_ffmpeg.substring(0, caminho_ffmpeg.length()-1);
        
        caminho_ffmpeg = caminho_ffmpeg.replaceAll("/", "\\\\") + "\\ffmpeg\\bin\\ffmpeg.exe";
        
        return caminho_ffmpeg;
    }
    
}
