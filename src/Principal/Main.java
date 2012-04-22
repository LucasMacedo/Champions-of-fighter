package Principal;

import Cenarios.MenuPrincipal;
import Cenarios.MenuModoGame;
import Cenarios.CentralDeBatalha;
import javaPlay.GameEngine;
import javaPlay.Imagem;

public class Main{
    public static void main(String[] args) throws Exception {    
        Imagem fundoAtual;
        
        CentralDeBatalha centralDeBatalha = new CentralDeBatalha();
        
        GameEngine.getInstance().addGameStateController(1, new MenuPrincipal());
        GameEngine.getInstance().addGameStateController(2, new MenuModoGame());
        GameEngine.getInstance().addGameStateController(3, centralDeBatalha);
        GameEngine.getInstance().addGameStateController(4, centralDeBatalha.getCenario());
    
        GameEngine.getInstance().setStartingGameStateController(4);
        
        GameEngine.getInstance().setFramesPerSecond(30);
        GameEngine.getInstance().run();
    }
}
