
package com.dao;

import com.pojos.Cliente;
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
public class DaoCliente {
    public String insertarCliente(Cliente cli){
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
    
    public String modificarCliente(Cliente cli){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(cli);
            session.getTransaction().commit();
            return "Modificado correctamente";
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return "No se ha insertado";
        } finally {
            session.close();
        }
    }
        
    public String eliminarCliente(Cliente cli){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(cli);
            session.getTransaction().commit();
            return "Eliminado correctamente";
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return "No se ha insertado";
        } finally {
            session.close();
        }
    }
    
    public List<Cliente> mostrarClientes(){
        List<Cliente> listaClientes = new ArrayList();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM Cliente";
        try {
            listaClientes = session.createQuery(sql).list();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            t.rollback();
        }
        return listaClientes;
    }
    
    public Cliente buscarCliente(int id){
        Cliente cli = new Cliente();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            cli = (Cliente) session.load(Cliente.class, id);
            t.commit();
            session.close();
        } catch (HibernateException e) {
            cli = null;
            t.rollback();
        }
        return cli;
    }
}
