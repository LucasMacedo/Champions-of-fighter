package Principal;

import Cenarios.MenuEntrada;
import Cenarios.MenuStart;
import Cenarios.Rank;
import javaPlay.GameEngine;
import javaPlay.Imagem;

public class Main{
    public static void main(String[] args) throws Exception {    
        Imagem fundoAtual;
        
        Rank rank = new Rank();
        
        GameEngine.getInstance().addGameStateController(1, new MenuEntrada());
        GameEngine.getInstance().addGameStateController(2, new MenuStart());
        GameEngine.getInstance().addGameStateController(3, rank);
        GameEngine.getInstance().addGameStateController(4, rank.getCenario());
    
        GameEngine.getInstance().setStartingGameStateController(4);
        
        GameEngine.getInstance().setFramesPerSecond(30);
        GameEngine.getInstance().run();
    }
}
