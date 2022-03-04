/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidio_maker.ferramentas;

import com.vidio_maker.controller.PrincipalController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Node;

/**
 *
 * @author JosNeto
 */
public class COnfigura_barra_titulo {
    private boolean click;
    private double posicao_inicial=0;
    private double inicial_x;
    private double inicial_y;
    private double inicial_largura;
    private double inicial_altura;
    
    private boolean tela_cheia;
    
    private Dimension tamanho_tela;
    
    private String line;
    private Thread thread;
    
    public COnfigura_barra_titulo(Node fecha, PrincipalController controller) {

        fecha.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {

            Stage stage = (Stage) fecha.getScene().getWindow();
            stage.close();

            try {
                controller.sair();
            } catch (IOException ex) {
                Logger.getLogger(COnfigura_barra_titulo.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }
}
