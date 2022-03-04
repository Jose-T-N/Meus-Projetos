/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidio_maker.ferramentas;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author JosNeto
 */
public class Itens {
    protected File musica;
    protected File imagem;
    
    private Copiar_Arquivo copiar;

    protected Itens(){
        
    }
    
    public Itens(File musica, File imagem) throws IOException {
        this.musica = musica;
        this.imagem = imagem;
        
        copiar = new Copiar_Arquivo(this);
        
    }
    
    public File getMusica() {
        return copiar.getMusica();
    }

    public File getImagem() {
        return copiar.getImagem();
    }
    
    public File getOriginal_Musica() {
        return musica;
    }

    public File getOriginal_Imagem() {
        return imagem;
    }

    public Copiar_Arquivo getCopiar() {
        return copiar;
    }
    
}
