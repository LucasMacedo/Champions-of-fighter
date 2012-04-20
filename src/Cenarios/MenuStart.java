
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
        try{
            this.Campeonato = new Imagem ("resources/Menu/torneio.png");
            this.Arcade = new Imagem("resources/Menu/Versus.png");
            this.Exit = new Imagem ("resources/Menu/Sair.png");
            this.Fundo = new Imagem("resources/Menu/ModeGame.png");
            
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Erro ao carregar a imagem de fundo: "+e);
       }
    }

    @Override
    public void load() {
     
    }

     @Override
    public void step(long timeElapsed) {
    }

    @Override
    public void draw(Graphics g) {
       this.Fundo.draw(g, 0, 0); 
       this.Arcade.draw(g, 500, 400);
       this.Campeonato.draw(g, 500, 300);
       this.Exit.draw(g, 550, 500);
    
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
