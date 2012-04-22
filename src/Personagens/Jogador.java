package Personagens;

import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.Imagem;
import javaPlay.Keyboard;
import javaPlay.Keys;
import javaPlay.Sprite;
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
            this.especial("jogador");
        }
        // Tecla LEFT
        if(key.keyDown(Keys.A)){
            this.andaTras();
        }
        // Tecla RIGHT
        if(key.keyDown(Keys.D)){
            this.andaFrente();
        }
        // Tecla BATE
        if(key.keyDown(Keys.E)){
            this.bate();
        }
        // Nada
        if(!key.keyDown(Keys.W) && !key.keyDown(Keys.S) && !key.keyDown(Keys.D) && !key.keyDown(Keys.A) ){
            this.normal();
        }
    }
    
    @Override
    protected void load() {
        try {
            this.imgPersonagem = new Sprite("resources/personagem/inimigo/mario.png", 1, 0, 76, 77);
            
            this.imgBarra    = new BarraStatus(17, 30);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
        
        this.x = 500;
        this.y = 400;
    }
    
    public void step(long timeEllapsed){
        super.step(timeEllapsed);
        
        this.tiro.step(timeEllapsed);
        
        this.verificaColisaoParede();
    }
    
    @Override
    public void draw(Graphics g) {
        this.imgPersonagem.draw(g, this.x, this.y);
        
        //g.drawRect(this.x, this.y, this.imgPersonagem.getWidth(), this.imgPersonagem.getHeight());
        this.imgBarra.setStatus(this.hp, this.sp);
        this.imgBarra.draw(g);
        this.tiro.draw(g);
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
