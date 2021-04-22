
package Negocio;

import Datos.D_Administrador;
import Datos.D_Sucursal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class N_Sucursal {
    SessionFactory miFactory = new Configuration()
            .configure("META-INF/hibernate.cfg.xml")
            .addAnnotatedClass(D_Sucursal.class)
            .buildSessionFactory();
    
    Session miSession;

    public N_Sucursal() {
        this.miSession = miFactory.openSession();
    }
    
    public DefaultTableModel listarSucursal(String busqueda){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("IdSucursal");
        modelo.addColumn("IdAdministrador");
        modelo.addColumn("Nombre");
        modelo.addColumn("Dirección");
        
        String hql;
        
        if(busqueda.equals("")){
            hql = "FROM D_Sucursal";
            
        }else{
            hql = "FROM D_Sucursal WHERE nombre LIKE '" + busqueda + "%'";
        }
        
        try{
            miSession.beginTransaction();
            
            List<D_Sucursal> sucursales = miSession.createQuery(hql).getResultList();
            
            for(D_Sucursal sucursal:sucursales){
                Object registro[] = {
                    sucursal.getIdSucursal(),
                    "1",//sucursal.getAdministrador().getIdAdministrador(),
                    sucursal.getNombreSucursal(),
                    sucursal.getDireccionSucursal()};
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
    
    public boolean agregarSucursal(String nombre, String direccion, int idAdministrador){
        try{
            miSession.beginTransaction();
            D_Administrador admin = miSession.get(D_Administrador.class, idAdministrador);
            D_Sucursal sucursal = new D_Sucursal(nombre, direccion, admin);
            
            miSession.save(sucursal);
            miSession.getTransaction().commit();
            
            return true;
        }catch(HibernateException e){
            JOptionPane.showMessageDialog(null,"Error al agregar datos " + e);
            return false;
        }finally{
            miSession.close();
        }
    }
    
    public boolean editarSucursal(int idSucursal, String nombre, String direccion, int idAdministrador){
        try{
            miSession.beginTransaction();
            
            D_Administrador admin = miSession.get(D_Administrador.class, idAdministrador);
            D_Sucursal sucursal = miSession.get(D_Sucursal.class, idSucursal);
            
            sucursal.setNombreSucursal(nombre);
            sucursal.setDireccionSucursal(direccion);
            sucursal.setAdministrador(admin);
            
            miSession.update(sucursal);
            miSession.getTransaction().commit();
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al editar datos " + e);
            return false;
        }finally{
            miSession.close();
        }
    }
    
    public boolean eliminarSucursal(int idSucursal){
        try{
            miSession.beginTransaction();
            
            D_Sucursal sucursal = miSession.get(D_Sucursal.class, idSucursal);
            miSession.delete(sucursal);
            
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
    
    public ArrayList<String> listarSucursal_Combo(){
        ArrayList<String> lista = new  ArrayList<>();
        try{
            miSession.beginTransaction();
            
            List<D_Administrador> admins;
            admins = miSession.createQuery("FROM D_Administrador ").getResultList();
            
            for(D_Administrador admin:admins){
                lista.add(String.valueOf(admin.getIdAdministrador()));
            }
            miSession.getTransaction().commit();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }finally{
            miSession.close();
        }
        return lista;
    } 
}
