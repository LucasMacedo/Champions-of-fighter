package Cenarios;

import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlay.Imagem;
import javaPlay.Mouse;
import javaPlay.Sprite;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MenuModoGame implements GameStateController{
    
    private Sprite imgTorneio;
    private Sprite imgVersus;
    private Sprite imgExit;
    private Sprite imgFundo;
    
    public MenuModoGame(){
        try{
            this.imgTorneio = new Sprite("resources/cenario/menu.jpg", 0, 2, 145, 40);
            this.imgVersus  = new Sprite("resources/cenario/menu.jpg", 0, 3, 140, 40);
            this.imgExit    = new Sprite("resources/cenario/menu.jpg", 0, 1, 74, 40);
            this.imgFundo   = new Sprite("resources/cenario/cenario.jpg", 5, 0, 800, 600);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao carregar a imagem de fundo: "+e);
        }
        
        /*
        this.imgTorneio.addActionListener(this);
        this.imgTorneio.setActionCommand("torneio");
        
        this.imgVersus.addActionListener(this);
        this.imgVersus.setActionCommand("versus");
        
        this.imgExit.addActionListener(this);
        this.imgExit.setActionCommand("exit");*/
    }

    @Override
    public void step(long timeElapsed) {
        this.verificaMouse(this.imgTorneio, "imgTorneio");
        this.verificaMouse(this.imgVersus, "imgVersus");
        this.verificaMouse(this.imgExit, "imgExit");
    }

    @Override
    public void draw(Graphics g) {
       this.imgFundo.draw(g, 0, 0); 
       this.imgVersus.draw(g, 500, 300);
       this.imgTorneio.draw(g, 510, 400);
       this.imgExit.draw(g, 525, 500);
    
    }
    
    @Override
    public void load() {}
    @Override
    public void unload() {}
    @Override
    public void start() {}
    @Override
    public void stop() {}
    
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
                GameEngine.getInstance().setNextGameStateController(4);
            }
            if(tipo.equals("imgVersus")){
                System.out.println("imgVersus");
            }
            if(tipo.equals("imgExit")){
                GameEngine.getInstance().setNextGameStateController(1);
            }
        }
    }
}
