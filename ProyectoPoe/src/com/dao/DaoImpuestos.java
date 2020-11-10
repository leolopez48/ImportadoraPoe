
package com.dao;

import com.pojos.Impuesto;
import com.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Natalia
 */
public class DaoImpuestos {
    
     public String insertarImpuesto(Impuesto imp){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(imp);
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
    
    public String modificarImpuesto(Impuesto imp){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(imp);
            session.getTransaction().commit();
            return "Modificado correctamente";
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage());
            return "No se ha insertado";
        } finally {
            session.close();
        }
    }
    
    public String eliminarImpuesto(Impuesto imp){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(imp);
            session.getTransaction().commit();
            return "Eliminado correctamente";
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            JOptionPane.showMessageDialog(null, e.getMessage());
            return "No se ha insertado";
        } finally {
            session.close();
        }
    }
    
    public List<Impuesto> mostrarImpuesto(){
        List<Impuesto> listaImpuesto = new ArrayList();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM Impuesto";
        try {
            listaImpuesto = session.createQuery(sql).list();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        return listaImpuesto;
    }
    
    public Impuesto buscarImpuesto(int id){
        Impuesto imp = new Impuesto();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            imp = (Impuesto) session.load(Impuesto.class, id);
            t.commit();
            session.close();
        } catch (HibernateException e) {
            imp = null;
            t.rollback();
        }
        return imp;
    }
    
    public int ultimoId() {
        List<Impuesto> listaImpuesto = new ArrayList();
        Session session = null;
        int id = 0;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM Impuesto";
        try {
            listaImpuesto = session.createQuery(sql).list();
            for (Impuesto usu : listaImpuesto) {
                id = usu.getIdImpuesto();
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
    
    public List<Impuesto> buscarImpuesto(String nombre){
        List<Impuesto> listaImpuesto = new ArrayList();
        Session session = null;
        int id = 0;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM Impuesto WHERE nombre LIKE :nombre";
        try {
            listaImpuesto = session.createQuery(sql).setString("nombre", "%"+nombre+"%").list();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        id++;
        return listaImpuesto;
    }
}
