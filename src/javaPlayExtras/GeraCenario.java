package javaPlayExtras;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class GeraCenario{
    private TitleMap titleMap;
    private ArrayList<String> posicao;
    private ArrayList<Chao> chao;
    private ArrayList<ObjetoComGravidade> objeto;
    
    private String listaImagem[];
    
    public GeraCenario(){
        this.titleMap = new TitleMap();
        
        this.chao = new ArrayList<Chao>();
        this.posicao = this.titleMap.getPosicao();
        this.listaImagem = this.titleMap.getListaImagens();
        this.objeto = new ArrayList<ObjetoComGravidade>();
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
