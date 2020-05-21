package com.revature.utillities;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.revature.model.Book;




public class Utilities {
	
	private static SessionFactory sessionFactory ;
	
	static {
		try {
			Configuration configuration = new Configuration();
		
			Properties settings = new Properties();
			settings.put(Environment.DRIVER, "org.postgresql.Driver");
			settings.put(Environment.URL,"jdbc:postgresql://rubeoutdb.ckpkeoyk6mwv.us-west-1.rds.amazonaws.com:5432/postgres");
			settings.put(Environment.USER, "LopezR013");
			settings.put(Environment.PASS, "Wtwmbiwfil13");
			settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
			settings.put(Environment.SHOW_SQL, "true");
		
			settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

			settings.put(Environment.HBM2DDL_AUTO, "none");

			configuration.setProperties(settings);

			configuration.addAnnotatedClass(Book.class);

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()

					.applySettings(configuration.getProperties()).build();

			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SessionFactory getSessionFaction() {
		return sessionFactory;
	}
	
}
