/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidio_maker.controller;

import com.vidio_maker.ferramentas.Explorador_de_Arquivos;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 *
 * @author JosNeto
 */
public abstract class Tela_Media implements Initializable{
    
    protected File arquivo;
    
    protected final Explorador_de_Arquivos es = new Explorador_de_Arquivos(); 
    
    protected abstract void mostra_media();
    
    protected abstract void procurar_media();
    
    public File getFile(){
        return arquivo;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
    
    protected void alerta(){
        menssagem("Nem um arquivo secelcionado\nPorfavor selecione um arquivo");
    }
    
    protected void menssagem(String menssagem){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setContentText(menssagem);
        alerta.show();
    }
    
}
