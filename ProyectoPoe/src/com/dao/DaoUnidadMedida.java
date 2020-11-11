/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.pojos.UnidadMedida;
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
public class DaoUnidadMedida {
    public String insertarUnidadMedida(UnidadMedida unidad){
    Session session = null;
    try {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(unidad);
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
    public String modificarUnidadMedida(UnidadMedida unidad){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(unidad);
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
    public String eliminarUnidadMedida(UnidadMedida unidad){
    Session session = null;
    try {
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(unidad);
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
    public List<UnidadMedida> mostrarUnidadMedidas(){
        List<UnidadMedida> listaUnidadMedidas = new ArrayList();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM UnidadMedida";
        try {
            listaUnidadMedidas = session.createQuery(sql).list();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        return listaUnidadMedidas;
    }
    public UnidadMedida buscarUnidadMedida(int id){
        UnidadMedida unidad = new UnidadMedida();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            unidad = (UnidadMedida) session.load(UnidadMedida.class, id);
            t.commit();
            session.close();
        } catch (HibernateException e) {
            unidad = null;
            t.rollback();
        }
        return unidad;
    }
    public int ultimoId() {
        List<UnidadMedida> listaUnidadMedidas = new ArrayList();
        Session session = null;
        int id = 0;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM UnidadMedida";
        try {
            listaUnidadMedidas = session.createQuery(sql).list();
            for (UnidadMedida usu : listaUnidadMedidas) {
            id = usu.getIdUnidadMedida();
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
    public List<UnidadMedida> buscarUnidadMedida(String nombre){
        List<UnidadMedida> listaUnidadMedidas = new ArrayList();
        Session session = null;
        int id = 0;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM UnidadMedida WHERE nombre LIKE :nombre";
        try {
            listaUnidadMedidas = session.createQuery(sql).setString("nombre", "%"+nombre+"%").list();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        id++;
        return listaUnidadMedidas;
    }
}
