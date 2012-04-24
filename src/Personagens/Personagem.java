package Personagens;

import java.awt.Rectangle;
import java.util.Random;
import javaPlay.Imagem;
import javaPlay.Sprite;
import javaPlayExtras.AudioPlayer;
import javaPlayExtras.BarraStatus;
import javaPlayExtras.EnumAcao;
import javaPlayExtras.EnumPersonagem;
import javaPlayExtras.ObjetoComGravidade;
import javaPlayExtras.Tiro;
import javax.swing.JOptionPane;

public abstract class Personagem extends ObjetoComGravidade{
    protected Sprite imgPersonagem;
    
    protected EnumPersonagem tipoPersonagem;
    
    protected Tiro tiro;
    protected BarraStatus imgBarra;
    
    protected double forca = 25;
    protected double inteligencia = 70;
    
    protected long timeEllapsed = 0;
    
    protected int forcaImpulso;
    protected int cont = 0;
    
    protected int vezesMorto = 0;
    
    protected boolean imgPersonagemVolta;
    protected boolean jogoPausado;
    
    protected EnumAcao acao = EnumAcao.NORMAL;
    
    protected abstract void eventosTeclado();
    protected abstract void animacaoNormal();
    protected abstract void animacaoPula();
    protected abstract void animacaoAnda();
    protected abstract void animacaoEspecial();
    protected abstract boolean animacaoBate();
    protected abstract void animacaoApanha();
    protected abstract void animacaoMorre();
    
    protected void load(){
        this.forcaImpulso = 38;
        this.tiro = new Tiro();
        this.hp = 144;
        this.sp = 87;
    }
    
    public void step(long timeEllaped){
        this.timeEllapsed += timeEllaped;
        if(this.timeEllapsed >= 200){
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
    
    private void mudaImagem(){
        this.setHeight( this.imgPersonagem.getHeight() );
        this.setWidth( this.imgPersonagem.getWidth() );
    }
    
    public void apanha(double apanha){
        if(this.estaMorto()){
            return;
        }
        this.hp -= apanha;
        
        if(this.tipoPersonagem == EnumPersonagem.JOGADOR){
            this.x += ((int)apanha)*3;
        }else{
            this.x -= ((int)apanha)*3;
        }
        
        this.acao = EnumAcao.APANHA;
        this.mudaImagem();
        this.animacaoApanha();
        
        
        if(this.estaMorto()){
            this.vezesMorto ++;
            //AudioPlayer.play("resources/som/apanha.wav", true);
            return;
        }
        //AudioPlayer.play("resources/som/apanha.wav", true);
    }
    
    public void revive(){
        this.load();
    }
    
    public void bate(){
        if(this.acao == EnumAcao.APANHA){
            this.normal();
            return;
        }
        if(this.animacaoBate()){
            this.acao = EnumAcao.BATE;
        }
    }
    
    private void morre(){
        this.mudaImagem();
        this.acao = EnumAcao.MORTO;
        if(this.vezesMorto == 2){
            this.animacaoMorre();
        }
    }
    
    public void pula(){
        if( this.estaSubindo() || this.estaDescendo() ){
            return;
        }
        
        this.impulso(this.forcaImpulso);
        this.animacaoPula();
        this.acao = EnumAcao.PULA;
    }
    
    public void especial(EnumPersonagem personagem){
        if(this.acao == EnumAcao.APANHA){
            this.normal();
            return;
        }
        
        if(this.sp > 20 && !this.tiro.getExiste()){
            this.acao = EnumAcao.ESPECIAL;
            this.animacaoEspecial();
            this.tiro = new Tiro(this.x, this.y, this.width, this.height, personagem);
            this.sp -= 20;
        }else{
            this.cont ++;
            if(this.cont >= 12){
                this.normal();                
                this.cont = 0;
            }
        }
    }
    
    public void andaFrente(){
        this.x += 8;
        this.animacaoAnda();
        this.acao = EnumAcao.ANDAFRENTE;
    }
    
    public void andaTras(){
        this.x -= 8;
        this.animacaoAnda();
        this.acao = EnumAcao.ANDATRAZ;
    }
    
    public void normal(){
        this.animacaoNormal();
        this.mudaImagem();
        this.acao = EnumAcao.NORMAL;
    }
    
    public void pausaJogo(boolean pausa){
        this.jogoPausado = pausa;
        this.chegouChao();
    }
    
    public boolean getPausaJogo(){
        return this.jogoPausado;
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
        return ((this.inteligencia*this.inteligencia)*2)/400;
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
        return this.acao;
    }
    
    public void setAcao(EnumAcao acao){
        this.acao = acao;
    }
    
    public Rectangle getRectangle(){
        return new Rectangle(this.x, this.y, this.width, this.height);
    }
    
    public boolean colisaoPersonagem(Personagem p){
        int x = p.getX();
        int y = p.getY();
        
        int width = p.getWidth();
        int height = p.getHeight();
        
        return false;
    }
}
