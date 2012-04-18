
package Cenarios;

import java.awt.Graphics;
import javaPlay.GameStateController;
import javaPlay.Imagem;
import javax.swing.JOptionPane;

public class Rank implements GameStateController{
    
    private Imagem rank;
    private Imagem fundo;
    private Imagem Exit;
    
    public Rank() {
        try{
            //this.fundo = new Imagem("");
            //this.Exit = new Imagem("");
            this.rank = new Imagem ("resources/Menu/Rank.png");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao carregar a imagem de fundo: "+e);
        }
    }
    
    
    @Override
    public void load() {
        
    }

    @Override
    public void unload() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void start() {
       
    }

    @Override
    public void step(long timeElapsed) {
      
    }

    @Override
    public void draw(Graphics g) {
     
        this.rank.draw(g, -110, 10);
    }

    @Override
    public void stop() {
        
    }
    
}
