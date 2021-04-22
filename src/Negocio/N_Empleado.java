
package Negocio;

import Datos.D_Empleado;
import Datos.D_Sucursal;
import Datos.D_Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class N_Empleado {
    SessionFactory miFactory = new Configuration()
            .configure("META-INF/hibernate.cfg.xml")
            .addAnnotatedClass(D_Empleado.class)
            .buildSessionFactory();
    
    Session miSession;
    
    public N_Empleado() {
        this.miSession = miFactory.openSession();
    }
    
    public DefaultTableModel mostrarEmpleado_Tabla(String busqueda){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("IdEmpleado");
        modelo.addColumn("IdUsuario");
        modelo.addColumn("IdSucursal");
        modelo.addColumn("Area");
        
        String hql;
        
        if(busqueda.equals("")){
            hql = "FROM D_Empleado";
            
        }else{
            hql = "FROM D_Empleado WHERE idEmpleado LIKE '" + busqueda + "%'";
        }
        
        try{
            miSession.beginTransaction();
            
            List<D_Empleado> empleados = miSession.createQuery(hql).getResultList();
            
            for(D_Empleado emp:empleados){
                Object registro[] = {
                    emp.getIdEmpleado(),
                    emp.getUsuario().getIdUsuario(),
                    emp.getSucursal().getIdSucursal(),
                    emp.getAreaEmpleado()};
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
    
    public boolean agregarEmpleado(String areaEmpleado, int idSucursal, int idUsuario){
        try{
            miSession.beginTransaction();
            D_Sucursal s = miSession.get(D_Sucursal.class, idSucursal);
            D_Usuario u = miSession.get(D_Usuario.class, idUsuario);
            D_Empleado emp = new D_Empleado(areaEmpleado, s, u);
            
            miSession.save(emp);
            miSession.getTransaction().commit();
            return true;
        }catch(HibernateException e){
            JOptionPane.showMessageDialog(null,"Error al agregar datos " + e);
            return false;
        }finally{
            miSession.close();
        }
    }
    
    public boolean editarEmpleado(int idEmpleado, String areaEmpleado, int idSucursal, int idUsuario){
        try{
            miSession.beginTransaction();
            
            D_Sucursal s = miSession.get(D_Sucursal.class, idSucursal);
            D_Usuario u = miSession.get(D_Usuario.class, idUsuario);
            D_Empleado emp = miSession.get(D_Empleado.class, idEmpleado);
            
            emp.setSucursal(s);
            emp.setUsuario(u);
            emp.setAreaEmpleado(areaEmpleado);
            
            miSession.update(emp);
            miSession.getTransaction().commit();
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error al editar datos " + e);
            return false;
        }finally{
            miSession.close();
        }
    }
    
    public boolean eliminarEmpleado(int idEmpleado){
        try{
            miSession.beginTransaction();
            
            D_Empleado emp = miSession.get(D_Empleado.class, idEmpleado);
            miSession.delete(emp);
            
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
    
    public ArrayList mostrarEmpleado_Combo(){
        ArrayList lista = new  ArrayList();
        String hql = "FROM D_Usuario Where estado=true and nivelUsuario=2";
        try{
            miSession.beginTransaction();
            List<D_Usuario> usuarios = miSession.createQuery(hql).getResultList();
            
            for(D_Usuario usu:usuarios){
                lista.add(usu.getIdUsuario());
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
    
    
    public  ArrayList IdSucursal(){
        ArrayList lista = new  ArrayList();
        String hql = "FROM D_Sucursal Where estado=true and nivelUsuario=2";
        try{
            miSession.beginTransaction();
            List<D_Sucursal> sucursales = miSession.createQuery(hql).getResultList();
            
            for(D_Sucursal sucursal:sucursales){
                lista.add(sucursal.getIdSucursal());
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
