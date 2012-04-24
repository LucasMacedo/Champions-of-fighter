/*
 * Sprite
 */

package javaPlay;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * @author VisionLab/PUC-Rio
 */
public class Sprite 
{    
    private Image image;
    private int animFrameCount;
    
    private int currAnimFrameH;
    private int currAnimFrameV;
    
    private int animFrameWidth;
    private int animFrameHeight;
    
    private int MAX_COUNT = 50;

    public Sprite(String filename, int animFrameCountWidth, int animFrameCountHeight, int animFrameWidth,
            int animFrameHeight) throws Exception
    {
        image = Toolkit.getDefaultToolkit().getImage(filename);

        int count = 0;

        while(image.getWidth(null) == -1)
        {
            Thread.sleep(1);
            count++;

            if(count == MAX_COUNT)
            {
                throw new Exception("Imagem \""+filename+"\" nao pode ser carregada");
            }
        }

        this.animFrameCount = animFrameCountWidth;
        this.animFrameWidth = animFrameWidth;
        this.animFrameHeight = animFrameHeight;
        
        this.currAnimFrameH = animFrameCountWidth;
        this.currAnimFrameV = animFrameCountHeight;
    }
    
    public int getCurrAnimFrameWidth(){
        return (currAnimFrameH);
    }
    
    public int getCurrAnimFrameHeight(){
        return (currAnimFrameV);
    }
    
    public void setCurrAnimFrameWidth(int frame){
        currAnimFrameH = frame;
    }
    
    public void setCurrAnimFrameHeight(int frame){
        currAnimFrameV = frame;
    }
    
    public int getWidth() {
        return this.animFrameWidth;
    }

    public int getHeight() {
        return this.animFrameHeight;
    }
    
    public void setWidth(int width){
        this.animFrameWidth = width;
    }
    
    public void setHeight(int height){
        this.animFrameHeight = height;
    }
    
    public void draw(Graphics g, int x, int y)
    {
        GameCanvas canvas = GameEngine.getInstance().getGameCanvas();

        //int xpos = canvas.getRenderScreenStartX() + x;
        //int ypos = canvas.getRenderScreenStartY() + y;
        
        int xpos =  x;
        int ypos =  y;

        g.drawImage(image, 
                xpos, ypos, 
                xpos + animFrameWidth, 
                ypos + animFrameHeight,
                
                currAnimFrameH * animFrameWidth,  
                currAnimFrameV * animFrameHeight, 
                
                (currAnimFrameH + 1) * animFrameWidth, 
                (currAnimFrameV + 1) * animFrameHeight, 
                
                null);
    }
    
    public void draw(Graphics g, int x, int y, int width, int height)
    {
        GameCanvas canvas = GameEngine.getInstance().getGameCanvas();

        //int xpos = canvas.getRenderScreenStartX() + x;
        //int ypos = canvas.getRenderScreenStartY() + y;
        
        int xpos =  x;
        int ypos =  y;

        g.drawImage(image, 
                xpos, ypos, 
                xpos + animFrameWidth, 
                ypos + animFrameHeight,
                
                currAnimFrameH * animFrameWidth,  
                currAnimFrameV * animFrameHeight, 
                
                (currAnimFrameH + 1) * animFrameWidth, 
                (currAnimFrameV + 1) * animFrameHeight, 
                
                null);
    }   
    
    public void drawFlipped(Graphics g, int x, int y) {
        x -= (animFrameWidth/2);
        g.drawImage(image, 
                    (animFrameWidth)/4 + x, y, 
                    x + animFrameWidth, 
                    y + animFrameHeight,
                    
                    (currAnimFrameH) * animFrameWidth,
                    (currAnimFrameV) * animFrameHeight, 
                    
                    (currAnimFrameH + 1) * animFrameWidth, 
                    (currAnimFrameV + 1) * animFrameHeight, 
                    
                    null);
    }

    private Sprite(Image image, int animFrameCount,
            int currAnimFrame, int animFrameWidth, int animFrameHeight)
    {
        this.image = image;
        this.animFrameCount = animFrameCount;
        this.currAnimFrameH = currAnimFrame;
        this.animFrameWidth = animFrameWidth;
        this.animFrameHeight = animFrameHeight;
    }

    public Sprite clone()
    {
        return new Sprite(image, animFrameCount, currAnimFrameH,
                animFrameWidth, animFrameHeight);
    }
}
