package Cenarios;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public abstract class Cenario {
    protected int cenario[][];
    protected ArrayList<String> posicao;
    protected String lista[];
    
    protected void geraPosicoes() {
        int x = 0;
        int y = 0;
        
        int width = 32;
        int height = 32;
        
        String posicao;
        
        while(y < 18){
            while(x < 25){
                posicao = "";
                
                if(this.cenario[y][x] > 0){
                    if((x*width) == 0             ){posicao += "000";}
                    if((x*width) < 10   && (x*width) > 0  ){posicao += "00"+(x*width);}
                    if((x*width) >= 10  && (x*width) < 100){posicao += "0"+(x*width);}
                    if((x*width) >= 100           ){posicao += ""+(x*width);}
                    
                    posicao += ",";
                    
                    if((y*height+23) == 0             ){posicao += "000";}
                    if((y*height+23) < 10   && (y*height+23) > 0  ){posicao += "00"+(y*height+23);}
                    if((y*height+23) >= 10  && (y*height+23) < 100){posicao += "0"+(y*height+23);}
                    if((y*height+23) >= 100           ){posicao += ""+(y*height+23);}
                    
                    posicao += ","+this.cenario[y][x];
                    this.posicao.add(posicao);
                }
                x ++;
            }
            x = 0;
            y ++;
        }
    }
    
    protected void listaImagens(){
        this.lista = new String[4]; // Inicializa o vetor onde o tamanho corresponde a quantidade de imagens usadas no cenario
        
        this.lista[0] = "resources/cenario1/chao.png"; // Imagem tipo 1 na matriz
        this.lista[1] = "resources/cenario1/imagem2.png"; // Imagem tipo 2 na matriz
        this.lista[2] = "resources/cenario1/imagem3.jpg"; // Imagem tipo 3 na matriz
        this.lista[3] = "resources/cenario1/imagem4.jpg"; // Imagem tipo 4 na matriz
    }
    
    public ArrayList<String> getPosicao(){
        return this.posicao;
    }
    
    public String[] getListaImagens(){
        return this.lista;
    }
}
