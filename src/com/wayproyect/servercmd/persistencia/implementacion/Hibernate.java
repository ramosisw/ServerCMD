package com.wayproyect.servercmd.persistencia.implementacion;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Clase para la manipulcacion de la base de datos usando Hibernate
 * @author Julio C. Ramos
 * E-Mail: ramos.isw@gmail.com
 *
 */
public class Hibernate {
	Configuration configuration;
	ServiceRegistry serviceRegistry;
	SessionFactory factory;
	Session sesion;
	Transaction transaccion;
	/**
	 * Constructor para inicilizar los atributos
	 */
	public Hibernate(){
		this.configuration = new Configuration();
		this.configuration.configure();
		this.serviceRegistry = new ServiceRegistryBuilder()
		.applySettings(this.configuration.getProperties())
		.buildServiceRegistry();
		this.factory = this.configuration
				.buildSessionFactory(this.serviceRegistry);
		this.sesion = factory.openSession();
		this.transaccion=this.sesion.beginTransaction();
		
	}
	/**
	 * @return El valor de configuration
	 */
	public Configuration getConfiguration() {
		return configuration;
	}
	/**
	 * @return El valor de serviceRegistry
	 */
	public ServiceRegistry getServiceRegistry() {
		return serviceRegistry;
	}
	/**
	 * @return El valor de factory
	 */
	public SessionFactory getFactory() {
		return factory;
	}
	/**
	 * @return El valor de sesion
	 */
	public Session getSesion() {
		return sesion;
	}
	/**
	 * @return El valor de transaccion
	 */
	public Transaction getTransaccion() {
		return transaccion;
	}
	
}
