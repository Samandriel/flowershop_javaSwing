/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flowershop;

import javax.swing.JFrame;
import Configurations.Database;
/**
 *
 * @author harri
 */
public class FlowerShop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                JFrame page = new Register();
                page.pack();
                page.setLocationRelativeTo(null);
                page.setVisible(true);
            }
        });
    }
    
}
