package Cenarios;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlay.Mouse;
import javaPlay.Sprite;

public class MenuCreditos implements GameStateController {
    private Sprite imgCreditos;
    
    public MenuCreditos(){
        this.load();
    }
    
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
            if(tipo.equals("imgTorneio")){
                GameEngine.getInstance().setNextGameStateController(3);
            }
            if(tipo.equals("imgVersus")){
                System.out.println("imgVersus");
            }
            if(tipo.equals("imgExit")){
                GameEngine.getInstance().setNextGameStateController(1);
            }
        }
    }
    
    @Override
    public void step(long timeElapsed) {
        //this.verificaMouse(imgCreditos, "imgCreditos");
    }
    
    @Override
    public void draw(Graphics g) {
        this.imgCreditos.draw(g, 0, 0);
    }
    
    @Override
    public void load() {
        try {
            this.imgCreditos = new Sprite("resources/cenario/cenario.jpg", 7, 0, 800, 600);
        } catch (Exception ex) {}
    }
    @Override
    public void unload() {}
    @Override
    public void start() {}
    @Override
    public void stop() {}
}
