
package Datos;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "tb_kardexproducto")
public class D_Kardexproducto{

    @JoinColumn(name = "IdProducto", referencedColumnName = "IdProducto")
    @ManyToOne(optional = false)
    private D_Producto idProducto;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdKardexProducto")
    private int idKardexProducto;
    
    @Column(name = "FechaKardexProducto")
    @Temporal(TemporalType.DATE)
    private Date fechaKardexProducto;
    
    @Column(name = "CantidadKardexProducto")
    private int cantidadKardexProducto;
    
    @Column(name = "TipoKardexProducto")
    private boolean tipoKardexProducto;
    
    @JoinColumn(name = "IdEmpleado")
    @OneToOne(cascade = CascadeType.ALL)
    private D_Empleado Empleado;

    public D_Kardexproducto() {
    }

    public D_Kardexproducto(Integer idKardexProducto) {
        this.idKardexProducto = idKardexProducto;
    }

    public D_Kardexproducto(Integer idKardexProducto, Date fechaKardexProducto, int cantidadKardexProducto, boolean tipoKardexProducto) {
        this.idKardexProducto = idKardexProducto;
        this.fechaKardexProducto = fechaKardexProducto;
        this.cantidadKardexProducto = cantidadKardexProducto;
        this.tipoKardexProducto = tipoKardexProducto;
    }

    public Integer getIdKardexProducto() {
        return idKardexProducto;
    }

    public void setIdKardexProducto(Integer idKardexProducto) {
        this.idKardexProducto = idKardexProducto;
    }

    public Date getFechaKardexProducto() {
        return fechaKardexProducto;
    }

    public void setFechaKardexProducto(Date fechaKardexProducto) {
        this.fechaKardexProducto = fechaKardexProducto;
    }

    public int getCantidadKardexProducto() {
        return cantidadKardexProducto;
    }

    public void setCantidadKardexProducto(int cantidadKardexProducto) {
        this.cantidadKardexProducto = cantidadKardexProducto;
    }

    public boolean getTipoKardexProducto() {
        return tipoKardexProducto;
    }

    public void setTipoKardexProducto(boolean tipoKardexProducto) {
        this.tipoKardexProducto = tipoKardexProducto;
    }

    public D_Empleado getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(D_Empleado idEmpleado) {
        this.Empleado = idEmpleado;
    }

    @Override
    public String toString() {
        return "Datos.TbKardexproducto[ idKardexProducto=" + idKardexProducto + " ]";
    }

    public D_Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(D_Producto idProducto) {
        this.idProducto = idProducto;
    }
    
}
