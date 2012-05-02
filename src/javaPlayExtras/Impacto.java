package javaPlayExtras;

import java.awt.Graphics;
import javaPlay.GameObject;
import javaPlay.Imagem;

public class Impacto extends GameObject{
    private Imagem imgImpacto;
    
    private long timeEllapsed;
    
    public Impacto(){
        this.load();
        
        this.x = -500;
        this.y = -500;
    }
    
    public Impacto(int x, int y, int width, int height, EnumPersonagem p){
        this.load();
        if(p == EnumPersonagem.JOGADOR){
            this.x = x+25;
        }else{
            this.x = x+width-25;
        }
        this.y = y+(height/2)-(this.height/2);
        
        this.timeEllapsed = 0;
    }
    
    private void load(){
        try {
            //this.imgImpacto = new Sprite("resources/personagem/impacto.png", 2, 0, 39, 78);
            this.imgImpacto = new Imagem("resources/personagem/impacto.png");
        } catch (Exception ex) {
            
        }
        this.height = this.imgImpacto.getHeight();
    }
    
    private void fimImpacto(){
        this.x = -500;
        this.y = -500;
    }
    
    @Override
    public void step(long timeEllapsed) {
        this.timeEllapsed += timeEllapsed;
        if(this.timeEllapsed >= 80){
            this.fimImpacto();
        }
    }

    @Override
    public void draw(Graphics g) {
        this.imgImpacto.draw(g, this.x, this.y);
    }
    
    public void drawFlipped(Graphics g){
        this.imgImpacto.drawFlipped(g, this.x, this.y);
    }
}
