
package Principal;

import Cenarios.Cenario;
import Cenarios.Rank;
import javaPlayExtras.EnumPersonagem;
import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlayExtras.Som;

public class CentralDeBatalha implements GameStateController{
    private Cenario cenario;
    private Som som;
    private Rank rank;
    
    private int contRank = 0;
    
    public CentralDeBatalha(Som som) {
        this.som = som;
        this.som.play();
        
        this.rank = new Rank(this.som);
        this.cenario = new Cenario();
        
        this.load();
        this.configCenario(EnumPersonagem.ICHIGO);
        //this.configCenario(this.rank.getRivalSemi());
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
        if(this.cenario.acabou()){
            this.contRank ++;
            this.cenario = new Cenario();
            if(this.contRank == 1){
                this.configCenario(this.rank.getRivalFinal());
                this.rank.addFinal();
                GameEngine.getInstance().setNextGameStateController(5);
            }
            if(this.contRank == 2){
                this.configCenario(EnumPersonagem.CHEFAO);
                this.rank.addChefao();
                GameEngine.getInstance().setNextGameStateController(5);
            }
            if(this.contRank == 3){
                this.rank.venceu();
                GameEngine.getInstance().setNextGameStateController(6);
            }
        }
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
