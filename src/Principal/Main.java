package Principal;

import Cenarios.Cenario;
import Cenarios.MenuEntrada;
import Cenarios.MenuStart;
import Cenarios.Rank;
import Personagens.*;
import javaPlay.GameEngine;
import javaPlayExtras.AudioPlayer;

public class Main{

    public static void main(String[] args) {
        Jogador jogador = new Jogador();
        Inimigo inimigo = new Inimigo();
        
        GameEngine.getInstance().addGameStateController(1, new MenuEntrada() );
        GameEngine.getInstance().addGameStateController(2, new MenuStart());
        GameEngine.getInstance().addGameStateController(3, new Rank());
        GameEngine.getInstance().addGameStateController(4, new Cenario(jogador, inimigo));
    
        GameEngine.getInstance().setStartingGameStateController(3);

        //Executa um som ".wav" .mp3 não funciona
        //AudioPlayer.play("resources/bgm/fundo.wav", true);
    
        GameEngine.getInstance().setFramesPerSecond(30);
        GameEngine.getInstance().run();
    }
}
