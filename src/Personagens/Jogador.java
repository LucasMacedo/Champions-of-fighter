package Personagens;

import java.awt.Color;
import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.Imagem;
import javaPlay.Keyboard;
import javaPlay.Keys;
import javax.swing.JOptionPane;

public class Jogador extends Personagem{
    
    public Jogador(){
        this.load();
    }
    
    @Override
    protected void eventosTeclado() {
        Keyboard key = GameEngine.getInstance().getKeyboard();
        
        // Tecla UP
        if( key.keyDown(Keys.W)){
            this.pula();
        }
        // Tecla DOWN
        if(key.keyDown(Keys.S)){
            this.defende();
        }
        // Tecla LEFT
        if(key.keyDown(Keys.A)){
            this.andaTras();
        }
        // Tecla RIGHT
        if(key.keyDown(Keys.D)){
            this.andaFrente();
        }
        
        if(!key.keyDown(Keys.W) && !key.keyDown(Keys.S) && !key.keyDown(Keys.D) && !key.keyDown(Keys.A) ){
            this.normal();
        }
    }
    
    @Override
    protected void load() {
        try {
            this.imgApanha  = new Imagem("resources/personagem/apanha.png");
            this.imgBate    = new Imagem("resources/personagem/soco.png");
            
            this.imgAtual   = new Imagem("resources/personagem/normal.png");
            
            this.imgFrente  = new Imagem("resources/personagem/normal.png");
            this.imgTras    = new Imagem("resources/personagem/normal.png");
            
            this.imgNormal  = new Imagem("resources/personagem/normal.png");
            this.imgMorto   = new Imagem("resources/personagem/normal.png");
            
            this.imgPula    = new Imagem("resources/personagem/pula.png");
            this.imgDefende = new Imagem("resources/personagem/haduken.png");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
        
        this.forcaImpulso = 38;
        
        this.x = 50;
        this.y = 50;
    }
    
    public void step(long timeEllapsed){
        super.step(timeEllapsed);
        this.verificaColisaoParede();
    }
    
    @Override
    public void draw(Graphics g) {
        this.imgAtual.draw(g, this.x, this.y);
        
        g.drawRect(this.x, this.y, this.imgAtual.getWidth(), this.imgAtual.getHeight());
    }
    
}
