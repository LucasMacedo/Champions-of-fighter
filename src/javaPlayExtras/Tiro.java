package javaPlayExtras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameObject;
import javaPlay.Imagem;
import javaPlay.Sprite;
import javax.swing.JOptionPane;

public class Tiro extends GameObject{
    private long timeElapsed;
    
    private int velocidade = 25;
    
    private boolean existeTiro;
    private boolean existeColisao;
    private Imagem tiro;
    
    private EnumPersonagem personagem;
    
    public Tiro(){
        this.height = 0;
        this.width = 0;
        
        this.x = -300;
        this.y = -300;
        
        this.personagem = EnumPersonagem.PERSONAGEM;
        
        this.iniciaTiro();
    }
    
    public Tiro(int posicaoX, int posicaoY, int width, int height, EnumPersonagem personagem){        
        this.personagem = personagem;
        this.existeTiro = true;
        
        this.iniciaTiro();
        
        if(personagem == EnumPersonagem.JOGADOR){
            this.x = posicaoX-25;
        }else{
            this.x = posicaoX+width;
        }
        
        this.y = (height/2)+posicaoY-(this.height/2);
    }
    
    private void iniciaTiro(){
        try {
            switch(this.personagem){
                case JOGADOR:
                    this.tiro = new Imagem("resources/personagem/jogador/especial.png");
                    this.velocidade = 30;
                    break;
                case MARIO:
                    this.tiro = new Imagem("resources/personagem/inimigo/tiroMario.gif");
                    this.velocidade = 40;
                    break;
                case CHEFAO:
                    this.tiro = new Imagem("resources/personagem/inimigo/tiroChefao.png");
                    this.velocidade = 40;
                    break;
                case ICHIGO:
                    this.tiro = new Imagem("resources/personagem/inimigo/tiroIchigo.png");
                    this.velocidade = 40;
                    break;
                case NARUTO:
                    this.tiro = new Imagem("resources/personagem/inimigo/tiroNaruto.gif");
                    this.velocidade = 40;
                    break;
                default: 
                    this.tiro = new Imagem("resources/personagem/jogador/especial.png");
                    break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na imagem Tiro: "+e);
        }
        
        this.height = this.tiro.getHeight();
        this.width = this.tiro.getWidth();
    }
    
    public void colisao(ObjetoComGravidade obj){
        this.existeColisao = obj.temColisao(this.getRectangle());
    }
    
    private Rectangle getRectangle(){
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
    
    private void fimTiro(){
        this.x = -350;
        this.y = -350;
        
        this.existeTiro = false;
    }
    
    public boolean getExiste(){
        return existeTiro;
    }
    
    public boolean existeColisao(){
        if(this.existeColisao){
            this.fimTiro();
        }
        return this.existeColisao;
    }
    
    @Override
    public void step(long timeElapsed) {
        this.timeElapsed += timeElapsed;
        
        if(this.existeTiro){
            if(this.personagem == EnumPersonagem.JOGADOR){
                this.x -= this.velocidade;
            }else{
                this.x += this.velocidade;
            }
        
            if(this.existeColisao){
                //Animacao

                this.fimTiro();
            }

            if(this.x > 850 || this.x < -50){
                this.fimTiro();
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        this.tiro.draw(g, this.x, this.y);
        g.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
    }
    
    public void drawUPPER(Graphics g, double aumenta){
        this.tiro.drawUPPER(g, this.x, this.y, aumenta);
        g.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
    }
    
    public void drawFlipped(Graphics g) {
        this.tiro.drawFlipped(g, this.x, this.y);
        g.drawRect(this.x, this.y, this.getWidth(), this.getHeight());
    }
}
