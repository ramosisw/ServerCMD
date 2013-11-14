package servercmd;

import com.wayproyect.servercmd.entidades.Log;
import com.wayproyect.servercmd.persistencia.implementacion.LogPersistencia;
import com.wayproyect.servercmd.vistas.ServerCMDVista;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Julio C. Ramos
 * @e-mail ramos.isw@gmail.com
 */
public class ServerCMD {

    String name;//nombre servidor
    int port;//puerto
    ServerSocket s;
    DataInputStream entrada = null;
    DataOutputStream salida = null;
    ServerCMDVista vista;
    boolean run;
    Thread thread;

    public ServerCMD(String name, int port) {
        try {
            this.name = name;
            this.port = port;
            // Establece el servidor en el socket port (espera 300 segundos)
            this.s = new ServerSocket(port, 300);
        } catch (IOException ex) {
            Logger.getLogger(ServerCMD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ServerCMD(String name, int port, ServerCMDVista aThis) {
        try {
            this.name = name;
            this.port = port;
            vista = aThis;
            // Establece el servidor en el socket port (espera 300 segundos)
            this.s = new ServerSocket(port, 300);
        } catch (IOException ex) {
            Logger.getLogger(ServerCMD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {
        this.run = true;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Socket s1 = null;
                System.out.println("Cargado.");
                // Ejecuta un bucle infinito de listen/accept
                while (run) {
                    try {
                        // Espera para aceptar una conexión
                        System.out.println("Esperando conexion...");
                        s1 = s.accept();
                        System.out.println("Coexion Aceptada!");
                        entrada = new DataInputStream(s1.getInputStream());
                        salida = new DataOutputStream(s1.getOutputStream());
                        int op = entrada.readInt();
                        //Solo Busca
                        if (op == 0) {
                            String user = entrada.readUTF();
                            System.out.println("Me Busco: " + user);
                            //VA a ejecutar un comando
                        } else if (op == 1) {
                            String user = entrada.readUTF();
                            System.out.println("Se conecto: " + user);
                            Log log = new Log();
                            log.setAccion("Conecto");
                            log.setUsuario(user);
                            new LogPersistencia().guardar(log);
                            //Opcion de comando
                            int o = entrada.readInt();
                            Process child = null;
                            switch (o) {
                                //Test Conexion
                                case 0:
                                    log = new Log();
                                    log.setAccion("Test Conection");
                                    log.setUsuario(user);
                                    System.out.println("El Dispositivo testeo la conexion!");
                                    child = Runtime.getRuntime().exec("Shutdown -s -f");
                                    break;
                                //Apagar Computadora de inmediato
                                case 1:
                                    log = new Log();
                                    log.setAccion("Apagado");
                                    log.setUsuario(user);
                                    System.out.println("La computadora se apagara!");
                                    child = Runtime.getRuntime().exec("Shutdown -s -f");
                                    break;
                                //Reiniciar
                                case 2:
                                    log = new Log();
                                    log.setAccion("Reinicio");
                                    log.setUsuario(user);
                                    System.out.println("La computadora se reiniciara!");
                                    child = Runtime.getRuntime().exec("Shutdown -r -f");
                                    break;
                                //Cerrar Sesion
                                case 3:
                                    log = new Log();
                                    log.setAccion("Close Session");
                                    log.setUsuario(user);
                                    System.out.println("La computadora cerrara sesion!");
                                    child = Runtime.getRuntime().exec("Shutdown -l -f");
                                //Show Message
                                case 4:
                                    log = new Log();
                                    log.setAccion("Show Message");
                                    log.setUsuario(user);

                                    new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                JOptionPane.showMessageDialog(null, entrada.readUTF());
                                            } catch (IOException ex) {
                                                Logger.getLogger(ServerCMD.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (Throwable ex) {
                                                Logger.getLogger(ServerCMD.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    }).start();
                                    System.out.println("La computadora mostrara un mensaje!");
                                    break;
                                default:
                                    throw new AssertionError();
                            }
                            new LogPersistencia()
                                    .guardar(log);


                        }
                        vista.getLog();
                        salida.writeUTF("Se Ejecuto correctamente");
                        s1.close();
                        System.out.println("Cerrada!");
                        // Obtiene un controlador de fichero de salida asociado con
                        // el socket
                        // Cierra la conexión, pero no el socket del servidor

                    } catch (IOException ex) {
                        Logger.getLogger(ServerCMD.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        this.thread.start();
    }

    public void stop() {
        try {
            this.run = false;
            this.s.close();
            this.thread.stop();
        } catch (IOException ex) {
            Logger.getLogger(ServerCMD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ServerCMD server = new ServerCMD("ServerCMD-1", 5050);
        server.run();

    }
}
