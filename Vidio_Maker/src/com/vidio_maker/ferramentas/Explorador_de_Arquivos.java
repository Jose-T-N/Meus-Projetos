/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidio_maker.ferramentas;

import java.io.File;
import java.util.List;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author JosNeto
 */
public class Explorador_de_Arquivos {
    
    public File buscar_arquivo(Node node,String tipo, List<String>extencoes){
        
         Stage stage = (Stage) node.getScene().getWindow();
        
        FileChooser arquivo = new FileChooser();
        arquivo.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(tipo,extencoes));
        return arquivo.showOpenDialog(stage);
        
    }
    
}
