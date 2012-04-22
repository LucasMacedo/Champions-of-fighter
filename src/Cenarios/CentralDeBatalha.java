
package Cenarios;

import Personagens.Inimigo;
import javaPlayExtras.EnumPersonagem;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import javaPlay.GameStateController;
import javaPlay.Imagem;
import javaPlay.Sprite;
import javaPlayExtras.AudioPlayer;
import javax.swing.JOptionPane;

public class CentralDeBatalha implements GameStateController{
    private ArrayList<EnumPersonagem> listaClassificacao;
    
    private Sprite imgRank;
    private Cenario cenario;
    
    public CentralDeBatalha() {
        this.listaClassificacao = new ArrayList<EnumPersonagem>();
        this.cenario = new Cenario();
        
        this.load();
        
        Collections.shuffle(this.listaClassificacao);
        Collections.shuffle(this.listaClassificacao);
        
        this.configCenario(EnumPersonagem.MARIO);
    }
    
    public Cenario getCenario(){
        return this.cenario;
    }
    
    private void configCenario(EnumPersonagem inimigo){
        this.cenario.setInimigo(inimigo);
    }
    
    @Override
    public void step(long timeElapsed) {
        
    }

    @Override
    public void draw(Graphics g) {
        this.imgRank.draw(g, 0, 2);
    }
    
    @Override
    public void load() {
        try{
            this.imgRank = new Sprite("resources/cenario/cenario.jpg", 6, 0, 800, 600);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao carregar a imagem de fundo: "+e);
        }
        
        this.listaClassificacao.add(EnumPersonagem.MARIO);
        this.listaClassificacao.add(EnumPersonagem.NARUTO);
        this.listaClassificacao.add(EnumPersonagem.ICHIGO);
        this.listaClassificacao.add(EnumPersonagem.JOGADOR);
    }
    @Override
    public void unload() {}
    @Override
    public void start() {}
    @Override
    public void stop() {}
}
