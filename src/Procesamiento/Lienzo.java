/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesamiento;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Luis
 */
public class Lienzo extends JPanel implements MouseListener, MouseMotionListener{
    ArrayList<Nodo> listaNodos;
    ArrayList<Flecha> listaFlechas;
    private Point p1, p2;
    private int indice;
    private Nodo nodoAux;
    private int iNodo;

    public Lienzo(){
        this.listaNodos = new ArrayList<>();
        this.listaFlechas = new ArrayList<>();
        this.indice = 1;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        nodoAux = null;
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        for (Nodo nodo: listaNodos){
            nodo.dibujar(g);
        }
        for (Flecha flecha: listaFlechas){
            flecha.dibujar(g);
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            listaNodos.add(new Nodo(e.getX(), e.getY(), indice++));
            repaint();
        }
        if(e.getButton()==MouseEvent.BUTTON3){
            for (Nodo nodo: listaNodos){
                if(new Rectangle(nodo.getX()-nodo.d/2, nodo.getY()-nodo.d/2,nodo.d,nodo.d).contains(e.getPoint())){
                    if (p1==null){
                        p1 = new Point(e.getX(),e.getY());
                    }else{
                        p2 = new Point(e.getX(),e.getY());
                        int valor = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el valor"));
                        this.listaFlechas.add(new Flecha(p1.x,p1.y,p2.x,p2.y,valor));
                        repaint();
                        p1=null;
                        p2=null;
                    }
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int iN=0;
        for (Nodo nodo: listaNodos){
            if(new Rectangle(nodo.getX()-nodo.d/2, nodo.getY()-nodo.d/2,nodo.d,nodo.d).contains(e.getPoint())){
                nodoAux = nodo;
                iNodo = iN;
                break;
            }
            iN++;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        nodoAux = null;
        iNodo = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (nodoAux!=null){
            this.listaNodos.set(iNodo, new Nodo(e.getX(),e.getY(),nodoAux.getNombre()));
            int iE = 0;
            for (Flecha flecha: listaFlechas){
                if(new Rectangle(flecha.getX1()-Nodo.d/2, flecha.getY1()-Nodo.d/2,Nodo.d,Nodo.d).contains(e.getPoint())){
                    this.listaFlechas.set(iE, new Flecha(e.getX(),e.getY(),flecha.getX2(),flecha.getY2(),flecha.getValor()));
                }else if(new Rectangle(flecha.getX2()-Nodo.d/2, flecha.getY2()-Nodo.d/2,Nodo.d,Nodo.d).contains(e.getPoint())){
                    this.listaFlechas.set(iE, new Flecha(flecha.getX1(),flecha.getY1(),e.getX(),e.getY(),flecha.getValor()));
                }
                iE++;
            }
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
