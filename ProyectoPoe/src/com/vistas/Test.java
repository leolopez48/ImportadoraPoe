
package com.vistas;

import com.conexion.Conexion;
import javax.swing.JOptionPane;

/**
 *
 * @author Roberto
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Conexion con= new Conexion();
        JOptionPane.showMessageDialog(null, "Conecto? "+con.conectar());
        
    }
    
}
