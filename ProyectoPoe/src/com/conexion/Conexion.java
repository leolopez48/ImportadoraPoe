package com.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * NombreClase: Conexion
 * Fecha: 13/10/2020
 * Versión: 1.0
 * Copyright: ITCA-FEPADE
 * @author Leonel Antonio López Valencia - 040119
 *         Roberto Alejandro Armijo Jímenez - 046719
 *         Sandra Natalia Menjívar Romero - 174218
 **/
public class Conexion {

    Connection con;

    public Connection getCon() {
        return con;
    }
    
    public boolean conectar()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/importadoraPoe","root","");
            return true;
        }catch(SQLException |ClassNotFoundException e){
           JOptionPane.showMessageDialog(null,"Error al conectar"+e.getMessage(),"Error",0);
           return false;
        }
    }
    
    public boolean desconectar()
       {
        try{
            if(con!=null)
            {
                if(con.isClosed()==false)
                {
                    con.close();
                }
            }
        }catch(SQLException e){
           JOptionPane.showMessageDialog(null,"Error al desconectar"+e.getMessage(),"Error",0);
           return false;
        }
        return true;
    }     
}
