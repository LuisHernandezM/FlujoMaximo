/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesamiento;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Luis
 */
public class Nodo {
    public static final int d = 50;
    private int x, y;
    private int nombre;
    private ArrayList<Flecha> flechas;
    

    
    public Nodo(int x, int y, int nombre){
        this.x = x;
        this.y = y;
        this.nombre = nombre;
        flechas = new ArrayList<>();
    }
    
    public void dibujar(Graphics g){
        g.drawOval(this.x-d/2, this.y-d/2, d, d);
        g.drawString(nombre+"", x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Flecha> getFlechas() {
        return flechas;
    }

    public void setFlechas(ArrayList<Flecha> flechas) {
        this.flechas = flechas;
    }
    
    
}
