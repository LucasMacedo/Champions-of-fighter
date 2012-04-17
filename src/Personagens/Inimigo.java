package Personagens;

import java.awt.Graphics;
import javaPlay.Imagem;

public class Inimigo extends Personagem{
    
    public Inimigo(){
        super.load();
        this.load();
    }
    
    @Override
    protected void eventosTeclado() {
        
    }
    
    public void setFaseClassificatoria(int fase){
        fase ++;
        this.forca += (this.forca*fase)/5;
    }
    
    @Override
    protected void load() {
        try {
            this.imgApanha  = new Imagem("resources/personagem/imagem.png");
            this.imgBate    = new Imagem("resources/personagem/imagem.png");
            
            this.imgAtual   = new Imagem("resources/personagem/imagem.png");
            
            this.imgFrente  = new Imagem("resources/personagem/imagem.png");
            this.imgTras    = new Imagem("resources/personagem/imagem.png");
            
            this.imgNormal  = new Imagem("resources/personagem/imagem.png");
            this.imgMorto   = new Imagem("resources/personagem/imagem.png");
            
            this.imgPula    = new Imagem("resources/personagem/imagem.png");
            this.imgEspecial = new Imagem("resources/personagem/imagem.png");
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
        
        this.x = 50;
        this.y = 450;
    }
    
    @Override
    public void draw(Graphics g) {
        
    }

    @Override
    protected void executaAudio(String diretorio) {
        
    }
}
