package javaPlayExtras;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameObject;
import javaPlay.Imagem;
import javax.swing.JOptionPane;

public class Chao extends GameObject{
    private Imagem imgParede;
    
    public Chao(Graphics g, int x, int y, int imagem, String lista[]){
        
        this.x = x;
        this.y = y;
        
        this.width = 32;
        this.height = 32;
        
        //JOptionPane.showMessageDialog(null, lista[imagem-1]);
        try {
            this.imgParede = new Imagem(lista[imagem-1]);
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
