package Personagens;

import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.Imagem;
import javaPlay.Keyboard;
import javaPlay.Keys;
import javaPlayExtras.BarraStatus;
import javaPlayExtras.Tiro;

public class Jogador extends Personagem{
    
    public Jogador(){
        super.load();
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
            this.especial();
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
            this.imgMorto   = new Imagem("resources/personagem/haduken.png");
            
            this.imgPula    = new Imagem("resources/personagem/pula.png");
            this.imgEspecial = new Imagem("resources/personagem/haduken.png");
            
            this.imgBarra   = new BarraStatus("resources/personagem/barra.png", 300, 100);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
        
        this.hp = 100;
        this.sp = 100;
        
        this.x = 50;
        this.y = 400;
    }
    
    public void step(long timeEllapsed){
        super.step(timeEllapsed);
        
        this.tiro.step(timeEllapsed);
        
        this.verificaColisaoParede();
    }
    
    @Override
    public void draw(Graphics g) {
        this.imgAtual.draw(g, this.x, this.y);
        
        g.drawRect(this.x, this.y, this.imgAtual.getWidth(), this.imgAtual.getHeight());
        this.imgBarra.setStatus(this.hp, this.sp);
        this.imgBarra.draw(g);
        this.tiro.draw(g);
    }

    @Override
    protected void executaAudio(String diretorio) {
        
    }
    
}
