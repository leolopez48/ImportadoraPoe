
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
    
    public String modificarUsuario(Usuario cli){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(cli);
            session.getTransaction().commit();
            return "Insertado correctamente";
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return "No se ha insertado";
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
        System.out.println("Hola");
        List<Usuario> listaUsuarios = new ArrayList();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "from usuario";
        try {
            listaUsuarios = session.createQuery(sql).list();
            System.out.println("Usuarios:"+ listaUsuarios.size());
            t.commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        }
        return listaUsuarios;
    }
}
