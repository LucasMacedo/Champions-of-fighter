package javaPlayExtras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameObject;
import javaPlay.Imagem;
import javaPlay.Sprite;
import javax.swing.JOptionPane;

public class Tiro extends GameObject{
    
    long timeElapsed;
    
    private boolean existeTiro;
    private boolean existeColisao;
    private Imagem tiro;
    
    String viradoPra;
    
    
    public Tiro(){
        this.height = 0;
        this.width = 0;
        
        this.x = 0;
        this.y = 0;
        
        try {
            this.tiro = new Imagem("resources/personagem/especial.png");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na imagem Tiro: "+e);
        }
    }
    
    public Tiro(int posicaoX, int posicaoY, int width, int height, String viradoPra){
        this.x = posicaoX+width;
        this.y = posicaoY+height/2;
        
        this.viradoPra = viradoPra;
        this.existeTiro = true;
        
        this.height = 29;
        this.width = 29;
        
         try {
            this.tiro = new Imagem("resources/personagem/especial.png");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na imagem Tiro: "+e);
        }
        
    }
    
    public void colisao(ObjetoComGravidade obj){
        this.existeColisao = obj.temColisao(this.getRectangle());
        //JOptionPane.showMessageDialog(null, this.existeColisao);
    }
    
    private boolean existeColisao(){
        return this.existeColisao;
    }
    
    private Rectangle getRectangle(){
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
    
    private void fimTiro(){
        this.width = 0;
        this.height = 0;
        
        this.existeTiro = false;
    }
    
    public boolean getExiste(){
        return existeTiro;
    }
    
    @Override
    public void step(long timeElapsed) {
        this.timeElapsed += timeElapsed;
        this.x += 30;
        
        if(this.existeColisao()){
            //Animacao
            
            
            this.fimTiro();
        }
        
        if(this.x > 850 || this.x < -50){
            this.fimTiro();
        }
    }

    @Override
    public void draw(Graphics g) {
        this.tiro.draw(g, this.x, this.y);
    }
    
}
