package Personagens;

import javaPlayExtras.EnumPersonagem;
import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.Imagem;
import javaPlay.Keyboard;
import javaPlay.Keys;
import javaPlay.Sprite;
import javaPlayExtras.AudioPlayer;
import javaPlayExtras.BarraStatus;

public class Inimigo extends Personagem{
    EnumPersonagem tipoInimigo;
    
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
        super.load();
        
        this.x = 700;
        this.y = 400;
        
        this.forca = 50;
    }
    
    public void step(long timeEllapsed){
        super.step(timeEllapsed);
        
        this.tiro.step(timeEllapsed);
        this.verificaColisaoParede();
    }
    
    @Override
    public void draw(Graphics g) {
        this.imgPersonagem.drawFlipped(g, this.x, this.y);
        
        g.drawRect(this.x, this.y, this.imgPersonagem.getWidth(), this.imgPersonagem.getHeight());
        this.imgBarra.setStatus(this.hp, this.sp);
        this.imgBarra.drawFlipped(g);
        this.tiro.drawFlipped(g);
    }

    @Override
    protected void animacaoPula() {
        
    }

    @Override
    protected void animacaoAnda() {
        
    }

    @Override
    protected void animacaoEspecial() {
        
    }

    @Override
    protected void animacaoBate() {
        
    }
}
