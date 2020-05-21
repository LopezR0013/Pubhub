package com.revature.web;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.revature.dao.BookDAOImpl;
import com.revature.model.Book;


@Controller
public class BookDetailsController {
	
	@RequestMapping("/ViewBookDetails")
	public ModelAndView bookPublishing(@RequestParam("isbn13") String isbn) {
		
		//Grab the list  from the database
		BookDAOImpl dao = new BookDAOImpl();
		Book book = dao.getBookByISBN(isbn);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("book", book);
		mv.setViewName("bookDetails");
		return mv;
	}
	
	@RequestMapping("/UpdateBook")
	public ModelAndView updateBook(@RequestParam("isbn13") String isbn13, @RequestParam("title") String title,
			@RequestParam("author") String author, @RequestParam("price") Double price) {
		
		boolean isSuccess= false;
		
		ModelAndView mv = new ModelAndView();
		
		BookDAOImpl dao = new BookDAOImpl();
		Book book = dao.getBookByISBN(isbn13);
		
		if(book != null){
			// The only fields we want to be updatable are title, author and price. A new ISBN has to be applied for
			// And a new edition of a book needs to be re-published.
			book.setTitle(title);
			book.setAuthor(author);
			book.setPrice(price);
			mv.addObject("book",book);
			dao.updateBook(book);
			isSuccess = dao.updateBook(book);
		}else {
			//ASSERT: couldn't find book with isbn. Update failed.
			isSuccess = false;
		}
		if(isSuccess){
//			request.getSession().setAttribute("message", "Book successfully updated");
//			request.getSession().setAttribute("messageClass", "alert-success");
			mv.addObject("message","Book successfully updated" );
			mv.addObject("messageClass", "alert-success");
		
		}else {
//			request.getSession().setAttribute("message", "There was a problem updating this book");
//			request.getSession().setAttribute("messageClass", "alert-danger");
			mv.addObject("message","There was a problem updating this book" );
			mv.addObject("messageClass", "alert-success");
			
		}
		
		mv.setViewName("bookDetails");
		return mv;
	}
	
	@RequestMapping("/PublishBook")
	public ModelAndView getToPublishBookPage() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("publishBook");
		return mv;
	}

}
