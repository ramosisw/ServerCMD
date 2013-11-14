package com.wayproyect.servercmd.persistencia.implementacion;

import com.wayproyect.servercmd.entidades.Log;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author Julio C. Ramos
 * @e-mail ramos.isw@gmail.com
 */
public class LogPersistencia {

    public List<Log> obtenerLog() {
        List<Log> logs = null;
        Hibernate hibernate = new Hibernate();
        try {
            logs = (List<Log>) (hibernate.sesion.createQuery("from Log")
                    .list());
            hibernate.transaccion.commit();
        } catch (HibernateException ex) {
            System.out
                    .println("Ocurrio un error durante la transaccion: " + ex);
        } finally {
            hibernate.sesion.close();
        }
        return logs;
    }

    public void guardar(Log log) {
        Hibernate hibernate = new Hibernate();
        try {
            hibernate.sesion.save(log); 
            hibernate.transaccion.commit();
        } catch (HibernateException ex) {
            System.out
                    .println("Ocurrio un error durante la transaccion: " + ex);
        } finally {
            hibernate.sesion.close();
        }
    }
}
