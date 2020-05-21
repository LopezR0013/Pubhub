package com.revature.web;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.revature.dao.BookDAOImpl;
import com.revature.model.Book;

@Controller
public class AddController {
	
	@RequestMapping("/add")
	public ModelAndView add(@RequestParam("t1") int num1, @RequestParam("t2") int num2) {
		
		ModelAndView mv = new ModelAndView();
		
		int k = num1 + num2;
		mv.addObject("result",k);
		mv.setViewName("result");
		
		
		return mv;
	}
	
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		return mv;
	}
	
	
	@RequestMapping("/BookPublishing")
	public ModelAndView bookPublishing() {
		
		//Grab the list  from the database
		BookDAOImpl dao = new BookDAOImpl();
		List<Book> bookList = dao.getAllBooks();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("books", bookList);
		mv.setViewName("bookPublishingHome");
		return mv;
	}
}
