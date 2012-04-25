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
import javaPlayExtras.EnumAcao;

public class Inimigo extends Personagem{
    @Override
    protected void eventosTeclado() {
        Keyboard key = GameEngine.getInstance().getKeyboard();
        
        boolean teclaPressionada = false;
        
        if(this.getPausaJogo()){
            return;
        }
        
        // Tecla UP
        if(key.keyDown(Keys.W)){
            this.pula();
            teclaPressionada = true;
        }
        // Tecla DOWN
        if(key.keyDown(Keys.S)){
            this.especial();
            teclaPressionada = true;
        }
        // Tecla LEFT
        if(key.keyDown(Keys.A)){
            if(this.colisaoPersonagem){
                return;
            }
            this.andaTras();
            teclaPressionada = true;
        }
        // Tecla RIGHT
        if(key.keyDown(Keys.D)){
            this.andaFrente();
            teclaPressionada = true;
        }
        // Tecla BATE
        if(key.keyDown(Keys.E)){
            this.bate();
            teclaPressionada = true;
        }
        // Nada
        if(this.acao != EnumAcao.NORMAL){
            if(this.timeEllapsed <= 185){
                return;
            }
            this.normal();
            return;
        }
        if(!teclaPressionada){
            this.normal();
        }
    }
    
    @Override
    protected void load() {
        super.load();
        
        this.x = 50;
        this.y = 400;
        
        this.inteligencia = 70;
        this.forca = 50;
    }
    
    public void step(long timeEllapsed){
        super.step(timeEllapsed);
        
        this.tiro.step(timeEllapsed);
        this.verificaColisaoParede();
    }
    
    @Override
    public void draw(Graphics g) {
        this.imgPersonagem.draw(g, this.x, this.y);
        
        g.drawRect(this.x, this.y, this.imgPersonagem.getWidth(), this.imgPersonagem.getHeight());
        this.imgBarra.setStatus(this.hp, this.sp);
        this.imgBarra.draw(g);
        
        switch(this.tipoPersonagem){
            case MARIO:
                this.tiro.drawUPPER(g, 1.5);
                break;
            default:
                this.tiro.draw(g);
                break;
        }
    }

    @Override
    protected void animacaoPula() {}

    @Override
    protected void animacaoAnda() {}

    @Override
    protected void animacaoEspecial() {}

    @Override
    protected boolean animacaoBate() {
        return false;
    }

    @Override
    protected void animacaoApanha() {}

    @Override
    protected void animacaoMorre() {}

    @Override
    protected void animacaoNormal() {}
}
