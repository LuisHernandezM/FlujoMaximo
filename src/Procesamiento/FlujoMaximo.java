/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesamiento;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class FlujoMaximo extends Thread{
    Lienzo grafo;
    ArrayList<Nodo> listaNodos;
    ArrayList<Flecha> listaFlechas;

    public FlujoMaximo(Lienzo grafo, ArrayList<Nodo> listaNodos, ArrayList<Flecha> listaFlechas) {
        this.grafo = grafo;
        this.listaNodos = listaNodos;
        this.listaFlechas = listaFlechas;
    }
    
    @Override
    public void run(){
        Nodo inicial = listaNodos.get(0);
        Nodo fin = listaNodos.get(listaNodos.size()-1);
        ArrayList<Integer> aux = new ArrayList<>();
        ArrayList<Integer> faux = new ArrayList<>(); 
        aux.add(0);
        while(true){
            int max = -1;
            Nodo n = aux.get(aux.size()-1);
            int iFlecha=-1;
            int i=0;
            for (Flecha flecha: listaFlechas){
                if(new Rectangle(flecha.getX1()-Nodo.d/2, flecha.getY1()-Nodo.d/2,Nodo.d,Nodo.d).contains(new Point(n.getX(),n.getY()))){
                    if (flecha.getValor()>max){
                        max = flecha.getValor();
                        iFlecha = i;
                    }
                }
                i++;
            }
            int jNodo=-1;
            int j=0;
            faux.add(listaFlechas.get(iFlecha));
            Flecha f = faux.get(listaFlechas.size()-1);
            for (Nodo nodo: listaNodos){
                if(new Rectangle(nodo.getX()-Nodo.d/2, nodo.getY()-Nodo.d/2,Nodo.d,Nodo.d).contains(new Point(f.getX2(),f.getY2()))){
                    
                }
            }
        }
    }
}
