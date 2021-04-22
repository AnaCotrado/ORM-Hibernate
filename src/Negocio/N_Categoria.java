
package Negocio;

import Datos.D_Administrador;
import Datos.D_Categoria;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class N_Categoria {
    SessionFactory miFactory = new Configuration()
            .configure("META-INF/hibernate.cfg.xml")
            .addAnnotatedClass(D_Categoria.class)
            .buildSessionFactory();
    
    Session miSession;
    
    public N_Categoria() {
        this.miSession = miFactory.openSession();
    }
    
    public DefaultTableModel listaCategoria(String busqueda){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("IdCategoria");
        modelo.addColumn("Nombre");
        modelo.addColumn("Descripción");
        
        String hql;
        
        if(busqueda.equals("")){
            hql = "FROM D_Categoria";
            
        }else{
            hql = "FROM D_Categoria WHERE nombre LIKE '" + busqueda + "%'";
        }
        
        try{
            miSession.beginTransaction();
            
            List<D_Categoria> categorias = miSession.createQuery(hql).getResultList();
            
            for(D_Categoria categoria:categorias){
                Object registro[] = {
                    categoria.getIdCategoria(),
                    categoria.getNombre(),
                    categoria.getDescripcion()};
                modelo.addRow(registro);
            }
            miSession.getTransaction().commit();
            return modelo;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }finally{
            miSession.close();
        }   
    }
    
    public boolean agregarCategoria(String nombreCategoria, String descripcionCategoria){
        try{
            miSession.beginTransaction();
            
            D_Categoria categ = new D_Categoria(nombreCategoria, descripcionCategoria);
            
            miSession.save(categ);
            miSession.getTransaction().commit();
            return true;
        }catch(HibernateException e){
            JOptionPane.showMessageDialog(null,"Error al agregar datos " + e);
            return false;
        }finally{
            miSession.close();
        }
    }
    
    public boolean editarCategoria(int idCategoria, String nombreCategoria, String descripcionCategoria){
        try{
            miSession.beginTransaction();
            D_Categoria categ = miSession.get(D_Categoria.class, idCategoria);
            
            categ.setNombre(nombreCategoria);
            categ.setDescripcion(descripcionCategoria);
            
            miSession.update(categ);
            miSession.getTransaction().commit();
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al editar datos " + e);
            return false;
        }finally{
            miSession.close();
        }
    }
    
    public boolean eliminarCategoria(int idCategoria){
        try{
            miSession.beginTransaction();
            
            D_Categoria categ = miSession.get(D_Categoria.class, idCategoria);
            miSession.delete(categ);
            
            miSession.getTransaction().commit();
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al editar datos " + e);
            return false;
        }
        finally{
            miSession.close();
        }
    }
    
}
