
package Datos;

import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name="tb_usuario")
public class D_Usuario{
    
    @Column(name = "NivelUsuario")
    private short nivelUsuario;
    
    @OneToMany(mappedBy = "Usuario")
    private Collection<D_Empleado> dEmpleadoCollection;
    
    @OneToMany(mappedBy = "Usuario")
    private Collection<D_Administrador> dAdministradorCollection;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IdUsuario")
    private int IdUsuario;
    
    @Column(name="DNIUsuario")
    private String DNI;
    
    @Column(name="NombreUsuario")
    private String nombre;
    
    @Column(name="ApellidosUsuario")
    private String apellido;
    
    @Column(name="LoginUsuario")
    private String login;
    
    @Column(name="PasswordUsuario")
    private String password;
    
    
    @Column(name="EstadoUsuario")
    private boolean estado;

    public D_Usuario() {
        this.IdUsuario = 0;
    }

    public D_Usuario(String DNI, String nombre, String apellido, String login, String password, short nivelUsuario, boolean estado) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.apellido = apellido;
        this.login = login;
        this.password = password;
        this.nivelUsuario = nivelUsuario;
        this.estado = estado;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNivel() {
        return nivelUsuario;
    }

    public void setNivel(short nivelUsuario) {
        this.nivelUsuario = nivelUsuario;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "tb_usuarios{" + "IdUsuario=" + IdUsuario + ", DNI=" + DNI + ", nombre=" + nombre + ", apellido=" + apellido + ", login=" + login + ", password=" + password + ", nivelUsuario=" + nivelUsuario + ", estado=" + estado + '}';
    } 


    @XmlTransient
    public Collection<D_Administrador> getDAdministradorCollection() {
        return dAdministradorCollection;
    }

    public void setDAdministradorCollection(Collection<D_Administrador> dAdministradorCollection) {
        this.dAdministradorCollection = dAdministradorCollection;
    }

    public short getNivelUsuario() {
        return nivelUsuario;
    }

    public void setNivelUsuario(short nivelUsuario) {
        this.nivelUsuario = nivelUsuario;
    }

    @XmlTransient
    public Collection<D_Empleado> getDEmpleadoCollection() {
        return dEmpleadoCollection;
    }

    public void setDEmpleadoCollection(Collection<D_Empleado> dEmpleadoCollection) {
        this.dEmpleadoCollection = dEmpleadoCollection;
    }
} 
