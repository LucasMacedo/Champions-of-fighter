package Cenarios;

import javaPlayExtras.GeraCenario;
import Personagens.Jogador;
import Personagens.Inimigo;
import java.awt.Graphics;
import javaPlay.GameStateController;
import javaPlay.Imagem;
import javaPlayExtras.ObjetoComGravidade;
import javax.swing.JOptionPane;

public class Cenario implements GameStateController{
    private GeraCenario cenarioComParede;
    
    private Imagem fundoMario;
    private Imagem fundoInimigo2;
    private Imagem fundoInimigo3;
    private Imagem fundoInimigo4;
    private Imagem fundoInimigo5;
    private Imagem fundoInimigo6;
    private Imagem fundoInimigo7;
    private Imagem fundoInimigo8;
    
    Jogador jogador;
    Inimigo inimigo;
    
    public Cenario(Jogador jogador, Inimigo inimigo){
        this.jogador = jogador;
        this.inimigo = inimigo;
        
        this.cenarioComParede = new GeraCenario();
        
        try {
            this.fundoMario    = new Imagem("resources/cenario1/fundo.jpg");
            this.fundoInimigo2 = new Imagem("resources/cenario1/fundo.jpg");
            this.fundoInimigo3 = new Imagem("resources/cenario1/fundo.jpg");
            this.fundoInimigo4 = new Imagem("resources/cenario1/fundo.jpg");
            this.fundoInimigo5 = new Imagem("resources/cenario1/fundo.jpg");
            this.fundoInimigo6 = new Imagem("resources/cenario1/fundo.jpg");
            this.fundoInimigo7 = new Imagem("resources/cenario1/fundo.jpg");
            this.fundoInimigo8 = new Imagem("resources/cenario1/fundo.jpg");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar a imagem de fundo: "+e);
        }
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
        
        this.inimigo.colisaoTiro(obj1);
        this.jogador.colisaoTiro(obj2);
    }
    
    @Override
    public void step(long timeElapsed) {
        this.jogador.step(timeElapsed);
        this.inimigo.step(timeElapsed);
        
        this.verificaColisao(timeElapsed);
    }
    
    @Override
    public void draw(Graphics g) {
        this.fundoMario.draw(g, -134, 0);
        this.cenarioComParede.draw(g);
        
        this.jogador.draw(g);
        this.inimigo.draw(g);
        
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