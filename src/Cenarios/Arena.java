package Cenarios;

import Personagens.Jogador;
import Personagens.Inimigo;
import java.awt.Graphics;
import javaPlay.GameStateController;
import javaPlay.Imagem;
import javaPlayExtras.CenarioComChao;
import javaPlayExtras.ObjetoComGravidade;
import javax.swing.JOptionPane;

public class Arena implements GameStateController{
    private CenarioComChao cenarioComParede;
    private Cenario1 cenario;
    private Imagem fundo;
    
    Jogador jogador;
    Inimigo inimigo;
    
    public Arena(Jogador jogador, Inimigo inimigo){
        this.jogador = jogador;
        this.inimigo = inimigo;
        
        this.cenario = new Cenario1();
        this.cenarioComParede = new CenarioComChao(this.cenario.getPosicao(), this.cenario.getListaImagens());
        try {
            this.fundo = new Imagem("resources/cenario1/fundo.jpg");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
    }
    
    private void verificaColisao(){
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
    }
    
    @Override
    public void step(long timeElapsed) {
        this.jogador.step(timeElapsed);
        this.inimigo.step(timeElapsed);
        
        this.verificaColisao();
    }

    @Override
    public void draw(Graphics g) {
        //g.setColor(Color.red);
        //g.fillRect(0, 0, 800, 600);
        
        this.fundo.draw(g, -134, 0);
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