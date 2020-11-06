
package com.dao;

import com.pojos.Usuario;
import com.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Leonel
 */
public class DaoUsuario {
        public String insertarUsuario(Usuario cli){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(cli);
            session.getTransaction().commit();
            return "Insertado correctamente";
        } catch (HibernateException e) {
            session.getTransaction().rollback();
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
            t.commit();
            session.close();
        } catch (HibernateException e) {
            cli = null;
            t.rollback();
        }
        return cli;
    }
}
