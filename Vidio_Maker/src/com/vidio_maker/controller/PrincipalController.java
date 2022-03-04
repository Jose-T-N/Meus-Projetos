/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidio_maker.controller;

import static com.sun.javafx.PlatformUtil.isWindows;
import com.vidio_maker.Principal;
import com.vidio_maker.controller.ImagemController;
import com.vidio_maker.controller.MusicaController;
import com.vidio_maker.ferramentas.COnfigura_barra_titulo;
import com.vidio_maker.ferramentas.Caminho_Salvar;
import com.vidio_maker.ferramentas.Copiar_Arquivo;
import com.vidio_maker.ferramentas.Execultar_CMD;
import com.vidio_maker.ferramentas.Itens;
import com.vidio_maker.ferramentas.Vidio_Maker;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author JosNeto
 */
public class PrincipalController implements Initializable {

    @FXML
    private StackPane principal;

    @FXML
    private AnchorPane anchorpane;

    @FXML
    private HBox telas;

    @FXML
    private Pane tela_musica;

    @FXML
    private Pane tela_imagem;

    @FXML
    private HBox botao;

    @FXML
    private Button comessar;

    @FXML
    private HBox barra_titulo;

    @FXML
    private FontAwesomeIconView fecha;

    @FXML
    private Pane cmd;
    
    @FXML
    private Label caminho;

    @FXML
    private TextField nome_arquivo;
    
    @FXML
    private Button pasta_salvar;
    
    private ImagemController imagem;
    
    private MusicaController musica;
    
    private ProcessamentoController processamento;
    
    private Vidio_Maker vm;
    
    private Itens itens;
    
    private Caminho_Salvar cs;
    
    private String caminho_salvar; 
    
    private Execultar_CMD cmd_thread;
    
    private File arquivo_intermediario;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cs = new Caminho_Salvar();

        try {
            cmd_thread = new Execultar_CMD();
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        imagem = new ImagemController(this);
        musica = new MusicaController(this);

        caminho_salvar = null;

        new COnfigura_barra_titulo(fecha, this);

        pasta_salvar.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {

            pasta_salvar();
        });

        comessar.addEventFilter(MouseEvent.MOUSE_CLICKED, (event) -> {

            if (cmd_thread.getCmd().getState() != Thread.State.RUNNABLE) {

                try {

                    if (musica.getFile() != null && imagem.getFile() != null) {

                        itens = new Itens(musica.getFile(), imagem.getFile());

                        if (caminho_salvar == null) {
                            menssagem("Aperte \"buscar pasta\" \nE selecione a pasta para salvar o arquivo");
                        } else if (nome_arquivo.getText().length() != 0
                                && nome_arquivo.getText().indexOf(" ") < 0) {
                            caminho_salvar = arquivo_intermediario+"\\" + nome_arquivo.getText();
                            vm = new Vidio_Maker(cmd_thread, this, itens);
                            
                            
                            
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                        } else {
                            menssagem("Digite o nome do arquivo \nE NÃO USE ESPAÇO");
                        }
                    } else {
                        menssagem("Selecione a musica e a imagem");
                    }
                } catch (IOException ex) {
                    Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } else {
                cancelar();
            }
        });

        try {
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("fxml/imagem.fxml"));
            loader1.setController(imagem);
            Parent pane1 = (Parent) loader1.load();
            tela_imagem.getChildren().add(pane1);

            //configuraçãoes da tela_musica
            FXMLLoader loader2 = new FXMLLoader(getClass().getResource("fxml/musica.fxml"));
            loader2.setController(musica);
            Pane pane2 = (Pane) loader2.load();
            tela_musica.getChildren().add(pane2);

        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    } 
    
    public void tela_carreganto(String line){
        
        processamento = new ProcessamentoController(vm);
        
        try {       
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("fxml/processamento.fxml"));
            loader1.setController(processamento);
            Parent pane1 =(Parent)loader1.load();
            cmd.getChildren().add(pane1);
        
           } catch (IOException ex) {
               Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }

    public ProcessamentoController getProcessamento() {
        return processamento;
    }

    public Vidio_Maker getVm() {
        return vm;
    }

    public StackPane getPrincipal() {
        return principal;
    }

    public TextField getNome_arquivo() {
        return nome_arquivo;
    }

    public Label getCaminho() {
        return caminho;
    }

    public MusicaController getMusica() {
        return musica;
    }

    public ImagemController getImagem() {
        return imagem;
    }
 
    private void menssagem(String mesnssagem){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setContentText(mesnssagem);
        alerta.show();
    }

    public String getCaminho_salvar() {
        return caminho_salvar;
    }

    public Execultar_CMD getCmd_thread() {
        return cmd_thread;
    }
    
    private void cancelar() {
        //String comando = "taskkill /f /im ffmpeg.exe";
        try {
            cmd_thread.cancelar();
            processamento.getCmd().setText("cancelado");
            comessar.setText("comessar");
        } catch (IOException ex) {
            Logger.getLogger(COnfigura_barra_titulo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void pasta_salvar() {
        if (cmd_thread.getCmd().getState() != Thread.State.RUNNABLE) {
            arquivo_intermediario = cs.caminho(principal);
            caminho_salvar = arquivo_intermediario != null ? arquivo_intermediario.toPath().toString().replaceAll("/", "\\\\")
                    : null;

            String verificacao = arquivo_intermediario.toPath().toString().replaceAll("/", "\\\\") + "\\";
            
            if (verificacao.equals(Copiar_Arquivo.caminho)) {
                
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setContentText("O diretorio que voce escolheu " + "\nÉ o mesmo que o progerama usa para salvar seus arquivos"
                        + "\nPortanto é apagado constantimente"
                        + "\nSelecione outra pasta");
                alerta.showAndWait();
                
                pasta_salvar();
                
            } else if (caminho_salvar == null) {
                menssagem("pasta não selecionada \n aperte \"busca pasta\" e selecione a pasta");
            } else {
                caminho.setText(caminho_salvar);
                caminho_salvar = arquivo_intermediario.toPath().toString().replaceAll("/", "\\\\");
            }
        } else {
            menssagem("espere o processo acabar");
        }
    }
    
    public void sair() throws IOException{
        cmd_thread.cancelar();
    }

    public Button getComessar() {
        return comessar;
    }

    public File getArquivo_intermediario() {
        return arquivo_intermediario;
    }
    
}
