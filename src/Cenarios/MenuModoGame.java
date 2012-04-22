
package Cenarios;

import java.awt.Graphics;
import javaPlay.GameStateController;
import javaPlay.Imagem;
import javaPlay.Sprite;
import javax.swing.JOptionPane;

public class MenuModoGame implements GameStateController {
    
    private Sprite Campeonato;
    private Sprite Arcade;
    private Sprite Exit;
    private Sprite Fundo;
    
    public MenuModoGame(){
        try{
            this.Campeonato = new Sprite("resources/cenario/menu.jpg", 0, 2, 145, 40);
            this.Arcade     = new Sprite("resources/cenario/menu.jpg", 0, 3, 140, 40);
            this.Exit       = new Sprite("resources/cenario/menu.jpg", 0, 1, 74, 40);
            this.Fundo      = new Sprite("resources/cenario/cenario.jpg", 5, 0, 800, 600);
            
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Erro ao carregar a imagem de fundo: "+e);
       }
    }

    @Override
    public void step(long timeElapsed) {}

    @Override
    public void draw(Graphics g) {
       this.Fundo.draw(g, 0, 0); 
       this.Arcade.draw(g, 500, 300);
       this.Campeonato.draw(g, 510, 400);
       this.Exit.draw(g, 525, 500);
    
    }
    
    @Override
    public void load() {}
    @Override
    public void unload() {}
    @Override
    public void start() {}
    @Override
    public void stop() {}
}
