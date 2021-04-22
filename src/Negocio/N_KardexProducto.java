
package Negocio;

import Datos.D_Administrador;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class N_KardexProducto {
    SessionFactory miFactory = new Configuration()
            .configure("META-INF/hibernate.cfg.xml")
            .addAnnotatedClass(D_Administrador.class)
            .buildSessionFactory();
    
    Session miSession;
    
    public N_KardexProducto() {
        this.miSession = miFactory.openSession();
    }
    
}
