
package Negocio;

import Datos.D_Administrador;
import Datos.D_Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

public class N_Administrador {
    SessionFactory miFactory = new Configuration()
            .configure("META-INF/hibernate.cfg.xml")
            .addAnnotatedClass(D_Administrador.class)
            .buildSessionFactory();
    
    Session miSession;
    
    public N_Administrador() {
        this.miSession = miFactory.openSession();
    }
    
    public DefaultTableModel mostrarAdmin_Tabla(String busqueda) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("IdAdministrador");
        modelo.addColumn("IdUsuario");
        modelo.addColumn("Cargo");

        String hql;
        
        if(busqueda.equals("")){
            hql = "FROM D_Administrador";
            
        }else{
            hql = "FROM D_Administrador WHERE idAdministrador LIKE '" + busqueda + "%'";
        }
        
        try{
            miSession.beginTransaction();
            
            List<D_Administrador> admins = miSession.createQuery(hql).getResultList();
            
            for(D_Administrador admin:admins){
                Object registro[] = {
                    admin.getIdAdministrador(),
                    admin.getUsuario().getIdUsuario(),
                    admin.getCargoAdministrador()};
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

    public boolean agregarAdmin(int idUsuario, String cargo) {
        try{
            miSession.beginTransaction();
            
            D_Usuario usu = miSession.get(D_Usuario.class, idUsuario);
            D_Administrador admin = new D_Administrador(cargo, usu);
            
            miSession.save(admin);
            miSession.getTransaction().commit();
            
            return true;
        }catch(HibernateException e){
            JOptionPane.showMessageDialog(null,"Error al agregar datos " + e);
            return false;
        }finally{
            miSession.close();
        }
    }

    public boolean editarAdmin(int idAdmin, int idUsuario, String cargo) {
        try{
            miSession.beginTransaction();
            
            D_Usuario usu = miSession.get(D_Usuario.class, idUsuario);
            D_Administrador admin = miSession.get(D_Administrador.class, idAdmin);
            
            admin.setUsuario(usu);
            admin.setCargoAdministrador(cargo);
            
            miSession.update(admin);
            miSession.getTransaction().commit();
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al editar datos " + e);
            return false;
        }finally{
            miSession.close();
        }
    }

    public boolean eliminarAdmin(int idAdmin) {
        try{
            miSession.beginTransaction();
            
            D_Administrador admin = miSession.get(D_Administrador.class, idAdmin);
            miSession.delete(admin);
            
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

    public ArrayList<String> mostrarAdmin_Combo() {
        ArrayList<String> lista = new  ArrayList<>();
        String hql = "FROM D_Usuario as u where u.estado=true and u.nivel=1";
        try{
            miSession.beginTransaction();
            
            List<D_Usuario> usuarios = miSession.createQuery(hql).getResultList();
            
            for(D_Usuario usu:usuarios){
                lista.add(String.valueOf(usu.getIdUsuario()));
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
