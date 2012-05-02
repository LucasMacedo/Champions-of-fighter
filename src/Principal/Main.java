package Principal;

import Cenarios.MenuCreditos;
import Cenarios.MenuPrincipal;
import Cenarios.MenuModoGame;
import javaPlay.GameEngine;
import javaPlayExtras.IniciaImagens;
import javaPlayExtras.Som;

public class Main{
    public static void main(String[] args) throws Exception {
        Som som = new Som("resources/audio/cenario/menu.wav", true);
        CentralDeBatalha centralDeBatalha = new CentralDeBatalha(som);
        
        new IniciaImagens();
        
        GameEngine.getInstance().addGameStateController(1, new MenuPrincipal(som));
        GameEngine.getInstance().addGameStateController(2, new MenuModoGame());
        GameEngine.getInstance().addGameStateController(3, new MenuCreditos());
        GameEngine.getInstance().addGameStateController(4, centralDeBatalha.getRank());
        GameEngine.getInstance().addGameStateController(5, centralDeBatalha.getCenario());
        GameEngine.getInstance().addGameStateController(6, centralDeBatalha);
        
        GameEngine.getInstance().setStartingGameStateController(1);
        
        GameEngine.getInstance().setFramesPerSecond(30);
        GameEngine.getInstance().run();
    }
}
