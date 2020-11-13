
package com.vistas;

//import com.conexion.Conexion;
import com.dao.Crypting;
import com.dao.DaoDetalleOferta;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * NombreClase: Test
 * Fecha: 13/11/2020 
 * Versión: 1.0 
 * Copyright:ITCA-FEPADE
 * @author Leonel Antonio López Valencia - 040119 
 * Roberto Alejandro Armijo Jímenez - 046719 
 * Sandra Natalia Menjívar Romero - 174218
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Conexion con= new Conexion();
        //JOptionPane.showMessageDialog(null, "Conecto? "+con.conectar());
        /*DaoCliente cli = new DaoCliente();
        DaoUsuario daoU = new DaoUsuario();
        Usuario usu = new Usuario();
        /*List<Cliente> clientes = cli.mostrarClientes();
        System.out.println(clientes.size());
        
        List<Usuario> usuarios = daoU.mostrarUsuarios();
        System.out.println(usuarios.size());*/
        //System.out.println(daoU.ultimoId());
        /*List<Cliente> clientes1 = cli.buscarCliente("Pedro");
        System.out.println(clientes1.get(0).getNombreCliente());
        int tipo = daoU.tipoUsuario("pedroM35");
        System.out.println(tipo);
        //System.out.println(cliente.getCorreoUsuario());
        /*if(cliente != null)
        System.out.println("Cliente: "+cliente.get(0).getNombreCliente());
        else
            System.out.println("NULL");*/
        
        /*Usuario usu = new Usuario();
        usu.setIdUsuario(1);
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setDireccionCliente("Direccion");
        cliente.setNombreCliente("Nombre");
        cliente.setTelefonoCliente("123456");
        cliente.setUsuario(usu);
        System.out.println(cli.insertarCliente(cliente));
        
        System.out.println(cli.modificarCliente(cliente));
        System.out.println(cli.eliminarCliente(cliente));*/
        
        DaoDetalleOferta daoD = new DaoDetalleOferta();
        //System.out.println(daoD.ventaMensual(11));
        
        //System.out.println(daoD.ventaCategoria("Mini Truck"));
        //System.out.println(daoD.mostrarDetalleOferta("pedroM35").get(0).getFechaOferta());
        
        try {
            Crypting crp = new Crypting();
            System.out.println(crp.decrypt("b4WE4bXVBj0="));
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
