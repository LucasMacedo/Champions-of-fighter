package Personagens;

import javaPlay.Imagem;
import javaPlayExtras.BarraStatus;
import javaPlayExtras.ObjetoComGravidade;
import javaPlayExtras.Tiro;

public abstract class Personagem extends ObjetoComGravidade{
    protected Imagem imgPula;
    protected Imagem imgApanha;
    protected Imagem imgBate;
    protected Imagem imgFrente;
    protected Imagem imgTras;
    protected Imagem imgMorto;
    protected Imagem imgNormal;
    protected Imagem imgAtual;
    protected Imagem imgEspecial;
    
    protected Tiro tiro;
    protected BarraStatus imgBarra;
    
    protected String viradoPra;
    
    protected double forca = 25;
    protected double inteligencia = 70;
    
    protected int forcaImpulso;
    
    protected int cont = 0;
    
    protected abstract void eventosTeclado();
    protected abstract void executaAudio(String diretorio);
    
    protected void load(){
        this.forcaImpulso = 38;
        this.tiro = new Tiro();
    }
    
    public void step(long timeEllaped){
        super.step(timeEllaped);
        
        if(this.estaMorto()){
            this.morre();
            return;
        }
        this.eventosTeclado();
    }
    
    public void existeTiro(){
        
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
    
    public void especial(){
        if(sp > 0 && !this.tiro.getExiste()){
            this.mudaImagem(this.imgEspecial);
            this.tiro = new Tiro(this.x, this.y, this.width, this.height, this.viradoPra);
            this.executaAudio("defende");
            //sp = 0;
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
        this.viradoPra = "frente";
        this.mudaImagem(this.imgFrente);
    }
    
    public void andaTras(){
        this.x -= 13;
        
        this.mudaImagem(this.imgTras);
        this.viradoPra = "tras";
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
    
    public void colisaoTiro(ObjetoComGravidade obj){        
        this.tiro.colisao(obj);
    }
}
