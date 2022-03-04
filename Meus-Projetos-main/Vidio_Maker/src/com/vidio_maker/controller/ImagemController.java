/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidio_maker.controller;

import com.vidio_maker.ferramentas.Explorador_de_Arquivos;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JosNeto
 */
public class ImagemController extends Tela_Media{

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private ImageView imagem;

    @FXML
    private Button buscar_imagem;
    
    @FXML
    private Pane pane;

    private PrincipalController controller;
    
    ImagemController(PrincipalController controller) {
       this.controller = controller;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buscar_imagem.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {
            if(controller.getCmd_thread().getCmd().getState() != Thread.State.RUNNABLE){
                procurar_media();
            }
            else{
                menssagem("espere o processo acabar");
            }
        });
        
    }    

    @Override
    protected void mostra_media() {
         imagem.setImage(new Image(arquivo.toURI().toString()));
    }

    @Override
    protected void procurar_media() {
        
        String [] extencoes = {"png","*.png","jpg","*.jpg","jpge","*.jpge"};
        
        File arquivo_intermediario = es.buscar_arquivo(buscar_imagem, "imagem", Arrays.asList(extencoes));
        arquivo = arquivo_intermediario != null ? arquivo_intermediario: null ;
        
        if(arquivo != null){
            mostra_media();
        }else{
            alerta();
        }
    }
    
}
