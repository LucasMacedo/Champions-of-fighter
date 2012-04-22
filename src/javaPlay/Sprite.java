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
    
    public int getCurrAnimFrameH(){
        return (currAnimFrameH);
    }
    
    public int getCurrAnimFrameV(){
        return (currAnimFrameV);
    }
    
    public void setCurrAnimFrameH(int frame){
        currAnimFrameH = frame;
    }
    
    public void setCurrAnimFrameV(int frame){
        currAnimFrameV = frame;
    }
    
    public void setAnimFrameWidth(int width){
        animFrameWidth = width;
    }
    
    public void setAnimFrameHeight(int height){
        animFrameWidth = height;
    }
    
    public int getAnimFrameWidth(){
        return animFrameWidth;
    }
    
    public int getAnimFrameHeight(){
        return animFrameWidth;
    }

    public int getWidth() {
        return this.animFrameWidth;
    }

    public int getHeight() {
        return this.animFrameHeight;
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
    
    public void drawFlipped(Graphics g, int x, int y) {
        x -= (image.getWidth(null)/4);
        g.drawImage(image, 
                    (image.getWidth(null))/2 + x, y, 
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
