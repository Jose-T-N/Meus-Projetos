/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidio_maker.ferramentas;

import com.vidio_maker.controller.PrincipalController;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 *
 * @author JosNeto
 */
public class Vidio_Maker {
    
    Path arquivo;
    
    public Vidio_Maker(Execultar_CMD cmd,PrincipalController controller, Itens itens) throws IOException {
        
        arquivo = Paths.get(controller.getCaminho_salvar()+".mp4");
        
        System.out.println(arquivo.toFile().getAbsolutePath());
        
        
        if(!arquivo.toFile().isFile()){
            cmd.start(controller, itens);
        }else{
            menssagem("já existe um vidio com esse nome na pasta indicada"+
                    "\nModifiqui o nome do arquivo ou a pasta");
        }
        
    }
    private void menssagem(String mesnssagem){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setContentText(mesnssagem);
        alerta.show();
    }
}
