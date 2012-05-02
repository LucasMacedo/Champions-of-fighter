package Cenarios;

import Personagens.Chefao;
import Personagens.Ichigo;
import javaPlayExtras.GeraCenario;
import Personagens.Jogador;
import Personagens.Inimigo;
import Personagens.Mario;
import Personagens.Naruto;
import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlay.Sprite;
import javaPlayExtras.AudioPlayer;
import javaPlayExtras.EnumAcao;
import javaPlayExtras.EnumPersonagem;
import javaPlayExtras.ObjetoComGravidade;
import javaPlayExtras.Som;
import javax.swing.JOptionPane;

public class Cenario implements GameStateController{
    private GeraCenario cenarioComParede;
    
    private boolean contaRound;
    
    private Sprite imgCenario;
    private Sprite tempo1;
    private Sprite tempo2;
    private Sprite rounds;
    
    private long timeElapsed;
    private int minuto = 51;
    private int round = 1;
    
    private int contTempoRounds;
    
    private Som som;
    
    private Jogador jogador;
    private Inimigo inimigo;
    
    public Cenario(){
        try {
            this.imgCenario = new Sprite("resources/cenario/cenario.jpg", 0, 0, 800, 600);
            this.tempo1     = new Sprite("resources/cenario/tempo.png", 0, 0, 10, 16);
            this.tempo2     = new Sprite("resources/cenario/tempo.png", 0, 0, 10, 16);
            
            this.rounds     = new Sprite("resources/cenario/rounds.png",0, 2, 384, 77);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
        
        this.cenarioComParede = new GeraCenario();
        
        this.contTempoRounds = 0;
        this.jogador = new Jogador();
        this.setInimigo(EnumPersonagem.MARIO);
    }
    
    private void verificaColisao(long timeElapsed){
        ObjetoComGravidade obj1 = this.cenarioComParede.verificaColisao(this.jogador);
        ObjetoComGravidade obj2 = this.cenarioComParede.verificaColisao(this.inimigo);
        
        this.jogador.setX(obj1.getX());
        this.jogador.setY(obj1.getY());
        if(obj1.noChao()){
            this.jogador.chegouChao();
        }
        this.inimigo.setX(obj2.getX());
        this.inimigo.setY(obj2.getY());
        if(obj2.noChao()){
            this.inimigo.chegouChao();
        }
        
        // Colisao Tiro
        this.inimigo.colisaoTiro(this.jogador);
        if(this.inimigo.existeColisaoTiro()){
            this.jogador.apanha(this.inimigo.getForcaTiro());
            AudioPlayer.play("resources/audio/tiro/excellent.wav");
        }
        this.jogador.colisaoTiro(this.inimigo);
        if(this.jogador.existeColisaoTiro()){
            this.inimigo.apanha(this.jogador.getForcaTiro());
            AudioPlayer.play("resources/audio/tiro/excellent.wav");
        }
        // Colisao Bate
        if(this.jogador.getAcao() == EnumAcao.BATE){
            this.jogador.setAcao(EnumAcao.NORMAL);
            if(this.jogador.existeColisaoSoco(this.inimigo)){
                this.inimigo.apanha(this.jogador.getForcaSoco());
                this.jogador.andaFrente();
            }
            return;
        }
        if(this.inimigo.getAcao() == EnumAcao.BATE){
            this.inimigo.setAcao(EnumAcao.NORMAL);
            if(this.inimigo.existeColisaoSoco(this.jogador)){
                this.jogador.apanha(this.inimigo.getForcaSoco());
                int cont = 0;
                while(cont < 10){
                    this.inimigo.andaTras();
                    cont ++;
                }
            }
            return;
        }
        
        this.jogador.colisaoPersonagem(this.inimigo.temColisao(this.jogador.getRectangle()));
        
        if(this.inimigo.colisaoPersonagem(this.jogador.getRectangle())){
            if(this.jogador.getAcao() == EnumAcao.BATE){
                this.jogador.andaFrente();
                System.out.println("Jogador bateu!");
            }
            if(this.inimigo.getAcao() == EnumAcao.BATE){
                this.inimigo.andaTras();
            }
            if(this.jogador.getAcao() == EnumAcao.ANDATRAZ && this.inimigo.getAcao() == EnumAcao.ANDAFRENTE){
                this.jogador.andaFrente();
                this.inimigo.andaTras();
                return;
            }
            if(this.jogador.getAcao() == EnumAcao.ANDATRAZ){
                this.jogador.andaFrente();
                return;
            }
            if(this.inimigo.getAcao() == EnumAcao.ANDAFRENTE){
                this.inimigo.andaTras();
                return;
            }
        }
    }
    
    public void setInimigo(EnumPersonagem inimigo){
        switch (inimigo){
            case MARIO:
                this.inimigo = new Mario();
                this.imgCenario.setCurrAnimFrameWidth(0);
                
                this.som = new Som("resources/audio/cenario/mario.wav", true);
                break;
            case NARUTO:
                this.inimigo = new Naruto();
                this.imgCenario.setCurrAnimFrameWidth(3);
                
                this.som = new Som("resources/audio/cenario/naruto.wav", true);
                break;
            case ICHIGO:
                this.inimigo = new Ichigo();
                this.imgCenario.setCurrAnimFrameWidth(2);
                
                this.som = new Som("resources/audio/cenario/ichigo.wav", true);
                break;
            case CHEFAO:
                this.inimigo = new Chefao();
                this.imgCenario.setCurrAnimFrameWidth(1);
                
                this.som = new Som("resources/audio/cenario/chefao.wav", true);
                break;
        }
    }
    
    private void verificaFimJogo(){
        if(this.timeElapsed >= 900){
            if(this.jogador.getVezesMorto() >= 2 || this.inimigo.getVezesMorto() >= 2){
                this.contTempoRounds ++;
                
                this.jogador.acabaJogo();
                this.inimigo.acabaJogo();
                
                if(this.contTempoRounds >= 50){
                    if(this.jogador.getVezesMorto() >= 2){
                        GameEngine.getInstance().setNextGameStateController(1);
                    }else{
                        GameEngine.getInstance().setNextGameStateController(4);
                    }
                }
            }
        }
        
        if(this.timeElapsed >= 300){
            this.rounds.setCurrAnimFrameHeight(2);
        }
        
        if(this.jogador.getVezesMorto() >= 2){
            this.rounds.setCurrAnimFrameHeight(0);
        }
    }
    
    private void verificaStatusJogo(){
        if(this.minuto == 51){
            AudioPlayer.play("resources/audio/cenario/round"+this.round+".wav");
            this.minuto --;
        }
        if(this.minuto < 0 || this.jogador.estaMorto() || this.inimigo.estaMorto()){
            if(this.jogador.estaMorto() || this.inimigo.estaMorto()){
                this.minuto = 0;
            }
            this.jogador.pausaJogo(true);
            this.inimigo.pausaJogo(true);
            
            this.minuto = 0;
            this.timeElapsed -= 100;
            
            if( ( this.jogador.reviveu() && this.inimigo.reviveu() ) ){
                this.minuto = 51;
                
                this.jogador.pausaJogo(false);
                this.inimigo.pausaJogo(false);
                
                this.contaRound = false;
                this.round ++;
                
                this.rounds.setCurrAnimFrameHeight(1);
            }else{
                this.jogador.revive();
                this.inimigo.revive();
            }
            
            if(!this.contaRound){
                if( this.jogador.getHP() > this.inimigo.getHP() ){
                    this.inimigo.setVezesMorto();
                    
                    this.contaRound = true;
                }
                if( this.jogador.getHP() < this.inimigo.getHP() ){
                    this.jogador.setVezesMorto();
                    
                    this.contaRound = true;
                }
                this.inimigo.setAcao(EnumAcao.MORTO);
                this.jogador.setAcao(EnumAcao.MORTO);
            }
        }
    }
    
    private void configTempo(){
        if(this.timeElapsed >= 900){
            this.timeElapsed = 0;
        }
        
        if(this.minuto < 0){
            this.minuto = 0;
        }
        
        String minuto = this.minuto+"";
        if(this.minuto < 10){
            minuto = "0"+this.minuto;
        }
        
        this.tempo1.setCurrAnimFrameWidth(Integer.parseInt((minuto).substring(0, 1)));
        this.tempo2.setCurrAnimFrameWidth(Integer.parseInt((minuto).substring(1, 2)));
    }
    
    public boolean acabou(){
        return (this.jogador.getVezesMorto() >= 2 || this.inimigo.getVezesMorto() >= 2);
    }
    
    public EnumPersonagem getVencedor(){
        if(this.jogador.getVezesMorto() >= 2){
            return EnumPersonagem.JOGADOR;
        }
        return this.inimigo.getTipoPersonagem();
    }
    
    @Override
    public void step(long timeElapsed) {
        this.jogador.step(timeElapsed);
        this.inimigo.eventos(this.jogador);
        this.inimigo.step(timeElapsed);
        
        this.verificaColisao(timeElapsed);
        
        this.timeElapsed += timeElapsed;
        
        if(this.timeElapsed >= 900 && !this.inimigo.estaMorto() && !this.jogador.estaMorto()){
            this.minuto --;
        }
        
        this.verificaFimJogo();
        this.verificaStatusJogo();
        this.configTempo();
        
        this.som.play();
    }
    
    @Override
    public void draw(Graphics g) {
        this.imgCenario.draw(g, 0, 2);
        this.cenarioComParede.draw(g);
        
        this.inimigo.draw(g);
        this.jogador.draw(g);
        
        this.tempo1.draw(g, 390, 50);
        this.tempo2.draw(g, 402, 50);
        
        this.rounds.draw(g, 220, 200);
    }
    
    @Override
    public void load() {}    
    @Override
    public void unload() {}
    @Override
    public void stop() {}
    @Override
    public void start() {}
}