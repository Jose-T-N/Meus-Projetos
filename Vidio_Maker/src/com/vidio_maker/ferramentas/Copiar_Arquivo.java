/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vidio_maker.ferramentas;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author JosNeto
 */
public class Copiar_Arquivo {
    
    private Itens itens;

    private File musica;
    private File imagem;
    
    public static final String caminho = "C:\\Users\\Public\\Videos\\vidioMaker\\";
    
    public Copiar_Arquivo(Itens itens) throws IOException {
        this.itens = itens;      
        
        File file = new File(caminho);
        
        if (!file.exists()) {
            file.mkdirs();
        }else{
            
            deleteDirectory(file);
            
            file.delete();
            file.mkdirs();
        }
        
        copiar_arquivo();
        
        System.out.println(getImagem());
        
    }
    
    private void copiar_arquivo() throws IOException{
        
        String mudanca = "_";
        
        Path imagem_ = Paths.get(itens.getOriginal_Imagem().toString().replaceAll("/", "\\\\"));
        Path musica_ = Paths.get(itens.getOriginal_Musica().toString().replaceAll("/", "\\\\"));

        //imagem
        String extencao_imagem = imagem_.getFileName().toString();
        int index_imagem = extencao_imagem.lastIndexOf(".");
        
        Path imagem_copia = Paths.get(caminho
               +"imagem."+
                extencao_imagem.substring(index_imagem+1, extencao_imagem.length()));
        //musica
        String extencao_musica = musica_.getFileName().toString();
        int index_musica = extencao_musica.lastIndexOf(".");
        
        Path musica_copia = Paths.get(caminho
                +"musica."+
                 extencao_musica.substring(index_musica+1, extencao_musica.length()));
        
        System.err.println(musica_.toAbsolutePath());
        while (true) {            
            if(!musica_copia.toFile().isFile() && !imagem_copia.toFile().isFile()){
                Files.copy(musica_,musica_copia);
                Files.copy(imagem_,imagem_copia);
                
                break;
            }
            else{
                mudanca = mudanca + mudanca;
                Caminhos caminhos = muda_nome(mudanca,imagem_copia,musica_copia);
                
                musica_copia = caminhos.musica;
                imagem_copia = caminhos.imagem;
                
            }
        }
            
        musica = musica_copia.toFile();
        imagem = imagem_copia.toFile();
    }
    
    private Caminhos muda_nome(String mudanca,Path imagem_copia, Path musica_copia){
        Path imagem_ = Paths.get(itens.getOriginal_Imagem().toString().replaceAll("/", "\\\\"));
        Path musica_ = Paths.get(itens.getOriginal_Musica().toString().replaceAll("/", "\\\\"));

        //imagem
        String extencao_imagem = imagem_.getFileName().toString();
        int index_imagem = extencao_imagem.lastIndexOf(".");
        
        imagem_copia = Paths.get(caminho
               +"imagem"+mudanca+"."+
                extencao_imagem.substring(index_imagem+1, extencao_imagem.length()));
        //musica
        String extencao_musica = musica_.getFileName().toString();
        int index_musica = extencao_musica.lastIndexOf(".");
        
        musica_copia = Paths.get(caminho
                +"musica"+mudanca+"."+
                 extencao_musica.substring(index_musica+1, extencao_musica.length()));
        
       return new Caminhos(imagem_copia, musica_copia);
        
    }
 
     public Itens getItens(){
        return itens;
    }

    public File getImagem() {
        return imagem;
    }

    public File getMusica() {
        return musica;
    }
    
    // function to delete subdirectories and files
    public void deleteDirectory(File file)
    {
        // store all the paths of files and folders present
        // inside directory
        for (File subfile : file.listFiles()) {
  
            // if it is a subfolder,e.g Rohan and Ritik,
            // recursiley call function to empty subfolder
            if (subfile.isDirectory()) {
                deleteDirectory(subfile);
            }
  
            // delete files and empty subfolders
            subfile.delete();
        }
    }
  
    
    private class Caminhos{
        
        public Path imagem;
        public Path musica;

        public Caminhos(Path imagem, Path musica) {
            this.imagem = imagem;
            this.musica = musica;
        }
        
    }
    
}