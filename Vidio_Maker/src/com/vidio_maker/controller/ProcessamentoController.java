/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidio_maker.controller;

import com.vidio_maker.ferramentas.Itens;
import com.vidio_maker.ferramentas.Vidio_Maker;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author JosNeto
 */
public class ProcessamentoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private TextArea cmd;

    private Vidio_Maker vm;
    
    private Itens itens;
    
    public ProcessamentoController(Vidio_Maker vm){
        this.vm = vm;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    public TextArea getCmd() {
        return cmd;
    }
    
}
