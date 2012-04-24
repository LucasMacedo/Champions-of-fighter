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
    
    private boolean existeTiro;
    private boolean existeColisao;
    private Imagem tiro;
    
    private EnumPersonagem personagem;
    
    public Tiro(){
        this.height = 0;
        this.width = 0;
        
        this.x = -10;
        this.y = -10;
        
        this.personagem = EnumPersonagem.PERSONAGEM;
        
        this.iniciaTiro();
    }
    
    public Tiro(int posicaoX, int posicaoY, int width, int height, EnumPersonagem personagem){
        if(personagem == EnumPersonagem.PERSONAGEM){
            this.x = posicaoX-25;
        }else{
            this.x = posicaoX+width;
        }
        this.y = posicaoY+((height/2)/2);
        
        this.personagem = personagem;
        
        this.existeTiro = true;
        
        this.iniciaTiro();
    }
    
    private void iniciaTiro(){
        try {
            this.tiro = new Imagem("resources/personagem/especial.png");
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
        this.x = -20;
        this.y = -20;
        
        this.existeTiro = false;
    }
    
    public boolean getExiste(){
        return existeTiro;
    }
    
    public boolean existeColisao(){
        return this.existeColisao;
    }
    
    @Override
    public void step(long timeElapsed) {
        this.timeElapsed += timeElapsed;
        
        if(this.existeTiro){
            if(this.personagem == EnumPersonagem.JOGADOR){
                this.x -= 15;
            }else{
                this.x += 15;
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
    }
    
    public void drawFlipped(Graphics g) {
        this.tiro.drawFlipped(g, this.x, this.y);
    }
}
