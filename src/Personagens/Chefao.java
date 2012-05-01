package Personagens;

import java.awt.Graphics;
import javaPlay.Imagem;
import javaPlay.Sprite;
import javaPlayExtras.BarraStatus;
import javaPlayExtras.EnumPersonagem;
import javax.swing.JOptionPane;

public class Chefao extends Inimigo{
    public Chefao(){
        this.load();
        super.load();
    }
    
    public void load(){
        this.tipoPersonagem = EnumPersonagem.CHEFAO;
        
        try {
            this.imgPersonagem = new Sprite("resources/personagem/inimigo/chefao.png", 0, 0, 48, 87);
            this.imgBarra   = new BarraStatus(17, 30, this.tipoPersonagem);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: "+e);
        }
        
        this.x = 700;
        this.y = 400;
    }
    
    
    @Override
    protected void animacaoPula() {
        this.imgPersonagem.setWidth(50);
        
        this.imgPersonagem.setCurrAnimFrameWidth(0);
        this.imgPersonagem.setCurrAnimFrameHeight(1);
    }

    @Override
    protected void animacaoAnda() {
        
    }

    @Override
    protected void animacaoEspecial() {
        this.imgPersonagem.setWidth(74);
        
        this.imgPersonagem.setCurrAnimFrameWidth(0);
        this.imgPersonagem.setCurrAnimFrameHeight(3);
        
        this.timeEllapsed = 0;
    }

    @Override
    protected boolean animacaoBate() {
        if(this.timeEllapsed+20 < 100){
            this.imgPersonagem.setWidth(74);
            this.imgPersonagem.setCurrAnimFrameHeight(2);
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
        this.imgPersonagem.setWidth(50);
        
        this.imgPersonagem.setCurrAnimFrameWidth(0);
        this.imgPersonagem.setCurrAnimFrameHeight(0);
    }
}
