
package com.dao;

import com.pojos.DetalleOferta;
import com.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * NombreClase: DaoDetalleOferta 
 * Fecha: 13/11/2020 
 * Versión: 1.0 
 * Copyright:ITCA-FEPADE
 * @author Leonel Antonio López Valencia - 040119 
 * Roberto Alejandro Armijo Jímenez - 046719 
 * Sandra Natalia Menjívar Romero - 174218
 */

public class DaoDetalleOferta {
    
     public String insertarDetalleOferta(DetalleOferta dof){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(dof);
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
    
    public String modificarDetalleOferta(DetalleOferta dof){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(dof);
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
    
    public String eliminarDetalleOferta(DetalleOferta dof){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(dof);
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
    
    public List<DetalleOferta> mostrarDetalleOferta(){
        List<DetalleOferta> listaDetalleOferta = new ArrayList();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM DetalleOferta";
        try {
            listaDetalleOferta = session.createQuery(sql).list();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        return listaDetalleOferta;
    }
    
    public DetalleOferta buscarDetalleOferta(int id){
        DetalleOferta dof = new DetalleOferta();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            dof = (DetalleOferta) session.load(DetalleOferta.class, id);
            t.commit();
            session.close();
        } catch (HibernateException e) {
            dof = null;
            t.rollback();
        }
        return dof;
    }
    
    public int ultimoId() {
        List<DetalleOferta> listaDetalleOferta = new ArrayList();
        Session session = null;
        int id = 0;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM DetalleOferta";
        try {
            listaDetalleOferta = session.createQuery(sql).list();
            for (DetalleOferta usu : listaDetalleOferta) {
                id = usu.getId().getIdDetalle();
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
    
    public List<DetalleOferta> buscarDetalleOferta(String nombre){
        List<DetalleOferta> listaDetalleOferta = new ArrayList();
        Session session = null;
        int id = 0;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM DetalleOferta WHERE idDetalle LIKE :id";
        try {
            listaDetalleOferta = session.createQuery(sql).setString("id", "%"+id+"%").list();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        id++;
        return listaDetalleOferta;
    }
    
    public long ventaMensual(int mes) {
        long cantidad = 0;
        Session session = null;

        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "select count(fechaOferta) as total_ventas from DetalleOferta where fechaOferta like :mes";
        try {
            cantidad = (long)session.createQuery(sql).setString("mes", "%-" + mes+ "-%").uniqueResult();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        return cantidad;
    }
    
    public long ventaCategoria(String categoria) {
        long cantidad = 0;
        Session session = null;

        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "select count(idUsuario) from DetalleOferta det "
                + "inner join det.vehiculo "
                + "inner join det.vehiculo.categoria where det.vehiculo.categoria = :categoria";
        try {
            cantidad = (long) session.createQuery(sql).setString("categoria", categoria).uniqueResult();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        return cantidad;
    }
    
    
}
