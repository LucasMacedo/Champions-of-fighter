package Cenarios;

import java.awt.Graphics;
import javaPlay.GameStateController;
import javaPlay.Imagem;
import javax.swing.JOptionPane;

public class MenuEntrada implements GameStateController{
    
    private Imagem StartGame;
    private Imagem Exit;
    private Imagem FundoMenu;
   
    public MenuEntrada(){ 
    
        try {
            //this.StartGame  = new Imagem("resources/menu/fundo.jpg");
            //this.Exit = new Imagem("resources/menu/exit.jpg");
            this.FundoMenu = new Imagem ("resources/menu/menu.png");
          } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar a imagem de fundo: "+e);
        }
    }
    
 
        
    @Override
    public void step(long timeElapsed) {
        
    }

    @Override
    public void draw(Graphics g) {
        this.FundoMenu.draw(g, 0, 0);
        //this.Exit.draw(g, 50, 50);
        //this.StartGame.draw(g, 80, 90);
        
        
    }
    
    
    
    
    
    @Override
    public void load(){}
    @Override
    public void stop() {}
    @Override
    public void start() {}
    @Override
    public void unload() {}
}
