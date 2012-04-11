package javaPlayExtras;

import java.awt.Color;
import java.awt.Graphics;
import javaPlay.Imagem;
import javax.swing.JOptionPane;

public class BarraStatus {
    private Imagem barra;
    
    private int width;
    private int height;
    
    private int x;
    private int y;
    
    public BarraStatus(String diretorio, int x, int y){
        try {
            this.barra = new Imagem(diretorio);
            
            this.width = this.barra.getWidth();
            this.height = this.barra.getHeight();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
        
        this.x = x;
        this.y = y;
    }
    
    public void draw(Graphics g, int hp, int sp){
        
        //this.barra.draw(g, this.x, this.y);
        
        // Fundo HP
        g.setColor(Color.white);
        g.fillRect(this.x, this.y, 100*2, 20);
        // Fundo SP
        g.fillRect(this.x, this.y+30, 100, 20);
        
        // Barra HP
        g.setColor(Color.red);
        g.fillRect(this.x, this.y, hp*2, 20);
        // Barra SP
        g.setColor(Color.blue);
        g.fillRect(this.x, this.y+30, sp, 20);
        
        /*
        g.setColor(Color.white);
        g.fillRect(this.x, this.y, 100, 20);
        g.setColor(Color.red);
        g.fillRect((100-hp)+this.x, this.y, hp, 20);
        */
    }
}
