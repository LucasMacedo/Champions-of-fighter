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
    
    private Imagem fundoAtual;
    
    Jogador jogador;
    Inimigo inimigo;
    
    public Cenario(){
        this.jogador = new Jogador();
        
        this.cenarioComParede = new GeraCenario();
        this.load();
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
    
    public void setInimigo(Imagem imgAtual, Inimigo inimigo){
        this.fundoAtual = imgAtual;
        this.inimigo = inimigo;
    }
    
    @Override
    public void step(long timeElapsed) {
        this.jogador.step(timeElapsed);
        this.inimigo.step(timeElapsed);
        
        this.verificaColisao(timeElapsed);
    }
    
    @Override
    public void draw(Graphics g) {
        this.fundoAtual.draw(g, 0, 2);
        this.cenarioComParede.draw(g);
        
        this.inimigo.draw(g);
        this.jogador.draw(g);
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