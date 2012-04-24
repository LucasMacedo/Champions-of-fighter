package Personagens;

import javaPlay.Imagem;
import javaPlay.Sprite;
import javaPlayExtras.BarraStatus;
import javaPlayExtras.EnumPersonagem;
import javax.swing.JOptionPane;

public class Mario extends Inimigo{
    public Mario(){
        this.load();
        super.load();
    }
    
    public void load(){
        try {
            this.imgPersonagem = new Sprite("resources/personagem/inimigo/mario.png", 3, 1, 76, 85);
            this.imgBarra   = new BarraStatus(17, 30);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
        
        this.tipoPersonagem = EnumPersonagem.MARIO;
        
        this.x = 700;
        this.y = 400;
    }
}