/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidio_maker.ferramentas;

import java.io.File;
import java.util.Arrays;
import javafx.scene.Node;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 *
 * @author JosNeto
 */
public class Caminho_Salvar {
    public File caminho(Node node){
        
        Stage stage = (Stage) node.getScene().getWindow();
        
        DirectoryChooser directoryChooser = new DirectoryChooser();
        return directoryChooser.showDialog(stage);
    }
}  
