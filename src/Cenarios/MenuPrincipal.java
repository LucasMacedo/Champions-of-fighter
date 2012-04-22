package Cenarios;

import java.awt.Graphics;
import javaPlay.GameStateController;
import javaPlay.Imagem;
import javaPlay.Sprite;
import javax.swing.JOptionPane;

public class MenuPrincipal implements GameStateController{
    
    private Sprite StartGame;
    private Sprite Exit;
    private Sprite FundoMenu;
   
    public MenuPrincipal(){ 
        try {
            this.StartGame = new Sprite("resources/cenario/menu.jpg", 0, 0, 109, 40);
            this.Exit      = new Sprite("resources/cenario/menu.jpg", 0, 1, 74, 40);
            this.FundoMenu = new Sprite("resources/cenario/cenario.jpg", 4, 0, 800, 600);
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
        this.Exit.draw(g, 500, 400);
        this.StartGame.draw(g, 500, 300);
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
