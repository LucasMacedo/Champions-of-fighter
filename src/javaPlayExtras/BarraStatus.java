package javaPlayExtras;

import java.awt.Color;
import java.awt.Graphics;
import javaPlay.GameObject;
import javaPlay.Imagem;
import javaPlay.Sprite;

public class BarraStatus extends GameObject{
    private Imagem barra;
    private Sprite avatar;
    
    public BarraStatus(int x, int y, EnumPersonagem personagem){
        try {
            this.barra  = new Imagem("resources/personagem/barra/barra.png");
            this.avatar = new Sprite("resources/personagem/barra/avatar.png", 0, 0, 51, 48);
            
            switch (personagem) {
                case MARIO:
                    this.avatar.setCurrAnimFrameWidth(0);
                    break;
                case JOGADOR:
                    this.avatar.setCurrAnimFrameWidth(1);
                    break;
                case ICHIGO:
                    this.avatar.setCurrAnimFrameWidth(2);
                    break;
                case NARUTO:
                    this.avatar.setCurrAnimFrameWidth(3);
                    break;
                case CHEFAO:
                    this.avatar.setCurrAnimFrameWidth(4);
                    break;
                default:
                    
            }
            
            this.width = this.barra.getWidth();
            this.height = this.barra.getHeight();
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
        
        this.x = x;
        this.y = y;
    }
    
    public void setStatus(double hp, double sp){
        this.hp = hp;
        this.sp = sp;
    }
    
    @Override
    public void draw(Graphics g){
        //this.barra.draw(g, this.x, this.y);
        // Fundo HP
        g.setColor(Color.white);
        g.fillRect(this.x+60, this.y+7, 144*2, 15);
        // Fundo SP
        g.fillRect(this.x+60, this.y+24, 86*2, 15);
        
        // Barra HP
        g.setColor(Color.GREEN);
        g.fillRect(this.x+60, this.y+7, (int)hp*2, 15);
        // Barra SP
        g.setColor(Color.blue);            
        
        g.fillRect(this.x+60, this.y+24, ((int)sp-1)*2, 15);
        
        //g.setColor(Color.red);
        //g.fillRect(367, this.y+3, 65, 54);
        
        this.avatar.draw(g, x+9, y+8);
        this.barra.draw(g, this.x, this.y);
    }
    
    public void drawFlipped(Graphics g){
        //this.barra.drawFlipped(g, x, y);
        // Fundo HP
        g.setColor(Color.white);
        g.fillRect(this.x+65, this.y+7, 144*2, 15);
        // Fundo SP
        g.fillRect(this.x+181, this.y+24, 86*2, 15);
        
        //Barra HP
        g.setColor(Color.GREEN);
        g.fillRect((144-(int)hp)*2+this.x+65, this.y+7, ((int)hp)*2, 15);
        // Barra SP
        g.setColor(Color.BLUE);
        g.fillRect((87-(int)sp)*2+this.x+181, this.y+24, ((int)sp-1)*2, 15);
       
        this.avatar.draw(g, x+353, y+8);
        this.barra.drawFlipped(g, x, y);
    }
    
    @Override
    public void step(long timeElapsed) {}

}
