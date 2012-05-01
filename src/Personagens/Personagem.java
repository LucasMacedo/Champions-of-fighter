package Personagens;

import com.sun.org.glassfish.gmbal.Impact;
import java.awt.Rectangle;
import java.util.Random;
import javaPlay.Imagem;
import javaPlay.Sprite;
import javaPlayExtras.AudioPlayer;
import javaPlayExtras.BarraStatus;
import javaPlayExtras.EnumAcao;
import javaPlayExtras.EnumPersonagem;
import javaPlayExtras.Impacto;
import javaPlayExtras.ObjetoComGravidade;
import javaPlayExtras.Som;
import javaPlayExtras.Tiro;
import javax.swing.JOptionPane;

public abstract class Personagem extends ObjetoComGravidade{
    protected Sprite imgPersonagem;
    protected Impacto impacto;
    
    protected int xInicial;
    protected int yInicial;
    
    protected Som som;
    
    protected EnumPersonagem tipoPersonagem;
    
    protected Tiro tiro;
    protected BarraStatus imgBarra;
    
    protected double forca = 25;
    protected double inteligencia = 70;
    
    protected long timeEllapsed = 0;
    
    protected int forcaImpulso = 38;
    protected int cont = 0;
    
    protected int vezesMorto = 0;
    
    protected int xInimigo = 0;
    
    protected boolean imgPersonagemVolta;
    protected boolean jogoPausado;
    
    protected boolean colisaoPersonagem;
    
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
        this.impacto = new Impacto();
        this.hp = 144;
        this.sp = 87;
        //this.mudaImagem();
    }
    
    public int getVezesMorto(){
        return this.vezesMorto;
    }
    
    public void setVezesMorto(){
        this.vezesMorto ++;
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
            this.sp = 0;
            return;
        }
        
        this.eventosTeclado();
        
    }
    
    private void mudaImagem(){
        this.widthAnterior  = this.getWidth();
        this.heightAnterior = this.getHeight();
        
        this.setHeight( this.imgPersonagem.getHeight() );
        this.setWidth ( this.imgPersonagem.getWidth() );
        
        if(this.tipoPersonagem == EnumPersonagem.JOGADOR/* && this.acao != EnumAcao.APANHA*/){
            if(this.getWidth() < this.widthAnterior){
                this.x -= (this.getWidth() - this.widthAnterior);
            }
            if(this.getWidth() > this.widthAnterior){
                this.x += this.widthAnterior - this.getWidth();
            }
        }
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
        this.animacaoApanha();
        this.mudaImagem();
        
        if(this.estaMorto()){
            this.vezesMorto ++;
            this.morre();
            return;
        }
    }
    
    public void revive(){
        System.out.println(this.acao);
        this.animacaoNormal();
        
        if( !this.estaNoChao ){
            this.y += 70;
        }
        
        if(this.y < 0){
            this.y = yInicial;
        }
        
        if(this.tipoPersonagem == EnumPersonagem.JOGADOR){
            if(this.x < this.xInicial){
                this.x += 10;
            }
            if(this.x > this.xInicial){
                this.x = this.xInicial;
            }
        }else{
            if(this.x > this.xInicial){
                this.x -= 10;
            }
            if(this.x < this.xInicial){
                this.x = this.xInicial;
            }
        }
        
        this.hp += 10;
        this.sp += 5;
        
        if(this.hp > 144){
            this.hp = 144;
        }
        if(this.sp > 87){
            this.sp = 87;
        }
        
        if(this.x == this.xInicial && this.hp == 144 && this.sp == 87){
            this.acao = EnumAcao.NORMAL;
        }
    }
    
    public boolean reviveu(){
        return (this.x == this.xInicial && this.hp == 144 && this.sp == 87);
    }
    
    public void bate(){
        if(this.acao == EnumAcao.APANHA){
            this.normal();
            return;
        }
        this.acao = EnumAcao.BATE;
        
        this.animacaoBate();
        this.mudaImagem();
    }
    
    private void morre(){
        this.acao = EnumAcao.MORTO;
        //if(this.vezesMorto == 2){
            this.animacaoMorre();
        //o}
        this.mudaImagem();
    }
    
    public void pula(){
        
        if( this.estaSubindo() || this.estaDescendo()){
            return;
        }
        
        this.impulso(this.forcaImpulso);
        this.animacaoPula();
        this.acao = EnumAcao.PULA;
        this.mudaImagem();
    }
    
    public void especial(){
        if(this.acao == EnumAcao.APANHA){
            this.normal();
            return;
        }
        
        if(this.sp > 20 && !this.tiro.getExiste()){
            this.acao = EnumAcao.ESPECIAL;
            this.animacaoEspecial();
            this.tiro = new Tiro(this.x, this.y, this.width, this.height, this.tipoPersonagem);
            this.sp -= 20;
        }else{
            this.cont ++;
            if(this.cont >= 12){
                this.normal();                
                this.cont = 0;
            }
        }
        this.mudaImagem();
    }
    
    public void andaFrente(){
        this.x += 8;
        this.animacaoAnda();
        this.acao = EnumAcao.ANDAFRENTE;
        this.mudaImagem();
    }
    
    public void andaTras(){
        this.x -= 8;
        this.animacaoAnda();
        this.acao = EnumAcao.ANDATRAZ;
        this.mudaImagem();
    }
    
    public void normal(){
        this.animacaoNormal();
        this.acao = EnumAcao.NORMAL;
        this.mudaImagem();
    }
    
    public void pausaJogo(boolean pausa){
        this.jogoPausado = pausa;
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
    
    public void colisaoPersonagem(boolean colisao){
        this.colisaoPersonagem = colisao;
    }
    
    public void setXInimigo(int xInimigo){
        this.xInimigo = xInimigo;
    }
    
    public Rectangle getRectangleMenor(){
        if(this.tipoPersonagem == EnumPersonagem.JOGADOR){
            return new Rectangle(this.x-2, y, width-2, height);
        }
        return new Rectangle(this.x+2, y, width+2, height);
    }
    
    public boolean colisaoPersonagem(Rectangle rec){
        return this.getRectangleMenor().intersects(rec);
    }
    
    public void novoImpacto(){
        this.impacto = new Impacto(this.x, this.y, this.width, this.height, this.tipoPersonagem);
    }
}
