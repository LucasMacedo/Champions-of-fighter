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
        
        // Jogo Pausado
        if(this.getPausaJogo()){
            return;
        }
        
        // Pulando
        if(key.keyDown(Keys.W) && key.keyDown(Keys.A)){
            this.andaTras();
            this.pula();
            return;
        }
        if(key.keyDown(Keys.W) && key.keyDown(Keys.D)){
            this.andaFrente();
            this.pula();
            return;
        }
        if(key.keyDown(Keys.W) && key.keyDown(Keys.E)){
            this.bate();
            this.pula();
            return;
        }
        if(key.keyDown(Keys.W) && key.keyDown(Keys.S)){
            this.especial();
            this.pula();
            return;
        }
        if(key.keyDown(Keys.W)){
            this.pula();
            return;
        }
        
        // Tecla BATE
        if(key.keyDown(Keys.E) && key.keyDown(Keys.A)){
            this.andaTras();
            this.bate();
            return;
        }
        if(key.keyDown(Keys.E) && key.keyDown(Keys.D)){
            this.andaFrente();
            this.bate();
            return;
        }
        if(key.keyDown(Keys.E)){
            this.bate();
            return;
        }
        
        // Tecla DOWN
        if(key.keyDown(Keys.S) && key.keyDown(Keys.A)){
            this.andaTras();
            this.especial();
            return;
        }
        if(key.keyDown(Keys.S) && key.keyDown(Keys.D)){
            this.andaFrente();
            this.especial();
            return;
        }
        if(key.keyDown(Keys.S)){
            this.especial();
            return;
        }
        
        // Tecla LEFT
        if(key.keyDown(Keys.A)){
            if(this.colisaoPersonagem){
                return;
            }
            this.andaTras();
            return;
        }
        // Tecla RIGHT
        if(key.keyDown(Keys.D)){
            this.andaFrente();
            return;
        }
       
        this.normal();
    }
    
    @Override
    protected void load() {
        super.load();
        
        this.xInicial = 10;
        this.yInicial = 500;
        
        this.x = 10;
        this.y = 500;
        
        this.inteligencia = 40;
        this.forca = 30;
    }
    
    public void step(long timeEllapsed){
        
        this.tiro.step(timeEllapsed);
        
        this.verificaColisaoParede();
        
        if(this.acao == EnumAcao.APANHA){
            this.novoImpacto();
            
        }
        
        this.impacto.step(timeEllapsed);
        
        super.step(timeEllapsed);
    }
    
    @Override
    public void draw(Graphics g) {
        this.imgPersonagem.draw(g, this.x, this.y);
        
        g.drawRect(this.x, this.y, this.imgPersonagem.getWidth(), this.imgPersonagem.getHeight());
        this.imgBarra.setStatus(this.hp, this.sp);
        this.imgBarra.draw(g);
        this.impacto.drawFlipped(g);
        
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