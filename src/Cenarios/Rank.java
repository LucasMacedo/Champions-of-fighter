package Cenarios;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlay.Keys;
import javaPlay.Keyboard;
import javaPlay.Mouse;
import javaPlay.Sprite;
import javaPlayExtras.EnumPersonagem;
import javaPlayExtras.EnumPersonagem;

public class Rank implements GameStateController{
    private ArrayList<EnumPersonagem> listaA;
    private ArrayList<EnumPersonagem> listaB;
    
    private long timeElapsed = 0;
    
    private Sprite imgRank;
    
    private Sprite imgAvatar1;
    private Sprite imgAvatar2;
    private Sprite imgAvatar3;
    private Sprite imgAvatar4;
    
    private Sprite imgAvatarChefao;
    
    private Sprite imgAvatarFinalA;
    private Sprite imgAvatarFinalB;
    private Sprite imgAvatarVenced;
    private Sprite imgAvatarVencedGeral;
    
    public Rank(){
        ArrayList<EnumPersonagem> listaClassificacao = new ArrayList<EnumPersonagem>();
        this.listaA = new ArrayList<EnumPersonagem>();
        this.listaB = new ArrayList<EnumPersonagem>();
        
        listaClassificacao.add(EnumPersonagem.MARIO);
        listaClassificacao.add(EnumPersonagem.NARUTO);
        listaClassificacao.add(EnumPersonagem.ICHIGO);
        
        Collections.shuffle(listaClassificacao);
        
        listaClassificacao.add(EnumPersonagem.JOGADOR);
        
        this.listaA.add(listaClassificacao.get(0));
        this.listaA.add(listaClassificacao.get(3));
        
        this.listaB.add(listaClassificacao.get(1));
        this.listaB.add(listaClassificacao.get(2));
    }
    
    /*public ArrayList<EnumPersonagem> getListaSemiA(){
        return this.listaA;
    }*/
    
    public ArrayList<EnumPersonagem> getListaSemiB(){
        return this.listaB;
    }
    
    public EnumPersonagem getRivalSemi(){
        return this.listaA.get(0);
    }
    
    public EnumPersonagem getRivalFinal(){
        Collections.shuffle(this.listaB);
        Collections.shuffle(this.listaB);
        
        return this.listaB.get(0);
    }
    
    public void verifica(){
        if(this.listaA.get(0) == EnumPersonagem.JOGADOR){
            this.imgAvatar1.setCurrAnimFrameWidth(1);
        }
        if(this.listaA.get(0) == EnumPersonagem.MARIO){
            this.imgAvatar1.setCurrAnimFrameWidth(2);
        }
        if(this.listaA.get(0) == EnumPersonagem.ICHIGO){
            this.imgAvatar1.setCurrAnimFrameWidth(3);
        }
        if(this.listaA.get(0) == EnumPersonagem.NARUTO){
            this.imgAvatar1.setCurrAnimFrameWidth(4);
        }
        
        
        if(this.listaA.get(1) == EnumPersonagem.JOGADOR){
            this.imgAvatar2.setCurrAnimFrameWidth(1);
        }
        if(this.listaA.get(1) == EnumPersonagem.MARIO){
            this.imgAvatar2.setCurrAnimFrameWidth(2);
        }
        if(this.listaA.get(1) == EnumPersonagem.ICHIGO){
            this.imgAvatar2.setCurrAnimFrameWidth(3);
        }
        if(this.listaA.get(1) == EnumPersonagem.NARUTO){
            this.imgAvatar2.setCurrAnimFrameWidth(4);
        }
        
        
        if(this.listaB.get(0) == EnumPersonagem.JOGADOR){
            this.imgAvatar3.setCurrAnimFrameWidth(1);
        }
        if(this.listaB.get(0) == EnumPersonagem.MARIO){
            this.imgAvatar3.setCurrAnimFrameWidth(2);
        }
        if(this.listaB.get(0) == EnumPersonagem.ICHIGO){
            this.imgAvatar3.setCurrAnimFrameWidth(3);
        }
        if(this.listaB.get(0) == EnumPersonagem.NARUTO){
            this.imgAvatar3.setCurrAnimFrameWidth(4);
        }
        
        
        if(this.listaB.get(1) == EnumPersonagem.JOGADOR){
            this.imgAvatar4.setCurrAnimFrameWidth(1);
        }
        if(this.listaB.get(1) == EnumPersonagem.MARIO){
            this.imgAvatar4.setCurrAnimFrameWidth(2);
        }
        if(this.listaB.get(1) == EnumPersonagem.ICHIGO){
            this.imgAvatar4.setCurrAnimFrameWidth(3);
        }
        if(this.listaB.get(1) == EnumPersonagem.NARUTO){
            this.imgAvatar4.setCurrAnimFrameWidth(4);
        }
    }
    
    @Override
    public void step(long timeElapsed) {
        Keyboard key = GameEngine.getInstance().getKeyboard();
        this.verifica();
        
        if(key.keyDown(Keys.ENTER) || key.keyDown(Keys.ESPACO)){
            GameEngine.getInstance().setNextGameStateController(5);
        }
    }

    @Override
    public void draw(Graphics g) {
        this.imgRank.draw(g, 0, 2);
        
        this.imgAvatar1.draw(g, 16, 127);
        this.imgAvatar2.draw(g, 16, 247);
        this.imgAvatar4.draw(g, 16, 369);
        this.imgAvatar3.draw(g, 16, 490);
        
        this.imgAvatarFinalA.draw(g, 216, 186);
        this.imgAvatarFinalB.draw(g, 216, 430);
        
        this.imgAvatarVenced.draw(g, 416, 309);
        this.imgAvatarChefao.draw(g, 616, 309);
        
        this.imgAvatarVencedGeral.draw(g, 516, 460);
    }
    
    @Override
    public void load() {
        try{
            this.imgRank = new Sprite("resources/cenario/cenario.jpg", 6, 0, 800, 600);
            
            this.imgAvatar1 = new Sprite("resources/cenario/avatar.jpg", 0, 0, 139, 99);//Jogador
            this.imgAvatar2 = new Sprite("resources/cenario/avatar.jpg", 0, 0, 139, 99);//Mario
            this.imgAvatar3 = new Sprite("resources/cenario/avatar.jpg", 0, 0, 139, 99);//Ichigo
            this.imgAvatar4 = new Sprite("resources/cenario/avatar.jpg", 0, 0, 139, 99);//Naruto
            
            this.imgAvatarFinalA = new Sprite("resources/cenario/avatar.jpg", 5, 0, 139, 99);
            this.imgAvatarFinalB = new Sprite("resources/cenario/avatar.jpg", 5, 0, 139, 99);
            
            this.imgAvatarChefao = new Sprite("resources/cenario/avatar.jpg", 6, 0, 139, 99);
            this.imgAvatarVenced = new Sprite("resources/cenario/avatar.jpg", 5, 0, 139, 99);
            
            this.imgAvatarVencedGeral = new Sprite("resources/cenario/avatar.jpg", 7, 0, 139, 99);
        }catch (Exception e){
            
        }
    }    
    @Override
    public void unload() {}
    @Override
    public void stop() {}
    @Override
    public void start() {}
}
