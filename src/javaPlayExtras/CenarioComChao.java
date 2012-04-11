package javaPlayExtras;

import java.awt.Graphics;
import java.util.ArrayList;
import javaPlay.GameObject;
import javax.swing.JOptionPane;

public class CenarioComChao {
    protected ArrayList<String> posicao;
    protected ArrayList<Chao> chao;
    protected ArrayList<ObjetoComGravidade> objeto;
    
    protected String listaImagem[];
    
    public CenarioComChao(ArrayList<String> posicao, String listaImagem[]){
        this.chao = new ArrayList<Chao>();
        this.posicao = posicao;
        this.listaImagem = listaImagem;
        this.objeto = new ArrayList<ObjetoComGravidade>();
    }
    
    public void addObjeto(ObjetoComGravidade objeto){
        this.objeto.add(objeto);
    }
    
    public ObjetoComGravidade verificaColisao(ObjetoComGravidade obj){
        int tamanhoLista = this.chao.size()-1;
        
        int x = obj.getX();
        int y = obj.getY();
        
        boolean houveColisao = false;
        
        while(tamanhoLista >= 0){
            while(obj.temColisao(this.chao.get(tamanhoLista).getRetangulo())){
                obj.setY(obj.getY()-1);
                
                if(!obj.temColisao(this.chao.get(tamanhoLista).getRetangulo())){
                    tamanhoLista = this.chao.size()-1;
                    houveColisao = true;
                }
                
            }
            tamanhoLista --;
        }
        
        if(houveColisao){
            obj.chegouChao();
        }else{
            obj.saiuChao();
        }
        
        return obj;
    }
    
    public void draw(Graphics g){
        int cont = this.posicao.size()-1;
        
        while(cont >= 0){
            int x = 0;
            int y = 0;
            int numImagem = 0;
            
            try{
                x = Integer.parseInt( this.posicao.get(cont).substring(0,3) );
                y = Integer.parseInt( this.posicao.get(cont).substring(4,7) );
                
                numImagem = Integer.parseInt( this.posicao.get(cont).substring(8,9) );
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null, "Erro: "+e);
            }
            
            this.chao.add( new Chao(g, x, y, numImagem, this.listaImagem) );
            cont --;
        }
    }
}
