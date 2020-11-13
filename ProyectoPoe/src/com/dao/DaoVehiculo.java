
package com.dao;

import com.pojos.Vehiculo;
import com.utils.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * NombreClase: DaoVehiculo
 * Fecha: 13/11/2020 
 * Versión: 1.0 
 * Copyright:ITCA-FEPADE
 * @author Leonel Antonio López Valencia - 040119 
 * Roberto Alejandro Armijo Jímenez - 046719 
 * Sandra Natalia Menjívar Romero - 174218
 */
public class DaoVehiculo {
    
    public String insertarVehiculo(Vehiculo vehiculo) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(vehiculo);
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

    public String modificarVehiculo(Vehiculo vehiculo) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(vehiculo);
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

    public String eliminarVehiculo(Vehiculo vehiculo) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(vehiculo);
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

    public List<Vehiculo> mostrarVehiculos() {
        List<Vehiculo> listaVehiculos = new ArrayList();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM Vehiculo";
        try {
            listaVehiculos = session.createQuery(sql).list();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        return listaVehiculos;
    }

    public Vehiculo buscarVehiculo(int id) {
        Vehiculo vehiculo = new Vehiculo();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            vehiculo = (Vehiculo) session.load(Vehiculo.class, id);
            t.commit();
            session.close();
        } catch (HibernateException e) {
            vehiculo = null;
            t.rollback();
        }
        return vehiculo;
    }

    public int ultimoId() {
        List<Vehiculo> listaVehiculos = new ArrayList();
        Session session = null;
        int id = 0;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM Vehiculo";
        try {
            listaVehiculos = session.createQuery(sql).list();
            for (Vehiculo usu : listaVehiculos) {
                id = usu.getIdVehiculo();
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

    public List<Vehiculo> buscarVehiculo(String nombre) {
        List<Vehiculo> listaVehiculos = new ArrayList();
        Session session = null;
        int id = 0;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM Vehiculo WHERE nombre LIKE :nombre";
        try {
            listaVehiculos = session.createQuery(sql).setString("nombre", "%" + nombre + "%").list();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        id++;
        return listaVehiculos;
    }
}
