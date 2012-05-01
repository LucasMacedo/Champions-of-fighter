
package Principal;

import Cenarios.Cenario;
import Cenarios.Rank;
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
    private Cenario cenario;
    
    private Rank rank;
    
    public CentralDeBatalha() {
        this.rank = new Rank();
        this.cenario = new Cenario();
        
        this.load();
        
        //this.configCenario(EnumPersonagem.NARUTO);
        this.configCenario(this.rank.getRivalSemi());
    }
    
    public Cenario getCenario(){
        return this.cenario;
    }
    
    private void configCenario(EnumPersonagem inimigo){
        this.cenario.setInimigo(inimigo);
    }
    
    public Rank getRank(){
        return this.rank;
    }
    
    @Override
    public void step(long timeElapsed) {
        
    }

    @Override
    public void draw(Graphics g) {}
    
    @Override
    public void load(){}
    @Override
    public void unload() {}
    @Override
    public void start() {}
    @Override
    public void stop() {}
}
