package Personagens;

import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.Keyboard;
import javaPlay.Keys;
import javaPlayExtras.EnumAcao;
import javaPlayExtras.EnumPersonagem;

public class Inimigo extends Personagem{
    private int contRound = 0;
    private boolean especial;
    
    public void eventos(Personagem personagem){
        if(this.getPausaJogo()){
            return;
        }
        
        if(this.acao == EnumAcao.APANHA){
            this.contRound ++;
        }
        
        if(personagem.getTiro().getX() - this.x <= 200 && personagem.getTiro().getX() - this.x > 0){
            this.pula();
        }else{
            this.normal();
        }
        
        if(this.especial){
            this.especial();
            this.especial = false;
            return;
        }
        
        if(this.y + this.height > personagem.getY() + personagem.getHeight()){
            this.pula();
            
            int talvez = (int)((10 - 0)*Math.random());
            if(talvez == 4){
                this.especial = true;
                return;
            }
        }
        
        if(personagem.getX() - (this.x+this.width) > 200){
            this.andaFrente();
            return;
        }
        
        if(personagem.getX() - (this.x+this.width) > 20){
            this.andaFrente();
            return;
        }
        
        if(this.x > personagem.getX()-5){
            this.andaTras();
            if(this.x > personagem.getX()+personagem.getWidth()){
                this.x = personagem.getX()-this.width;
            }
        }
        
        if(this.contRound >= 3 &&personagem.getX() - this.x <= 50){
            this.especial();
            this.contRound = 0;
            return;
        }
        
        this.bate();
    }
    
    @Override
    protected void eventosTeclado() {
        if(1 == 1){
            return;
        }
        
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
    
    public EnumPersonagem getTipoPersonagem(){
        return this.tipoPersonagem;
    }
    
    @Override
    protected void load() {
        super.load();
        
        this.xInicial = 10;
        this.yInicial = 500;
        
        this.x = 10;
        this.y = 500;
        
        this.inteligencia = 55;
        this.forca = 45;
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
        
        //g.drawRect(this.x, this.y, this.imgPersonagem.getWidth(), this.imgPersonagem.getHeight());
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