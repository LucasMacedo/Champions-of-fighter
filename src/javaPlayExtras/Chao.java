package javaPlayExtras;

import java.awt.Graphics;
import javaPlay.GameObject;
import javaPlay.Imagem;
import javax.swing.JOptionPane;

public class Chao extends GameObject{
    private Imagem imgParede;
    
    public Chao(Graphics g, int x, int y, int imagem, String lista[]){
        
        this.x = x;
        this.y = y;
        
        try {
            this.imgParede = new Imagem(lista[imagem-1]);
            this.width = this.imgParede.getWidth();
            this.height = this.imgParede.getHeight();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro: "+ex);
        }
        
        this.imgParede.draw(g, this. x, this.y);
    }
    
    @Override
    public void step(long timeElapsed) {}

    @Override
    public void draw(Graphics g) {}
}
