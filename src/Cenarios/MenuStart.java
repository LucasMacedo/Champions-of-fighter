
package Cenarios;

import java.awt.Graphics;
import javaPlay.GameStateController;
import javaPlay.Imagem;
import javax.swing.JOptionPane;

public class MenuStart implements GameStateController {
    
    private Imagem Campeonato;
    private Imagem Arcade;
    private Imagem Exit;
    private Imagem Fundo;
    
            
    public MenuStart(){
        //try{
            //this.Campeonato = new Imagem ("");
            //this.Arcade = new Imagem("");
            //this.Exit = new Imagem ("");
            //this.Fundo = new Imagem("");
            
        //}catch(Exception e){
        //JOptionPane.showMessageDialog(null, "Erro ao carregar a imagem de fundo: "+e);
       //}
    }

    @Override
    public void load() {
     
    }

     @Override
    public void step(long timeElapsed) {
    }

    @Override
    public void draw(Graphics g) {
       //this.Fundo.draw(g, 800, 600); 
       //this.Arcade.draw(g, 0, 0);
       //this.Campeonato.draw(g, 0, 0);
       //this.Exit.draw(g, 0, 0);
    
    }
    
    
    
    
    @Override
    public void unload() {
     }
    @Override
    public void start() {
    }
    @Override
    public void stop() {
    }
}
