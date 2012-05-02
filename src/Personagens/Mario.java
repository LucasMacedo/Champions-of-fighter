package Personagens;

import java.awt.Graphics;
import javaPlay.Imagem;
import javaPlay.Sprite;
import javaPlayExtras.AudioPlayer;
import javaPlayExtras.BarraStatus;
import javaPlayExtras.EnumPersonagem;
import javaPlayExtras.Som;
import javax.swing.JOptionPane;

public class Mario extends Inimigo{
    public Mario(){
        super.load();
        this.load();
    }
    
    public void load(){
        this.tipoPersonagem = EnumPersonagem.MARIO;
        
        try {
            this.imgPersonagem = new Sprite("resources/personagem/inimigo/mario.png", 0, 0, 40, 82);
            this.imgBarra   = new BarraStatus(17, 30, this.tipoPersonagem);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
        
        this.x = 700;
        this.y = 400;
    }
    
    
    @Override
    protected void animacaoPula() {
        this.imgPersonagem.setWidth(55);
        
        this.imgPersonagem.setCurrAnimFrameWidth(0);
        this.imgPersonagem.setCurrAnimFrameHeight(1);
        
        
        
        //AudioPlayer.play("resources/audio/personagem/mario-pula.wav");
    }

    @Override
    protected void animacaoAnda() {
        
    }

    @Override
    protected void animacaoEspecial() {
        this.imgPersonagem.setWidth(62);
        
        this.imgPersonagem.setCurrAnimFrameWidth(0);
        this.imgPersonagem.setCurrAnimFrameHeight(2);
        
        this.timeEllapsed = 0;
    }

    @Override
    protected boolean animacaoBate() {
        if(this.timeEllapsed+20 < 100){
            this.imgPersonagem.setWidth(62);
            this.imgPersonagem.setCurrAnimFrameHeight(3);
            this.imgPersonagem.setCurrAnimFrameWidth(0);
            return true;
        }else{
            this.normal();
            return false;
        }
    }

    @Override
    protected void animacaoApanha() {
        /*this.imgPersonagem.setWidth(0);
        
        this.imgPersonagem.setCurrAnimFrameWidth(0);
        this.imgPersonagem.setCurrAnimFrameHeight(0);*/
    }

    @Override
    protected void animacaoMorre() {
        /*this.imgPersonagem.setWidth(0);
        this.imgPersonagem.setCurrAnimFrameHeight(0);
        this.imgPersonagem.setCurrAnimFrameWidth(0);*/
    }

    @Override
    protected void animacaoNormal() {
        this.imgPersonagem.setWidth(40);
        
        this.imgPersonagem.setCurrAnimFrameWidth(0);
        this.imgPersonagem.setCurrAnimFrameHeight(0);
    }
}
