
package champions_fighter;

import javaPlay.GameEngine;

public class Main {

   
    public static void main(String[] args) {
        GameEngine.getInstance().addGameStateController(1, new Menu());
        GameEngine.getInstance().setStartingGameStateController(1);
        
        
        GameEngine.getInstance().setFramesPerSecond(30);
        GameEngine.getInstance().run();
        
    }
}
