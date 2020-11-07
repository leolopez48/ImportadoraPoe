
package com.dao;

import com.pojos.Proveedor;
import com.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Roberto
 */
public class DaoProveedor {
    
    public String insertarProveedor(Proveedor pro){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(pro);
            session.getTransaction().commit();
            return "Insertado correctamente";
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return "No se ha insertado";
        } finally {
            if(session!=null)
            {
            session.close();
            }
        }
    }
    
    public String modificarProveedor(Proveedor pro){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(pro);
            session.getTransaction().commit();
            return "Modificado correctamente";
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return "No se ha Modificado";
        } finally {
            if(session!=null)
            {
            session.close();
            }
        }
    }

     public String eliminarProveedor(Proveedor pro){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(pro);
            session.getTransaction().commit();
            return "Eliminado correctamente";
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            return "No se ha Eliminado";
        } finally {
            if(session!=null)
            {
            session.close();
            }
        }
    }
     
     public List<Proveedor> mostrarProveedor(){
        List<Proveedor> listaProveedor = new ArrayList();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM Proveedor";
        try {
            listaProveedor = session.createQuery(sql).list();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            t.rollback();
        }
        return listaProveedor;
    }
    
    public List<Proveedor> buscarCliente(int id){
        //Proveedor pro = new Proveedor();
        List<Proveedor>pro = new ArrayList();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            //pro = (Proveedor) session.load(Cliente.class, id);
            t.commit();
            session.close();
        } catch (HibernateException e) {
            pro = null;
            t.rollback();
        }
        return pro;
    }
    
    
}
