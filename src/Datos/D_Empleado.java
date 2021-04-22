
package Datos;

import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "tb_empleado")
public class D_Empleado{

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Empleado")
    private Collection<D_Kardexproducto> dKardexproductoCollection;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEmpleado")
    private int idEmpleado;
    
    @Column(name = "AreaEmpleado")
    private String areaEmpleado;
    
    @JoinColumn(name = "IdSucursal", referencedColumnName = "IdSucursal")
    @OneToOne(cascade = CascadeType.ALL)
    private D_Sucursal Sucursal;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdUsuario")
    private D_Usuario Usuario;

    public D_Empleado() {
        this.idEmpleado = 0;
    }

    public D_Empleado(String areaEmpleado, D_Sucursal idSucursal, D_Usuario Usuario) {
        this.areaEmpleado = areaEmpleado;
        this.Sucursal = idSucursal;
        this.Usuario = Usuario;
    }
    
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getAreaEmpleado() {
        return areaEmpleado;
    }

    public void setAreaEmpleado(String areaEmpleado) {
        this.areaEmpleado = areaEmpleado;
    }

    public D_Sucursal getSucursal() {
        return Sucursal;
    }

    public void setSucursal(D_Sucursal Sucursal) {
        this.Sucursal = Sucursal;
    }
    
    public void setSucursal(int idSucursal) {
        this.Sucursal.setIdSucursal(idSucursal);
    }

    public D_Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(D_Usuario Usuario) {
        this.Usuario = Usuario;
    }
    
    public void setUsuario(int idUsuario) {
        this.Usuario.setIdUsuario(idUsuario);
    }

    
    @Override
    public String toString() {
        return "D_Empleado{" + "idEmpleado=" + idEmpleado + ", areaEmpleado=" + areaEmpleado + ", idSucursal=" + Sucursal + ", Usuario=" + Usuario + '}';
    }

    @XmlTransient
    public Collection<D_Kardexproducto> getDKardexproductoCollection() {
        return dKardexproductoCollection;
    }

    public void setDKardexproductoCollection(Collection<D_Kardexproducto> dKardexproductoCollection) {
        this.dKardexproductoCollection = dKardexproductoCollection;
    }
}
