package servercmd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julio C. Ramos
 * @e-mail ramos.isw@gmail.com
 */
public class CrearBD {

    public void CrearDB() {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:dbservercmd;create=true");
            con.createStatement().execute("create table tbl_log( id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                    + "datetime VARCHAR(50), "
                    + "usuario VARCHAR(50),"
                    + "accion VARCHAR(50) )");
            con.createStatement().execute("insert into tbl_log(datetime,usuario,accion) values "
                    + "('12-12-2013 - 59:59:59', 'Android', 'Test1') ,"
                    + "('12-12-2013 - 59:59:59', 'Android', 'Test2')");
            con.close();

            System.out.println("Se ha creado la tabla correctamente");
        } catch (SQLException ex) {
            //Logger.getLogger(CrearBD.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
    
    public void vaciarDB(){
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:dbservercmd;create=true");
            con.createStatement().execute("DROP TABLE tbl_log");
            con.createStatement().execute("create table tbl_log( id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), "
                    + "datetime VARCHAR(50), "
                    + "usuario VARCHAR(50),"
                    + "accion VARCHAR(50) )");
            con.close();

            System.out.println("Se ha creado la tabla correctamente");
        } catch (SQLException ex) {
            Logger.getLogger(CrearBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
