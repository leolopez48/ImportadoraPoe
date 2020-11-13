
package com.dao;

import com.pojos.Usuario;
import com.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.dao.Crypting;

/**
 * NombreClase: DaoUsuario
 * Fecha: 13/11/2020 
 * Versión: 1.0 
 * Copyright:ITCA-FEPADE
 * @author Leonel Antonio López Valencia - 040119 
 * Roberto Alejandro Armijo Jímenez - 046719 
 * Sandra Natalia Menjívar Romero - 174218
 */
public class DaoUsuario {
    
        public String insertarUsuario(Usuario cli){
        Session session = null;
        
            try {
                Crypting crp = new Crypting();
                String contra = cli.getContra();
                cli.setContra(crp.encrypt(contra));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al encriptar: "+e.getMessage());
            }
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(cli);
            session.getTransaction().commit();
            return "Insertado correctamente";
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage());
            return "No se ha insertado";
        } finally {
            session.close();
        }
    }
    
    public String modificarUsuario(Usuario usu){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(usu);
            session.getTransaction().commit();
            return "Modificado correctamente";
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage());
            return "No se ha modificado "+e.getMessage();
        } finally {
            session.close();
        }
    }
        
    public String eliminarUsuario(Usuario cli){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(cli);
            session.getTransaction().commit();
            return "Insertado correctamente";
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage());
            return "No se ha insertado";
        } finally {
            session.close();
        }
    }
    
    public List<Usuario> mostrarUsuarios(){
        List<Usuario> listaUsuarios = new ArrayList();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM Usuario";
        try {
            listaUsuarios = session.createQuery(sql).list();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        return listaUsuarios;
    }
    
    public Usuario buscarUsuario(int id){
        Usuario cli = new Usuario();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            cli = (Usuario) session.load(Usuario.class, id);
            System.out.println(cli.getNombreUsuario());
            t.commit();
            session.close();
        } catch (HibernateException e) {
            cli = null;
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        return cli;
    }
    
    public boolean login(String nombre, String contra) throws Exception{
        Session session = null;
        boolean validado = false;

        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM Usuario where nombreUsuario = :nombre AND contra = :contra";
        try {
            Crypting crp = new Crypting();
            contra = crp.encrypt(contra);
            Usuario usu = (Usuario) session.createQuery(sql).setString("nombre", nombre).setString("contra", contra).uniqueResult();
            t.commit();
            if(usu != null){
                if (usu.getNombreUsuario().equals(nombre) && usu.getContra().equals(contra)) {
                    validado = true;
                }   
            }
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        return validado;
    }
    
    public int ultimoId(){
        List<Usuario> listaUsuarios = new ArrayList();
        Session session = null;
        int id = 0;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM Usuario";
        try {
            listaUsuarios = session.createQuery(sql).list();
            for(Usuario usu: listaUsuarios){
               id = usu.getIdUsuario();
            }
            t.commit();
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        id++;
        return id;
    }
    
    public List<Usuario> buscarUsuario(String nombre) {
        List<Usuario> listaUsuarios = new ArrayList();
        Session session = null;
        int id = 0;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM Usuario WHERE nombreUsuario LIKE :nombre";
        try {
            listaUsuarios = session.createQuery(sql).setString("nombre", "%" + nombre + "%").list();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        id++;
        return listaUsuarios;
    }
    
    public List<Usuario> buscarUsuarioNombre(String nombre) {
        List<Usuario> listaUsuarios = new ArrayList();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM Usuario WHERE nombreUsuario = :nombre";
        try {
            listaUsuarios = session.createQuery(sql).setString("nombre", nombre).list();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        return listaUsuarios;
    }
    
    public int tipoUsuario(String nombre) {
        Usuario usu = new Usuario();
        Session session = null;
        int id = 0;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM Usuario WHERE nombreUsuario LIKE :nombre";
        try {
            usu = (Usuario)session.createQuery(sql).setString("nombre", "%" + nombre + "%").uniqueResult();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        return usu.getTipoUsuario();
    }
}
