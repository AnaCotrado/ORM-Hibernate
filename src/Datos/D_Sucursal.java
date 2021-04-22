
package Datos;

import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "tb_sucursal")
public class D_Sucursal {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Sucursal")
    private Collection<D_Empleado> dEmpleadoCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Sucursal")
    private Collection<D_Producto> dProductoCollection;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSucursal")
    private int idSucursal;
    
    @Column(name = "NombreSucursal")
    private String nombre;
    
    @Column(name = "DireccionSucursal")
    private String direccion;
    
    @JoinColumn(name = "IdAdministrador")
    @OneToOne(cascade = CascadeType.ALL)
    private D_Administrador Administrador;
    
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<D_Empleado> Empleados;
    
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<D_Producto> Productos;

    public D_Sucursal() {
        this.idSucursal = 0;
    }

    public D_Sucursal(String nombre, String direccion, D_Administrador Administrador) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.Administrador = Administrador;
    }
    

    public D_Sucursal(String nombre, String direccion, D_Administrador Administrador, Collection<D_Empleado> Empleados, Collection<D_Producto> Productos) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.Administrador = Administrador;
        this.Empleados = Empleados;
        this.Productos = Productos;
    }

    
    public D_Administrador getAdministrador() {
        return Administrador;
    }

    public void setAdministrador(D_Administrador Administrador) {
        this.Administrador = Administrador;
    }

    public void setAdministrador(int idAdministrador) {
        this.Administrador.setIdAdministrador(idAdministrador);
    }
    
    public Collection<D_Empleado> getEmpleados() {
        return Empleados;
    }

    public void setEmpleados(Collection<D_Empleado> Empleados) {
        this.Empleados = Empleados;
    }

    public Collection<D_Producto> getProductos() {
        return Productos;
    }

    public void setProductos(Collection<D_Producto> Productos) {
        this.Productos = Productos;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombreSucursal() {
        return nombre;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombre = nombreSucursal;
    }

    public String getDireccionSucursal() {
        return direccion;
    }

    public void setDireccionSucursal(String direccionSucursal) {
        this.direccion = direccionSucursal;
    }

    @Override
    public String toString() {
        return "D_Sucursal{" + "idSucursal=" + idSucursal + ", nombreSucursal=" + nombre + ", direccionSucursal=" + direccion + ", Administrador=" + Administrador + ", Empleados=" + Empleados + ", Productos=" + Productos + '}';
    }

    @XmlTransient
    public Collection<D_Empleado> getDEmpleadoCollection() {
        return dEmpleadoCollection;
    }

    public void setDEmpleadoCollection(Collection<D_Empleado> dEmpleadoCollection) {
        this.dEmpleadoCollection = dEmpleadoCollection;
    }

    @XmlTransient
    public Collection<D_Producto> getDProductoCollection() {
        return dProductoCollection;
    }

    public void setDProductoCollection(Collection<D_Producto> dProductoCollection) {
        this.dProductoCollection = dProductoCollection;
    }
}
