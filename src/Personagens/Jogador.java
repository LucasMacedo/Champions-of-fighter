package Personagens;

import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.Keyboard;
import javaPlay.Keys;
import javaPlay.Sprite;
import javaPlayExtras.BarraStatus;
import javaPlayExtras.EnumAcao;
import javaPlayExtras.EnumPersonagem;

public class Jogador extends Personagem{
    
    public Jogador(){
        super.load();
        this.load();
    }
    
    @Override
    protected void eventosTeclado() {
        Keyboard key = GameEngine.getInstance().getKeyboard();
        
        boolean teclaPressionada = false;
        
        if(this.getPausaJogo()){
            return;
        }
        
        if(key.keyDown(Keys.TOP) && key.keyDown(Keys.LEFT)){
            this.andaTras();
            this.pula();
            return;
        }
        if(key.keyDown(Keys.TOP) && key.keyDown(Keys.RIGHT)){
            this.andaFrente();
            this.pula();
            return;
        }
        if(key.keyDown(Keys.TOP) && key.keyDown(Keys.O)){
            this.bate();
            this.pula();
            return;
        }
        if(key.keyDown(Keys.TOP) && key.keyDown(Keys.BOTTOM)){
            this.especial();
            this.pula();
            return;
        }
        if(key.keyDown(Keys.TOP)){
            this.pula();
            return;
        }
        
        // Tecla BATE
        if(key.keyDown(Keys.O) && key.keyDown(Keys.LEFT)){
            this.andaTras();
            this.bate();
            return;
        }
        if(key.keyDown(Keys.O) && key.keyDown(Keys.RIGHT)){
            this.andaFrente();
            this.bate();
            return;
        }
        if(key.keyDown(Keys.O)){
            this.bate();
            return;
        }
        
        // Tecla DOWN
        if(key.keyDown(Keys.BOTTOM) && key.keyDown(Keys.LEFT)){
            this.andaTras();
            this.especial();
            return;
        }
        if(key.keyDown(Keys.BOTTOM) && key.keyDown(Keys.RIGHT)){
            this.andaFrente();
            this.especial();
            return;
        }
        if(key.keyDown(Keys.BOTTOM)){
            this.especial();
            return;
        }
        
        // Tecla LEFT
        if(key.keyDown(Keys.LEFT)){
            if(this.colisaoPersonagem){
                return;
            }
            this.andaTras();
            return;
        }
        // Tecla RIGHT
        if(key.keyDown(Keys.RIGHT)){
            this.andaFrente();
            return;
        }
        
        this.normal();
    }
    
    @Override
    protected void load() {
        this.tipoPersonagem = EnumPersonagem.JOGADOR;
        
        try {
            this.imgPersonagem = new Sprite("resources/personagem/jogador/jogador.png", 0, 0, 37, 94);
            this.imgBarra      = new BarraStatus(370, 30, this.tipoPersonagem);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
        
        this.forca = 40;
        this.inteligencia = 40;
        
        this.xInicial = 753;
        this.yInicial = 500;
        
        this.x = 790;
        this.y = 500;
        
        this.normal();
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
        this.imgBarra.drawFlipped(g);
        this.tiro.drawFlipped(g);
        this.impacto.draw(g);
    }

    @Override
    protected void animacaoPula() {
        this.imgPersonagem.setWidth(37);
        this.imgPersonagem.setHeight(93);
        
        this.imgPersonagem.setCurrAnimFrameWidth(1);
        this.imgPersonagem.setCurrAnimFrameHeight(0);
    }

    @Override
    protected void animacaoAnda() {
        /*int frames = 2;
        
        this.imgPersonagem.setWidth(46);
        if(this.timeEllapsed <= 100){
            
            if(this.imgPersonagem.getCurrAnimFrameWidth() == 0){
                this.imgPersonagem.setCurrAnimFrameWidth(1);
            }else{
                this.normal();
                //this.imgPersonagem.setCurrAnimFrameWidth(0);
            }
        }
        this.imgPersonagem.setCurrAnimFrameHeight(1);
        */
    }

    @Override
    protected void animacaoEspecial() {
        this.imgPersonagem.setWidth(75);
        
        this.imgPersonagem.setCurrAnimFrameWidth(1);
        this.imgPersonagem.setCurrAnimFrameHeight(2);
        
        this.timeEllapsed = 0;
    }

    @Override
    protected boolean animacaoBate() {
        int frames = 3;
        
        if(this.timeEllapsed+20 < 100){
            this.imgPersonagem.setWidth(75);
            this.imgPersonagem.setCurrAnimFrameHeight(2);
            this.imgPersonagem.setCurrAnimFrameWidth(2);
            return true;
        }else{
            this.normal();
            return false;
        }
    }

    @Override
    protected void animacaoApanha() {
        this.imgPersonagem.setWidth(47);
        
        this.imgPersonagem.setCurrAnimFrameWidth(0);
        this.imgPersonagem.setCurrAnimFrameHeight(3);
    }

    @Override
    protected void animacaoMorre() {
        this.imgPersonagem.setWidth(79);
        this.imgPersonagem.setCurrAnimFrameHeight(3);
        this.imgPersonagem.setCurrAnimFrameWidth(2);
    }

    @Override
    protected void animacaoNormal() {
        this.imgPersonagem.setWidth(37);
        
        this.imgPersonagem.setCurrAnimFrameWidth(0);
        this.imgPersonagem.setCurrAnimFrameHeight(0);
    }
    
}
