package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Credenciales {
    private String driver;
    private String url;
    private String usuario;
    private String contraseña;
    Connection con;

    public Credenciales() {
        this.driver = "com.mysql.jdbc.Driver";
        this.url ="jdbc:mysql://localhost:3306/vacante";
        this.usuario = "root";
        this.contraseña = "";
    }

    public String getDriver() {
        return driver;
    }

    public String getUrl() {
        return url;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }
    
    public Connection getConnection() throws SQLException{
        try {
            con =  DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException e) {
            System.out.println("No se pudo conectar");
            con = null;
        }
        return con;
    }
    
}
