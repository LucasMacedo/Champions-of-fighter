
package Cenarios;

import Personagens.Inimigo;
import javaPlayExtras.Enum;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import javaPlay.GameStateController;
import javaPlay.Imagem;
import javaPlayExtras.AudioPlayer;
import javax.swing.JOptionPane;

public class Rank implements GameStateController{
    private ArrayList<Enum> listaClassificacao;
    
    private Cenario cenario;
    
    private Imagem rank;
    private Imagem fundoMario;
    private Imagem fundoNaruto;
    private Imagem fundoInimigo3;
    private Imagem fundoChefao;
    
    private Inimigo inimigo;
    
    public Rank() {
        this.listaClassificacao = new ArrayList<Enum>();
        this.cenario = new Cenario();
        
        this.load();
        
        Collections.shuffle(this.listaClassificacao);
        Collections.shuffle(this.listaClassificacao);
        
        this.fundoAtual(Enum.MARIO);
    }
    
    public Cenario getCenario(){
        return this.cenario;
    }
    
    private void fundoAtual(Enum inimigo){
        if(inimigo == Enum.MARIO){
            this.inimigo = new Inimigo(Enum.MARIO);
            this.cenario.setInimigo(this.fundoMario, this.inimigo);
            
            AudioPlayer.play("resources/som/mario-cenario.wav", true);
        }
        if(inimigo == Enum.NARUTO){
            this.inimigo = new Inimigo(Enum.NARUTO);
            this.cenario.setInimigo(this.fundoNaruto, this.inimigo);
            //AudioPlayer.play("resources/som/naruto.wav", true);
        }
        if(inimigo == Enum.INIMIGO3){
            this.inimigo = new Inimigo(Enum.INIMIGO3);
            this.cenario.setInimigo(this.fundoInimigo3, this.inimigo);
            //AudioPlayer.play("resources/som/inimigo3.wav", true);
        }
        if(inimigo == Enum.INIMIGO4){
            this.inimigo = new Inimigo(Enum.INIMIGO4);
            this.cenario.setInimigo(this.fundoInimigo3, this.inimigo);
            //AudioPlayer.play("resources/som/inimigo4.wav", true);
        }
        if(inimigo == Enum.CHEFAO){
            this.inimigo = new Inimigo(Enum.CHEFAO);
            this.cenario.setInimigo(this.fundoChefao, this.inimigo);
            //AudioPlayer.play("resources/som/chefao.wav", true);
        }
    }
    
    @Override
    public void step(long timeElapsed) {
        
    }

    @Override
    public void draw(Graphics g) {
        this.rank.draw(g, -110, 10);
    }
    
    @Override
    public void load() {
        try{
            this.fundoMario    = new Imagem("resources/cenario/mario.jpg");
            this.fundoNaruto   = new Imagem("resources/cenario/naruto.png");
            this.fundoInimigo3 = new Imagem("resources/cenario/mario.jpg");
            this.fundoChefao   = new Imagem("resources/cenario/mario.jpg");
            
            this.rank = new Imagem ("resources/Menu/Rank.png");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao carregar a imagem de fundo: "+e);
        }
        
        int cont = 0;
        while(cont < 2){
            this.listaClassificacao.add(Enum.MARIO);
            this.listaClassificacao.add(Enum.NARUTO);
            this.listaClassificacao.add(Enum.INIMIGO3);
            
            cont ++;
        }
        this.listaClassificacao.add(Enum.INIMIGO4);
        this.listaClassificacao.add(Enum.JOGADOR);
    }
    @Override
    public void unload() {}
    @Override
    public void start() {}
    @Override
    public void stop() {}
}
