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
    
    protected double forca = 25;
    protected double inteligencia = 70;
    
    protected int forcaImpulso;
    
    protected abstract void eventosTeclado();
    protected abstract void load();
    protected abstract void executaAudio(String diretorio);
    
    public void step(long timeEllaped){
        super.step(timeEllaped);
        
        if(this.estaMorto()){
            this.morre();
            return;
        }
        this.eventosTeclado();
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
        this.executaAudio("bate");
    }
    
    private void morre(){
        //this.mudaImagem(this.imgMorto);
        //System.out.println("Morreu");
        this.executaAudio("morre");
    }
    
    public void pula(){
        if( this.estaSubindo() || this.estaDescendo() ){
            return;
        }
        this.apanha();
        
        this.impulso(this.forcaImpulso);
        this.mudaImagem(this.imgPula);
        this.executaAudio("pula");
    }
    
    public void defende(){
        this.mudaImagem(this.imgDefende);
        this.executaAudio("defende");
    }
    
    public void andaFrente(){
        this.x += 13;
        this.mudaImagem(this.imgFrente);
    }
    
    public void andaTras(){
        this.x -= 13;
        
        this.mudaImagem(this.imgTras);
    }
    
    public void normal(){
        this.mudaImagem(this.imgNormal);
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
}
