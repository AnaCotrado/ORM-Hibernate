
package Negocio;

import Datos.D_Usuario;
import Presentacion.FrmLogin;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class N_Usuario {
    
    SessionFactory miFactory = new Configuration()
            .configure("META-INF/hibernate.cfg.xml")
            .addAnnotatedClass(D_Usuario.class)
            .buildSessionFactory();
    
    Session miSession;

    public N_Usuario() {
        this.miSession = miFactory.openSession();
    }
    
    public boolean registrarUsuario(String dni, 
            String nombre,
            String apellido, 
            String login, 
            String password, 
            short nivel){
        
        try{
            D_Usuario usu = new D_Usuario(dni, nombre, apellido, login, password, nivel, true);
            miSession.beginTransaction();
            
            miSession.save(usu);
            miSession.getTransaction().commit();
            
            return true;
        }catch(HibernateException e){
            JOptionPane.showMessageDialog(null,"Error al agregar datos " + e);
            return false;
        }finally{
            miSession.close();
        }
    }
    
    public boolean modificarUsuario(int id,
            String dni, 
            String nombre,
            String apellido, 
            String login, 
            String password, 
            short nivel){
        try{
            miSession.beginTransaction();
            
            D_Usuario usu = miSession.get(D_Usuario.class, id);
            usu.setDNI(dni);
            usu.setNombre(nombre);
            usu.setApellido(apellido);
            usu.setLogin(login);
            usu.setPassword(password);
            usu.setNivel(nivel);
            
            miSession.update(usu);
            miSession.getTransaction().commit();
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al editar datos " + e);
            return false;
        }finally{
            miSession.close();
        }
    }
    
    public boolean eliminarUsuario(int id){
        try{
            miSession.beginTransaction();
            
            D_Usuario usu = miSession.get(D_Usuario.class, id);
            miSession.delete(usu);
            
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
    
    public DefaultTableModel mostrarUsuarios_Tabla(String busqueda){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("IdUsuario");
        modelo.addColumn("Dni");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Login");
        modelo.addColumn("Password");
        modelo.addColumn("Nivel");
        modelo.addColumn("Estado");
        
        String hql;
        
        if(busqueda.equals("")){
            hql = "FROM D_Usuario";
            
        }else{
            hql = "FROM D_Usuario WHERE DNI LIKE '" + busqueda + "%'";
        }
        
        try{
            miSession.beginTransaction();
            
            List<D_Usuario> usuarios = miSession.createQuery(hql).getResultList();
            
            for(D_Usuario usu:usuarios){
                Object registro[] = {
                    usu.getIdUsuario(),
                    usu.getDNI(),
                    usu.getNombre(),
                    usu.getApellido(),
                    usu.getLogin(),
                    usu.getPassword(),
                    usu.getNivel(), 
                    usu.isEstado()};
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
    
    public boolean loginUsuario(String user, String password){
        D_Usuario usu;
        try {
            miSession.beginTransaction();
            usu = (D_Usuario) miSession.createQuery("FROM D_Usuario U WHERE U.login = :userName")
                    .setParameter("userName", user)
                    .uniqueResult();

            if (usu != null && usu.getPassword().equals(password)) {
                FrmLogin.usuario = usu;
                return true;
            }
            miSession.getTransaction().commit();
        } catch (Exception e) {
            if (miSession.getTransaction() != null) {
                miSession.getTransaction().rollback();
            }
            e.printStackTrace();
        }
        return false;
    }
}
