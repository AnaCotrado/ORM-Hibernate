
package Datos;

import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(name = "tb_producto")
public class D_Producto {

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "PrecioProducto")
    private double precioProducto;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProducto")
    private int idProducto;
    
    @Column(name = "NombreProducto")
    private String nombre;
    
    @Column(name = "StockProducto")
    private int stock;
    
    
    @Column(name = "DescripcionProducto")
    private String descripcion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProducto")
    private Collection<D_Kardexproducto> Kardex;
    
    @JoinColumn(name = "IdCategoria")
    @ManyToOne
    private D_Categoria Categoria;
    
    @JoinColumn(name = "IdSucursal")
    @ManyToOne
    private D_Sucursal Sucursal;

    public D_Producto() {
        this.idProducto = 0;
    }

    public D_Producto(String nombre, int stock, double precio, String descripcion, Collection<D_Kardexproducto> Kardex, D_Categoria Categoria, D_Sucursal Sucursal) {
        this.nombre = nombre;
        this.stock = stock;
        this.precioProducto = precio;
        this.descripcion = descripcion;
        this.Kardex = Kardex;
        this.Categoria = Categoria;
        this.Sucursal = Sucursal;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Collection<D_Kardexproducto> getKardex() {
        return Kardex;
    }

    public void setKardex(Collection<D_Kardexproducto> Kardex) {
        this.Kardex = Kardex;
    }

    public D_Categoria getCategoria() {
        return Categoria;
    }

    public void setCategoria(D_Categoria Categoria) {
        this.Categoria = Categoria;
    }

    public D_Sucursal getSucursal() {
        return Sucursal;
    }

    public void setSucursal(D_Sucursal Sucursal) {
        this.Sucursal = Sucursal;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }
}
