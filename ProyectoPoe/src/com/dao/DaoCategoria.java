
package Dao;

import com.pojos.Categoria;
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
public class DaoCategoria {
    
    public String insertarCategoria(Categoria cat){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(cat);
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
    
    public String modificarCategoria(Categoria cat){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(cat);
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
    
    public String eliminarCategoria(Categoria cat){
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(cat);
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
    
    public List<Categoria> mostrarCategoria(){
        List<Categoria> listaCategoria = new ArrayList();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM Categoria";
        try {
            listaCategoria = session.createQuery(sql).list();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        return listaCategoria;
    }
    
    public Categoria buscarCategoria(int id){
        Categoria cat = new Categoria();
        Session session = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        try {
            cat = (Categoria) session.load(Categoria.class, id);
            t.commit();
            session.close();
        } catch (HibernateException e) {
            cat = null;
            t.rollback();
        }
        return cat;
    }
    
    public int ultimoId() {
        List<Categoria> listaCategoria = new ArrayList();
        Session session = null;
        int id = 0;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM Categoria";
        try {
            listaCategoria = session.createQuery(sql).list();
            for (Categoria usu : listaCategoria) {
                id = usu.getIdCategoria();
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
    
    public List<Categoria> buscarCategoria(String nombre){
        List<Categoria> listaCategoria = new ArrayList();
        Session session = null;
        int id = 0;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();
        String sql = "FROM Categoria WHERE nombreCategoria LIKE :nombre";
        try {
            listaCategoria = session.createQuery(sql).setString("nombre", "%"+nombre+"%").list();
            t.commit();
            session.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            t.rollback();
        }
        id++;
        return listaCategoria;
    }
}
