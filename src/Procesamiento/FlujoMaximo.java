/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesamiento;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
        //Nodo inicial = listaNodos.get(0);
        //Nodo fin = listaNodos.get(listaNodos.size()-1);
        boolean bandera = true;
        ArrayList<Integer> aux = new ArrayList<>();
        ArrayList<Integer> faux = new ArrayList<>(); 
        int flow = 0;
        while(true) {
            aux.add(0);
            while(bandera){
                int max = -1;
                Nodo n = listaNodos.get(aux.get(aux.size()-1));
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
                faux.add(iFlecha);
                Flecha f = listaFlechas.get(faux.size()-1);
                for (Nodo nodo: listaNodos){
                    if(new Rectangle(nodo.getX()-Nodo.d/2, nodo.getY()-Nodo.d/2,Nodo.d,Nodo.d).contains(new Point(f.getX2(),f.getY2()))){
                        jNodo = j;
                        aux.add(jNodo);
                    }
                    j++;
                }
                if ((listaNodos.size()-1)==jNodo){
                    bandera = false;
                }
            }
            int valmin = 999999;
            for (int t=0;t<faux.size();t++){
                Flecha fle = listaFlechas.get(aux.get(t));
                if(fle.getValor()<valmin){
                    valmin=fle.getValor();
                }
            }
            for (int t=0;t<faux.size();t++){
                Flecha fle = listaFlechas.get(aux.get(t));
                fle.setValor(fle.getValor()-valmin);
                grafo.repaint();
                try {
                    this.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(FlujoMaximo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            int acu=0;
            Nodo nodoAux = listaNodos.get(0);
            for (Flecha insidentes: listaFlechas){
                if(new Rectangle(insidentes.getX1()-Nodo.d/2, insidentes.getY1()-Nodo.d/2,Nodo.d,Nodo.d).contains(new Point(nodoAux.getX(),nodoAux.getY()))){
                    acu = acu + insidentes.getValor();
                }
            }
            flow = flow + valmin;
            bandera=true;
            if(acu==0){
                JOptionPane.showMessageDialog(grafo, "El flujo máximo es de " + flow);
                this.stop();
            }
            aux.clear();
            faux.clear();
        }
    }
}
