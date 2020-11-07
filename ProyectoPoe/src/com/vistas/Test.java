
package com.vistas;

//import com.conexion.Conexion;
import com.dao.DaoCliente;
import com.dao.DaoUsuario;
import com.pojos.Cliente;
import com.pojos.Usuario;
import java.util.List;
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
        //Conexion con= new Conexion();
        //JOptionPane.showMessageDialog(null, "Conecto? "+con.conectar());
        DaoCliente cli = new DaoCliente();
        DaoUsuario daoU = new DaoUsuario();
        Usuario usu = new Usuario();
        /*List<Cliente> clientes = cli.mostrarClientes();
        System.out.println(clientes.size());
        
        List<Usuario> usuarios = daoU.mostrarUsuarios();
        System.out.println(usuarios.size());*/
        System.out.println(daoU.login("pedroM35", "123456"));
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
    }
    
}
