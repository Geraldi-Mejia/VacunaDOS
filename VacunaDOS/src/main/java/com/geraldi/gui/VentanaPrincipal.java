/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.geraldi.gui;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 *
 * @author admin
 */
public class VentanaPrincipal extends JFrame{

    public VentanaPrincipal() throws HeadlessException {
        setBounds(500,0,300, 650);
        setTitle("VacunaDOS");
        setResizable(false);
        PanelPrincipal panel = new PanelPrincipal();
        panel.setLayout(null);
        this.add(panel);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    }
    
    
}
