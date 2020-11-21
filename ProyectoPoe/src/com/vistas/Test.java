
package com.vistas;

//import com.conexion.Conexion;
import com.dao.Crypting;
import com.dao.DaoCliente;
import com.dao.DaoDetalleOferta;
import com.dao.DaoUsuario;
import com.dao.DaoVehiculo;
import com.pojos.Cliente;
import com.pojos.DetalleOferta;
import com.pojos.DetalleOfertaId;
import com.pojos.Usuario;
import java.util.Date;
import java.util.List;
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
        DaoUsuario daoU = new DaoUsuario();

        DaoCliente cli = new DaoCliente();
        
        Usuario usu = new Usuario();
        List<Cliente> clientes = cli.mostrarClientes();
        System.out.println(clientes.size());
        
        List<Usuario> usuarios = daoU.mostrarUsuarios();
        System.out.println(usuarios.size());
        System.out.println(daoU.ultimoId());
        
        List<Cliente> clientes1 = cli.buscarCliente("Pedro");
        System.out.println(clientes1.get(0).getNombreCliente());
        int tipo = daoU.tipoUsuario("pedroM35");
        System.out.println(tipo);
        
        usu.setIdUsuario(1);
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setDireccionCliente("Direccion");
        cliente.setNombreCliente("Nombre");
        cliente.setTelefonoCliente("123456");
        cliente.setUsuario(usu);
        System.out.println(cli.insertarCliente(cliente));
        
        System.out.println(cli.modificarCliente(cliente));
        System.out.println(cli.eliminarCliente(cliente));
        
        DaoDetalleOferta daoD = new DaoDetalleOferta();
        System.out.println(daoD.ventaMensual(11));
        
        System.out.println(daoD.ventaCategoria("Mini Truck"));
        
        try {
            Crypting crp = new Crypting();
            System.out.println(crp.decrypt("b4WE4bXVBj0="));
            System.out.println(crp.encrypt("agua"));
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        DetalleOfertaId id = new DetalleOfertaId();
        id.setIdDetalle(1);
        id.setIdVehiculo(1);
        
        System.out.println(daoD.eliminarDetalleOferta(1, 6));
        
        DetalleOferta detalle = new DetalleOferta();
        DaoVehiculo daoV = new DaoVehiculo();
        
        DetalleOfertaId deta = new DetalleOfertaId(2, 4);
        detalle.setId(deta);
        detalle.setCantidad(3);
        detalle.setUsuario(daoU.buscarUsuarioNombre("robertoP37").get(0));
        Date date = java.util.Calendar.getInstance().getTime();
        detalle.setFechaOferta(date);
        detalle.setVehiculo(daoV.buscarVehiculo(4));
        System.out.println(daoD.modificarDetalleOferta(detalle));
    }
    
}
