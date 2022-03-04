/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidio_maker.ferramentas;

import com.vidio_maker.Principal;
import com.vidio_maker.controller.PrincipalController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 *
 * @author JosNeto
 */
public class Execultar_CMD {

    private Thread cmd;
    private Thread cancelar;
    private String line;
    private String line_cancelar;
    private String verifica_null = "";
    
    private Itens itens;
    private PrincipalController controller; 

    public Execultar_CMD() throws IOException {
        cmd = new Thread();
    }

    public void start(PrincipalController controller,Itens itens) throws IOException {
        
        this.itens = itens;
        this.controller = controller;

        controller.getComessar().setText("cancelar");
        
        String imagem = itens.getImagem().toPath().toString().replaceAll("/", "\\\\");
        String musica = itens.getMusica().toPath().toString().replaceAll("/", "\\\\");

        String comando = String.format("%s -loop 1 -i \"%s\" -i \"%s\" -c:v libx264 -tune stillimage -c:a aac -b:a 192k -vf \"scale='iw-mod(iw,2)':'ih-mod(ih,2)',format=yuv420p\" -shortest -movflags +faststart \"%s.mp4\"",
                Principal.ffmpeg_caminho(),
                imagem,
                musica,
                 controller.getCaminho_salvar());
        
        final Runtime run = Runtime.getRuntime();
        Process pro;
        BufferedReader read;

        String[] cmds = {comando};

        ProcessBuilder builder = new ProcessBuilder("cmd", "/c",
                String.join("& ", cmds));

        builder.redirectErrorStream(true);

        Process p = builder.start();

        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        line = "cmd";
        controller.tela_carreganto(line);

        cmd = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        line = line + "\n" + r.readLine();
                        verifica_null = r.readLine();
                        if (verifica_null == null) {

                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                                    alerta.setContentText("Processo finalizado.");
                                    alerta.show();
                                    
                                    controller.getComessar().setText("comessar");
                                    controller.getProcessamento().getCmd().setText( line +"\n" +"finalizado..............");
                                    
                                    controller.getProcessamento().getCmd().setScrollTop(Double.MAX_VALUE);
                                    
                                }
                            });
                            
                            break;
                        } else {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    controller.getProcessamento().getCmd().setText(line);
                                    controller.getProcessamento().getCmd().setScrollTop(Double.MAX_VALUE);
                                
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    System.err.println(e);
                }

            }
        });
        
        cmd.start();
    }

    public String cancelar() throws IOException{
        
        cmd.interrupt();
        
        String comando ="taskkill /f /im ffmpeg.exe";
        
        final Runtime run = Runtime.getRuntime();
        Process pro;
        BufferedReader read;
        
        String[] cmds = {comando};

            ProcessBuilder builder = new ProcessBuilder("cmd", "/c",
                String.join("& ", cmds));

            builder.redirectErrorStream(true);
            
            Process p = builder.start();

            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            line_cancelar = "";
            
            cancelar = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            line_cancelar = r.readLine();
                            if (line_cancelar == null) {
                                break;
                            }
                            System.out.println(line_cancelar);
                            
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    
                                    controller.getProcessamento().getCmd().setText(line +"\n" +"cancelado.................");
                                    controller.getComessar().setText("comessar");
                                    
                                    controller.getProcessamento().getCmd().setScrollTop(Double.MAX_VALUE);
                                    
                                
                                }
                            });
                            
                        }
                        
                        cancelar.interrupt();
                        
                    } catch (Exception e) {
                        System.err.println(e);
                    }

                }
            });
            cancelar.start();
            
            this.cmd.interrupt();
            
            return "comessar";
    }
    
    public Thread getCmd() {
        return cmd;
    }
 
    private void menssagem(String mesnssagem){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setContentText(mesnssagem);
        alerta.show();
    }
    
}
