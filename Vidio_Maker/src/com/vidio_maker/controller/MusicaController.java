/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidio_maker.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JosNeto
 */
public class MusicaController extends Tela_Media{

    @FXML
    private Button buscar_musica;

    @FXML
    private FontAwesomeIconView play_pause;
    
    private MediaPlayer media_player;
    
    private boolean play;
    private final PrincipalController controller;    
    
    MusicaController(PrincipalController controller) {
        this.controller = controller;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        buscar_musica.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            
            if(controller.getCmd_thread().getCmd().getState() == Thread.State.NEW){
                procurar_media();
            }
            else{
                menssagem("espere o processo acabar");
            }
            
        });
        
        play_pause.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            
           if(controller.getCmd_thread().getCmd().getState() == Thread.State.NEW){
                mostra_media();
            }
            else{
                menssagem("espere o processo acabar");
            }
            
        });
    }    
    
    @Override
    protected void mostra_media() {
        if(media_player != null){
            if(play){
                media_player.pause();
                play = false;
            
                play_pause.setGlyphName("PLAY");
            
            }else{
                media_player.play();
                play = true;
            
                play_pause.setGlyphName("PAUSE");
            
            }
        }else{
            alerta();
        }
    }

    @Override
    protected void procurar_media() {
        
        String [] extencoes = {"mp3","*.mp3","wav","*.wav","ogg","*.ogg"};
        
        File arquivo_intermediario = es.buscar_arquivo(buscar_musica, "audio", Arrays.asList(extencoes));
        arquivo = arquivo_intermediario != null ? arquivo_intermediario: null ;
        
        if(arquivo != null){
            Media media = new Media(arquivo.toURI().toString());
            media_player = new MediaPlayer(media);
        }else{
            alerta();
        }
    }
    
}
