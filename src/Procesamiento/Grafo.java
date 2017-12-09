/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesamiento;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Luis
 */
public class Grafo {
    
    
    public static void main(String[] args){
        Lienzo lienzo = new Lienzo();
        JFrame ventana = new JFrame("Flujo Máximo");
        String mensaje = "Antes de calcular el flujo máximo debes dibujar el grafo";
        JOptionPane.showMessageDialog(ventana, mensaje);
        JButton boton = new JButton("Flujo maximo");
        boton.setBounds(0, 0, 700, 30);
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FlujoMaximo fm = new FlujoMaximo(lienzo, lienzo.listaNodos, lienzo.listaFlechas);
                fm.start();
            }
        });
        ventana.add(boton);
        boton.setVisible(true);
        ventana.add(lienzo);
        ventana.setSize(700,700);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ventana.setLayout(new GridLayout(2,1));
        ventana.setVisible(true);
    }
}
