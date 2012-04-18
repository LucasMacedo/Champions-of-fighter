package Personagens;

import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.Imagem;
import javaPlay.Keyboard;
import javaPlay.Keys;
import javaPlayExtras.BarraStatus;

public class Inimigo extends Personagem{
    
    String tipoInimigo;
    
    public Inimigo(){
        super.load();
        this.load();
    }
    
    @Override
    protected void eventosTeclado() {
        Keyboard key = GameEngine.getInstance().getKeyboard();
        
        // Tecla UP
        if( key.keyDown(Keys.TOP)){
            this.pula();
        }
        // Tecla DOWN
        if(key.keyDown(Keys.BOTTOM)){
            this.especial("inimigo");
        }
        // Tecla LEFT
        if(key.keyDown(Keys.LEFT)){
            this.andaTras();
        }
        // Tecla RIGHT
        if(key.keyDown(Keys.RIGHT)){
            this.andaFrente();
        }
        
        if(!key.keyDown(Keys.TOP) && !key.keyDown(Keys.BOTTOM) && !key.keyDown(Keys.LEFT) && !key.keyDown(Keys.RIGHT) ){
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
            
            this.imgBarra   = new BarraStatus("resources/personagem/barra.png", 415, 30);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
        
        this.x = 700;
        this.y = 400;
    }
    
    public void step(long timeEllapsed){
        super.step(timeEllapsed);
        
        this.tiro.step(timeEllapsed);
        
        this.verificaColisaoParede();
    }
    
    @Override
    public void draw(Graphics g) {
        this.imgAtual.drawFlipped(g, this.x, this.y);
        
        //g.drawRect(this.x, this.y, this.imgAtual.getWidth(), this.imgAtual.getHeight());
        this.imgBarra.setStatus(this.hp, this.sp);
        this.imgBarra.drawFlipped(g);
        this.tiro.drawFlipped(g);
    }
    
    @Override
    protected void executaAudio(String diretorio) {
        
    }
}
