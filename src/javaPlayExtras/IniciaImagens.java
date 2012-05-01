package javaPlayExtras;

import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.Imagem;
import javax.swing.JOptionPane;

public class IniciaImagens{
    public IniciaImagens(){
        try {
            Imagem imagem;
            
            imagem = new Imagem("resources/cenario/avatar.jpg");
            imagem = new Imagem("resources/cenario/cenario.jpg");
            imagem = new Imagem("resources/cenario/chao.png");
            imagem = new Imagem("resources/cenario/menu.jpg");
            imagem = new Imagem("resources/cenario/tempo.png");
            
            imagem = new Imagem("resources/personagem/barra/avatar.png");
            imagem = new Imagem("resources/personagem/barra/barra.png");
            
            imagem = new Imagem("resources/personagem/jogador/jogador.png");
            imagem = new Imagem("resources/personagem/jogador/especial.png");
            
            imagem = new Imagem("resources/personagem/impacto.png");
            
            imagem = new Imagem("resources/personagem/inimigo/chefao.png");
            imagem = new Imagem("resources/personagem/inimigo/ichigo.png");
            imagem = new Imagem("resources/personagem/inimigo/mario.png");
            imagem = new Imagem("resources/personagem/inimigo/naruto.png");
            imagem = new Imagem("resources/personagem/inimigo/tiroChefao.png");
            imagem = new Imagem("resources/personagem/inimigo/tiroIchigo.png");
            imagem = new Imagem("resources/personagem/inimigo/tiroMario.gif");
            imagem = new Imagem("resources/personagem/inimigo/tiroNaruto.gif");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, " Erro ao carregar uma das imagens. Certifique-se de possuir a pasta resources no diretorio do arquivo Champions_Fighter.jar! \n "+
                                                e);
            System.exit(0);
        }
    }
}
