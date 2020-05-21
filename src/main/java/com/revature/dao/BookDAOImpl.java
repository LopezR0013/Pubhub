package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.revature.model.Book;
import com.revature.utillities.Utilities;



public class BookDAOImpl implements BookDAO {

	final Logger loggy = Logger.getLogger(BookDAOImpl.class);
	@Override
	public List<Book> getAllBooks() {
		
		loggy.info("getting all books");
		

		try {
			Session session = Utilities.getSessionFaction().openSession();
			
			@SuppressWarnings("unchecked")
			List<Book> results = (List<Book>) session.createQuery("from Book").list();
			
			
			return results;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		Utilities.getSessionFaction().close();
		return null;
		
	}

	
	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public List<Book> getBooksByTitle(String title) {
		loggy.info("Retriving " + title);
		List<Book> books = new ArrayList<>();

		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sessionFactory = null;
		
		try {
			Session session = Utilities.getSessionFaction().openSession();
			String hql = "FROM Book B WHERE B.title like CONCAT('%', :setTitle , '%')";
			
			@SuppressWarnings("unchecked")
			List<Book> results = (List<Book>) session.createQuery(hql).setString("setTitle", title).list();
			
			
			return results;
			
			
		}
		catch (Exception e) {
			System.out.println("Could not create connection!");
			e.printStackTrace();
			
			StandardServiceRegistryBuilder.destroy(registry);
		}
		
		
		
		
		return null;
		
	}

	
	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public List<Book> getBooksByAuthor(String author) {
		
		loggy.info("getting Author" + author);

		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sessionFactory = null;
		
		try {
			Session session = Utilities.getSessionFaction().openSession();
			
			String hql = "FROM Book B WHERE B.author like CONCAT('%', :setAuthor , '%')";
			
			List<Book> results = (List<Book>) session.createQuery(hql).setString("setAuthor", author).list();
			
			
			return results;
			
		}
		catch (Exception e) {
			System.out.println("Could not create connection!");
			e.printStackTrace();
			
			StandardServiceRegistryBuilder.destroy(registry);
		}
		
		
		
	
		
		
		return null;
		
	}

	
	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public List<Book> getBooksLessThanPrice(double price) {
		loggy.info("getting less than price" + price);
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sessionFactory = null;
		
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
			
			
		}
		catch (Exception e) {
			System.out.println("Could not create connection!");
			e.printStackTrace();
			
			StandardServiceRegistryBuilder.destroy(registry);
		}
		
		Session session = sessionFactory.openSession();
		
		String hql = "FROM Book B WHERE B.price < :setPrice";
		
		List<Book> results = (List<Book>) session.createQuery(hql).setDouble("setPrice", price).list();
		
		
		return results;
	}

	
	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public Book getBookByISBN(String isbn) {
		loggy.info("getting by isbn" + isbn);
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sessionFactory = null;
		
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
			
			
		}
		catch (Exception e) {
			System.out.println("Could not create connection!");
			e.printStackTrace();
			
			StandardServiceRegistryBuilder.destroy(registry);
		}
		
		Session session = sessionFactory.openSession();
		
		String hql = "FROM Book B WHERE B.isbn13 LIKE :setIsbn";
		
		Book results =  (Book) session.createQuery(hql).setString("setIsbn", isbn).uniqueResult();
		
		
		return results;
	}


	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public boolean addBook(Book book) {
		loggy.info("Adding bool" + book.getTitle());
		
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sessionFactory = null;
		
		try {
			Session session = Utilities.getSessionFaction().openSession();
			
			session.beginTransaction();
			
			session.save(book); //Here you have the magic

			session.getTransaction().commit();
			session.close();
			
			return true;
			
			
		}
		catch (Exception e) {
			System.out.println("Could not create connection!");
			e.printStackTrace();
			
			StandardServiceRegistryBuilder.destroy(registry);
		}
		
		
		return true;
	}

	
	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public boolean updateBook(Book book) {
		loggy.info("Updating book " + book.getTitle());
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sessionFactory = null;
		
		try {
			Session session = Utilities.getSessionFaction().openSession();
			session.beginTransaction();
			
			session.update(book); //Here you have the magic

			session.getTransaction().commit();
			session.close();
			
		}
		catch (Exception e) {
			System.out.println("Could not create connection!");
			e.printStackTrace();
			
			StandardServiceRegistryBuilder.destroy(registry);
		}
		
		
		
		return true;
		
	}

	
	/*------------------------------------------------------------------------------------------------*/

	
	@Override
	public boolean deleteBookByISBN(String isbn) {
		loggy.info("deleting book" + isbn);
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory sessionFactory = null;
		
		try {
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
			
			
		}
		catch (Exception e) {
			System.out.println("Could not create connection!");
			e.printStackTrace();
			
			StandardServiceRegistryBuilder.destroy(registry);
		}
		
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		String hql = "delete from Book B WHERE B.isbn13 = :setIsbn13 ";
		
		Query query = (Query) session.createQuery(hql).setString("setIsbn13", isbn);
		int count = query.executeUpdate();
		
		

		
		session.getTransaction().commit();
		session.clear();
		session.close();
		
		return true;
	}

}
