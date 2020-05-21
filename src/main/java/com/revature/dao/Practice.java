package com.revature.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Book;



public class Practice {
	
	public static void main(String[] args) {
		final Logger loggy = Logger.getLogger(Practice.class);
		loggy.info("hello");
		System.out.println("hello");
		Book book = new Book("1111111111111", "Adventure of Steve", "Steve", null);
		
		BookDAO bookDAO = new BookDAOImpl();
		List<Book> books = bookDAO.getAllBooks();
		//bookDAO.deleteBookByISBN("1111111111113");
		//book.setPrice(25.00);
		//bookDAO.updateBook(book);
		
		
		
//		public Book(String isbn, String title, String author, byte[] content) {
//		this.isbn13 = isbn;
//		this.title = title;
//		this.author = author;
//		this.publishDate = LocalDate.now();
//		this.content = content;
//	}
		
		System.out.println("hello");
		for (int i = 0 ; i < books.size(); i++) {
			System.out.println(books.get(i).getTitle());
		}
	}

}
