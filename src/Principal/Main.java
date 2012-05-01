package Principal;

import Cenarios.MenuCreditos;
import Cenarios.MenuPrincipal;
import Cenarios.MenuModoGame;
import javaPlay.GameEngine;
import javaPlay.Imagem;
import javaPlayExtras.IniciaImagens;

public class Main{
    public static void main(String[] args) throws Exception {    
        CentralDeBatalha centralDeBatalha = new CentralDeBatalha();
        
        GameEngine.getInstance().addGameStateController(1, new MenuPrincipal());
        GameEngine.getInstance().addGameStateController(2, new MenuModoGame());
        GameEngine.getInstance().addGameStateController(3, new MenuCreditos());
        GameEngine.getInstance().addGameStateController(4, centralDeBatalha.getRank());
        GameEngine.getInstance().addGameStateController(5, centralDeBatalha.getCenario());
        
        new IniciaImagens();
        
        GameEngine.getInstance().setStartingGameStateController(1);
        
        GameEngine.getInstance().setFramesPerSecond(30);
        GameEngine.getInstance().run();
    }
}
