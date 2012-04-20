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
        g.fillRect(this.x+58, this.y+7, 144*2, 15);
        // Fundo SP
        g.fillRect(this.x+59, this.y+24, 87*2, 15);
        
        // Barra HP
        g.setColor(Color.GREEN);
        g.fillRect(this.x+58, this.y+7, (int)hp*2, 15);
        // Barra SP
        g.setColor(Color.blue);            
        
        g.fillRect(this.x+59, this.y+24, (int)sp*2, 15);
        
        //g.setColor(Color.red);
        //g.fillRect(367, this.y+3, 65, 54);
        
        this.barra.draw(g, this.x, this.y);
    }
    
    public void drawFlipped(Graphics g){
        // Fundo HP
        g.setColor(Color.white);
        g.fillRect(this.x+62, this.y+7, 148*2, 15);
        // Fundo SP
        g.fillRect(this.x+181, this.y+24, 87*2, 15);
        
        //Barra HP
        g.setColor(Color.GREEN);
        g.fillRect((150-(int)hp)*2+this.x+58, this.y+7, ((int)hp)*2, 15);
        // Barra SP
        g.setColor(Color.BLUE);
        g.fillRect((87-(int)sp)*2+this.x+181, this.y+24, ((int)sp)*2, 15);
       
        
        this.barra.drawFlipped(g, x, y);
    }
    
    @Override
    public void step(long timeElapsed) {}

}
