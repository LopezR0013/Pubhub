package com.revature.web;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.revature.dao.BookDAO;
import com.revature.dao.BookDAOImpl;
import com.revature.model.Book;


@Controller
public class FileUploadController {
	
	
	
	@RequestMapping(value="/singleUpload", method=RequestMethod.POST)
	public ModelAndView getSingleUploadPage(@RequestParam("isbn13") String isbn13 ,@RequestParam("content") MultipartFile file,
			@RequestParam("title") String title, @RequestParam("author") String author, @RequestParam("price") double price) throws IOException {
		
		ModelAndView mv = new ModelAndView();
		boolean isSuccess= false;
		
			try {
				//Retrieving the data
				byte[] bytes = file.getBytes();
				
				
				//Creating the object
				Book book = new Book();
				book.setIsbn13(isbn13);
				book.setAuthor(author);
				book.setTitle(title);
				book.setPrice(price);
				book.setContent(bytes);
				BookDAOImpl bookDao = new BookDAOImpl();
				bookDao.addBook(book);
				
				isSuccess = true;
				mv.addObject("message", "Book successfully published");
				mv.addObject("messageClass", "alert-success");
				
			
			
			} catch (Exception e) {
				System.out.println(e);
			}
		
		
		mv.setViewName("publishBook");
		return mv;
		
	}
}
