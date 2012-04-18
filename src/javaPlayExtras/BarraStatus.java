package javaPlayExtras;

import java.awt.Color;
import java.awt.Graphics;
import javaPlay.GameObject;
import javaPlay.Imagem;

public class BarraStatus extends GameObject{
    private Imagem barra;
    
    public BarraStatus(String diretorio, int x, int y){
        try {
            this.barra = new Imagem(diretorio);
            
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
        // Fundo HP
        g.setColor(Color.white);
        g.fillRect(this.x+10, this.y+18, 150*2, 15);
        // Fundo SP
        g.fillRect(this.x+10, this.y+42, 130, 10);
        
        // Barra HP
        g.setColor(Color.black);
        g.fillRect(this.x+10, this.y+18, (int)hp*2, 15);
        // Barra SP
        g.setColor(Color.blue);
        g.fillRect(this.x+10, this.y+42, (int)sp*2, 10);
        
        g.setColor(Color.black);
        //g.fillRect(367, this.y+3, 65, 54);
        
        this.barra.draw(g, this.x, this.y);
    }
    
    public void drawFlipped(Graphics g){
        // Fundo HP
        g.setColor(Color.white);
        g.fillRect(this.x+60, this.y+18, 150*2, 15);
        // Fundo SP
        g.fillRect(this.x+100, this.y+42, 130*2, 10);
        
        //Barra HP
        g.setColor(Color.black);
        g.fillRect((150-(int)hp)*2+this.x+60, this.y+18, ((int)hp)*2, 15);
        // Barra SP
        g.setColor(Color.BLUE);
        g.fillRect(this.x+100, this.y+42, ((int)sp)*2, 10);
        
        this.barra.drawFlipped(g, x, y);
    }
    
    @Override
    public void step(long timeElapsed) {}

}
