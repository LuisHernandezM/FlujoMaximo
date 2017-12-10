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
        Nodo fin = listaNodos.get(listaNodos.size()-1);
        boolean bandera = true;
        ArrayList<Integer> aux = new ArrayList<>(); //indices de nodos
        ArrayList<Integer> faux = new ArrayList<>(); //indices de flechas
        int flow = 0; //valor del flujo maximo
        while(true) {
            aux.add(0); // agrega el nodo inicial
            while(bandera){
                int max = -1; // variable para obtener la flecha con el mayor valor
                Nodo n = listaNodos.get(aux.get(aux.size()-1));
                int iFlecha=-1;
                int i=0;
                for (Flecha flecha: listaFlechas){
                    if(new Rectangle(n.getX()-Nodo.d/2, n.getY()-Nodo.d/2,Nodo.d,Nodo.d).contains(new Point(flecha.getX1(),flecha.getY1()))){
                        //if ()
                        if (new Rectangle(fin.getX()-Nodo.d/2, fin.getY()-Nodo.d/2,Nodo.d,Nodo.d).contains(new Point(flecha.getX2(),flecha.getY2()))!=true){
                            if (flecha.getValor()>max&&flecha.getValor()!=0){
                                max = flecha.getValor();
                                iFlecha = i;
                                System.out.println(flecha.getValor());
                            }
                        }   
                    }
                    i++;
                }
                int jNodo=-1;
                int j=0;
                faux.add(iFlecha);
                System.out.println(listaFlechas.get(faux.size()-1).getValor());
                Flecha f = listaFlechas.get(faux.size()-1);
                //Nodo ant = listaNodos.get(aux.get(aux.size()-1));
                for (Nodo nodo: listaNodos){
                    if(new Rectangle(nodo.getX()-Nodo.d/2, nodo.getY()-Nodo.d/2,Nodo.d,Nodo.d).contains(new Point(f.getX2(),f.getY2()))){
                        if (nodo.equals(fin)){
                            jNodo = j;
                            aux.add(jNodo);
                        }
                        //
                    }
                    j++;
                }
                if (listaNodos.size()-1==jNodo){
                    bandera = false;
                }
            }
            System.out.println();
            // *****************************************************************
            int valmin = 999999;
            for (int t=0;t<faux.size();t++){
                Flecha fle = listaFlechas.get(faux.get(t));
                if(fle.getValor()<valmin){
                    valmin=fle.getValor();
                }
            }
            for (int t=0;t<faux.size();t++){
                Flecha fle = listaFlechas.get(faux.get(t));
                if (fle.getValor()>=valmin){
                    listaFlechas.get(faux.get(t)).setValor(fle.getValor()-valmin);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FlujoMaximo.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                grafo.repaint();
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
