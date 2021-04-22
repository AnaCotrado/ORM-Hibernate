
package Datos;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "tb_administrador")
public class D_Administrador implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAdministrador")
    private Integer idAdministrador;
    
    @Column(name = "CargoAdministrador")
    private String cargoAdministrador;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdUsuario")
    private D_Usuario Usuario;
    
    public D_Administrador() {
        this.idAdministrador = 0;
    }

    public D_Administrador(String cargoAdministrador, D_Usuario Usuario) {
        this.cargoAdministrador = cargoAdministrador;
        this.Usuario = Usuario;
    }

    public Integer getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Integer idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getCargoAdministrador() {
        return cargoAdministrador;
    }

    public void setCargoAdministrador(String cargoAdministrador) {
        this.cargoAdministrador = cargoAdministrador;
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

}
