package Personagens;

import java.awt.Rectangle;
import java.util.Random;
import javaPlay.Imagem;
import javaPlay.Sprite;
import javaPlayExtras.AudioPlayer;
import javaPlayExtras.BarraStatus;
import javaPlayExtras.EnumAcao;
import javaPlayExtras.ObjetoComGravidade;
import javaPlayExtras.Tiro;
import javax.swing.JOptionPane;

public abstract class Personagem extends ObjetoComGravidade{
    protected Sprite imgPersonagem;
    
    protected Tiro tiro;
    protected BarraStatus imgBarra;
    
    protected double forca = 25;
    protected double inteligencia = 70;
    
    protected long timeEllapsed = 0;
    
    protected int forcaImpulso;
    protected int cont = 0;
    
    protected EnumAcao acao = EnumAcao.NORMAL;
    
    protected abstract void eventosTeclado();
    protected abstract void animacaoPula();
    protected abstract void animacaoAnda();
    protected abstract void animacaoEspecial();
    protected abstract void animacaoBate();
    
    protected void load(){
        this.forcaImpulso = 38;
        this.tiro = new Tiro();
        this.hp = 144;
        this.sp = 87;
    }
    
    public void step(long timeEllaped){
        this.timeEllapsed += timeEllaped;
        if(this.timeEllapsed >= 100){
            if(this.sp < 87){
                this.sp += 0.1;
                if(this.sp > 87){
                    this.sp = 87;
                }
            }
            this.timeEllapsed = 0;
        }
        
        super.step(timeEllaped);
        
        if(this.estaMorto()){
            this.morre();
            return;
        }
        this.eventosTeclado();
    }
    private void mudaImagem(int width, int height){
        this.setHeight( this.imgPersonagem.getHeight() );
        this.setWidth( this.imgPersonagem.getWidth() );
    }
    
    public void apanha(double apanha){
        if(this.estaMorto()){
            return;
        }
        this.hp -= apanha;
        
        this.mudaImagem(0,0);
        //AudioPlayer.play("resources/som/apanha.wav", true);
    }
    
    public void bate(){
        this.animacaoBate();
        this.acao = EnumAcao.BATE;
        
    }
    
    private void morre(){
        this.mudaImagem(0,0);
        //System.out.println("Morreu");
    }
    
    public void pula(){
        if( this.estaSubindo() || this.estaDescendo() ){
            return;
        }
        
        this.impulso(this.forcaImpulso);
        this.animacaoPula();
        this.acao = EnumAcao.PULA;
    }
    
    public void especial(String personagem){
        if(this.sp > 20 && !this.tiro.getExiste()){
            this.animacaoEspecial();
            this.tiro = new Tiro(this.x, this.y, this.width, this.height, personagem);
            this.sp -= 20;
            this.acao = EnumAcao.ESPECIAL;
        }else{
            this.cont ++;
            if(this.cont >= 12){
                this.normal();                
                this.cont = 0;
            }
        }
    }
    
    public void andaFrente(){
        this.x += 13;
        this.animacaoAnda();
        this.acao = EnumAcao.ANDA;
    }
    
    public void andaTras(){
        this.x -= 13;
        this.animacaoAnda();
        this.acao = EnumAcao.ANDA;
    }
    
    public void normal(){
        this.mudaImagem(0,0);
        this.acao = EnumAcao.NORMAL;
    }
    
    public boolean estaMorto(){
        return (this.hp <= 0);
    }
    
    public void verificaColisaoParede(){
        if((this.x + this.width) >= 796){
            this.x = -(this.width- 796);
        }
        if(this.x <= 3){
            this.x = 3;
        }
    }
    
    public void addHP(double hp){
        this.hp += hp;
        if(this.hp > 144){
            this.hp = 144;
        }
    }
    
    public double getHP(){
        return this.hp;
    }
    
    public double getForcaTiro(){
        return ((this.forca*this.forca)*2)/400;
    }
    
    public double getForcaSoco(){
        return ((this.forca*this.forca)*2)/600;
    }
    
    public void colisaoTiro(ObjetoComGravidade obj){        
        this.tiro.colisao(obj);
    }
    
    public boolean existeColisaoTiro(){
        return this.tiro.existeColisao();
    }
    
    public boolean existeColisaoSoco(ObjetoComGravidade obj){
        return obj.temColisao(this.getRectangle());
    }
    
    public EnumAcao getAcao(){
        EnumAcao acao = this.acao;
        this.acao = EnumAcao.NORMAL;
        return acao;
    }
    
    public Rectangle getRectangle(){
        return new Rectangle(this.x, this.y, this.width, this.height);
    }


}
