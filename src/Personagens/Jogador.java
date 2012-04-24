package Personagens;

import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.Imagem;
import javaPlay.Keyboard;
import javaPlay.Keys;
import javaPlay.Sprite;
import javaPlayExtras.BarraStatus;
import javaPlayExtras.EnumAcao;
import javaPlayExtras.EnumPersonagem;
import javaPlayExtras.Tiro;
import javax.swing.JOptionPane;

public class Jogador extends Personagem{
    
    public Jogador(){
        super.load();
        this.load();
    }
    
    @Override
    protected void eventosTeclado() {
        Keyboard key = GameEngine.getInstance().getKeyboard();
        
        if(this.getPausaJogo()){
            return;
        }
        
        // Tecla UP
        if(key.keyDown(Keys.W)){
            this.pula();
            return;
        }
        // Tecla DOWN
        if(key.keyDown(Keys.S)){
            this.especial(EnumPersonagem.JOGADOR);
            return;
        }
        // Tecla LEFT
        if(key.keyDown(Keys.A)){
            this.andaTras();
            return;
        }
        // Tecla RIGHT
        if(key.keyDown(Keys.D)){
            this.andaFrente();
            return;
        }
        // Tecla BATE
        if(key.keyDown(Keys.E)){
            this.bate();
            return;
        }
        // Nada
        if(this.acao != EnumAcao.NORMAL){
            if(this.timeEllapsed <= 185){
                return;
            }
            this.normal();
            return;
        }
        this.normal();
    }
    
    @Override
    protected void load() {
        try {
            this.imgPersonagem = new Sprite("resources/personagem/jogador/jogador.png", 0, 0, 37, 94);
            this.imgBarra    = new BarraStatus(370, 30);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
        
        this.tipoPersonagem = EnumPersonagem.JOGADOR;
        
        this.forca = 50;
        this.inteligencia = 70;
        
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
        this.imgPersonagem.draw(g, this.x, this.y);
        
        //g.drawRect(this.x, this.y, this.imgPersonagem.getWidth(), this.imgPersonagem.getHeight());
        this.imgBarra.setStatus(this.hp, this.sp);
        this.imgBarra.drawFlipped(g);
        this.tiro.drawFlipped(g);
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
        /*if(this.timeEllapsed >= 40){
            if(!this.imgPersonagemVolta){
                if((this.imgPersonagem.getCurrAnimFrameWidth()+1) <= frames-1){
                    this.imgPersonagem.setCurrAnimFrameWidth(this.imgPersonagem.getCurrAnimFrameWidth()+1);
                }else{
                    this.imgPersonagem.setCurrAnimFrameWidth(this.imgPersonagem.getCurrAnimFrameWidth()-1);
                    this.imgPersonagemVolta = true;
                }
            }
            
            
            if(this.imgPersonagemVolta){
                if((this.imgPersonagem.getCurrAnimFrameWidth()-1) >= 0){
                    this.imgPersonagem.setCurrAnimFrameWidth(this.imgPersonagem.getCurrAnimFrameWidth()-1);
                }else{
                    this.imgPersonagem.setCurrAnimFrameWidth(this.imgPersonagem.getCurrAnimFrameWidth()+1);
                    this.imgPersonagemVolta = false;
                }
            }
        }*/
    }

    @Override
    protected void animacaoApanha() {
        this.imgPersonagem.setWidth(79);
        
        this.imgPersonagem.setCurrAnimFrameWidth(1);
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
