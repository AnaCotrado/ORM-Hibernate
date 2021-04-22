
package Datos;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "tb_categoria")
public class D_Categoria {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "Categoria")
    private Collection<D_Producto> dProductoCollection;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCategoria")
    private int idCategoria;
    
    @Column(name = "NombreCategoria")
    private String nombre;
    
    @Column(name = "DescripcionCategoria")
    private String descripcion;

    public D_Categoria() {
        this.idCategoria = 0;
    }

    public D_Categoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public D_Categoria( String nombreCategoria, String descripcionCategoria) {
        this.nombre = nombreCategoria;
        this.descripcion = descripcionCategoria;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombreCategoria) {
        this.nombre = nombreCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcionCategoria) {
        this.descripcion = descripcionCategoria;
    }

    @XmlTransient
    public Collection<D_Producto> getDProductoCollection() {
        return dProductoCollection;
    }

    public void setDProductoCollection(Collection<D_Producto> dProductoCollection) {
        this.dProductoCollection = dProductoCollection;
    }

    
}
