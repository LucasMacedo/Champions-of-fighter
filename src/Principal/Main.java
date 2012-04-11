package Principal;

import Cenarios.Arena;
import Personagens.*;
import javaPlay.GameEngine;
import javaPlayExtras.AudioPlayer;

public class Main{

    public static void main(String[] args) {
        Jogador jogador = new Jogador();
        Inimigo inimigo = new Inimigo();
        
        GameEngine.getInstance().addGameStateController(1, new Arena(jogador, inimigo));
    
        GameEngine.getInstance().setStartingGameStateController(1);

        //Executa um som ".wav" .mp3 não funciona
        //AudioPlayer.play("resources/bgm/fundo.wav", true);
    
        GameEngine.getInstance().setFramesPerSecond(30);
        GameEngine.getInstance().run();
    }
}
