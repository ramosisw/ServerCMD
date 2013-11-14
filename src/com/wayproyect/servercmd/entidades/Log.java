package com.wayproyect.servercmd.entidades;

import java.util.Calendar;
import java.util.GregorianCalendar;



/**
 *
 * @author Julio C. Ramos
 * @e-mail ramos.isw@gmail.com
 */
public class Log {

    private int id;
    private String datetime;
    private String usuario;
    private String accion;
    public Log(){
        Calendar c=new GregorianCalendar();
        int DD=c.get(Calendar.DAY_OF_MONTH);
        int MM=c.get(Calendar.MONTH)+1;
        int YYYY=c.get(Calendar.YEAR);
        int h=c.get(Calendar.HOUR_OF_DAY);
        int m=c.get(Calendar.MINUTE);
        int s=c.get(Calendar.SECOND);
        int ms=c.get(Calendar.MILLISECOND);
        datetime=DD+"/"+MM+"/"+YYYY+" - "+h+":"+m+":"+s+":"+ms;
        //System.out.println(datetime);
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the datetime
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * @param datetime the datetime to set
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the accion
     */
    public String getAccion() {
        return accion;
    }

    /**
     * @param accion the accion to set
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }
}
