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
                
                JFrame menu = new MainMenu();
                menu.pack();
                menu.setLocationRelativeTo(null);
                menu.setVisible(true);
            }
        });
    }
    
}
