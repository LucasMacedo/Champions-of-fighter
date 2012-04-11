package Personagens;

import javaPlay.Imagem;
import javaPlayExtras.BarraStatus;
import javaPlayExtras.ObjetoComGravidade;

public abstract class Personagem extends ObjetoComGravidade{
    protected Imagem imgPula;
    protected Imagem imgApanha;
    protected Imagem imgBate;
    protected Imagem imgFrente;
    protected Imagem imgTras;
    protected Imagem imgMorto;
    protected Imagem imgNormal;
    protected Imagem imgAtual;
    protected Imagem imgDefende;
    
    protected BarraStatus imgBarra;
    
    protected int hp = 100;
    protected int sp = 100;
    
    protected int forcaImpulso;
    
    protected abstract void eventosTeclado();
    protected abstract void load();
    
    public void step(long timeEllaped){
        super.step(timeEllaped);
        
        this.eventosTeclado();
        if(this.estaMorto()){
            this.morre();
        }
    }
    
    private void mudaImagem(Imagem novaImagem){
        this.imgAtual = novaImagem;
        
        this.setHeight( this.imgAtual.getHeight() );
        this.setWidth( this.imgAtual.getWidth() );
    }
    
    public void apanha(){
        this.x -= 20;
        this.hp -= 10;
        
        this.mudaImagem(this.imgApanha);
    }
    
    public void bate(){
        this.sp += 5;
        
        this.mudaImagem(this.imgBate);
    }
    
    private void morre(){
     //this.mudaImagem(this.imgMorto);
    }
    
    public void pula(){
        if( this.estaSubindo() || this.estaDescendo() ){
            return;
        }
        this.hp -= 10;
        this.sp -= 20;
        
        this.impulso(this.forcaImpulso);
        this.mudaImagem(this.imgPula);
    }
    
    public void defende(){
        this.mudaImagem(this.imgDefende);
    }
    
    public void andaFrente(){
        this.x += 20;
        this.mudaImagem(this.imgFrente);
    }
    
    public void andaTras(){
        this.x -= 20;
        
        this.mudaImagem(this.imgTras);
    }
    
    public void normal(){
        this.mudaImagem(this.imgNormal);
    }
    
    public boolean estaMorto(){
        return (this.hp <= 0);
    }
    
    public void verificaColisaoParede(){
        if((this.x + this.width) >= 790){
            this.x = -(this.width- 790);
        }
        if(this.x <= 0){
            this.x = 5;
        }
    }
}
