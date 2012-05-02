package Cenarios;

import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlay.Imagem;
import javaPlay.Mouse;
import javaPlay.Sprite;
import javaPlayExtras.Som;
import javax.swing.JOptionPane;

public class MenuPrincipal implements GameStateController{ 
    private Sprite imgStartGame;
    private Sprite imgExit;
    private Sprite FundoMenu;
    
    private Som som;
    
    public MenuPrincipal(Som som){ 
        try {
            this.imgStartGame = new Sprite("resources/cenario/menu.jpg", 0, 0, 109, 40);
            this.imgExit      = new Sprite("resources/cenario/menu.jpg", 0, 1, 74, 40);
            this.FundoMenu = new Sprite("resources/cenario/cenario.jpg", 4, 0, 800, 600);
          } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar a imagem de fundo: "+e);
        }
        this.som = som;
        this.som.play();
    }
    
    @Override
    public void step(long timeElapsed) {
        this.verificaMouse(this.imgExit, "imgExit");
        this.verificaMouse(this.imgStartGame, "imgStart");
    }

    @Override
    public void draw(Graphics g) {
        this.FundoMenu.draw(g, 0, 0);
        this.imgExit.draw(g, 500, 400);
        this.imgStartGame.draw(g, 500, 300);
    }
     
    @Override
    public void load(){}
    @Override
    public void stop() {}
    @Override
    public void start() {}
    @Override
    public void unload() {}

    public void verificaMouse(Sprite sprite, String tipo) {
        Mouse mouse = GameEngine.getInstance().getMouse();
        Point point = new Point();
        point = mouse.getMousePos();
        
        if( (point.getX() >= sprite.getX()) && (point.getY() >= sprite.getY()) && 
            (point.getX() <= sprite.getX()+sprite.getWidth()) && (point.getY() <= sprite.getY()+sprite.getHeight()) ){
            sprite.setCurrAnimFrameWidth(1);
        }else{
            sprite.setCurrAnimFrameWidth(0);
        }
        
        if(!mouse.isLeftButtonPressed()){
            return;
        }
        
        point = mouse.getMousePos();
        Rectangle rect = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
        
        if(rect.contains(point)){
            if(tipo.equals("imgStart")){
                GameEngine.getInstance().setNextGameStateController(4);
            }
            if(tipo.equals("imgExit")){
                System.exit(0);
            }
        }
    }
}
