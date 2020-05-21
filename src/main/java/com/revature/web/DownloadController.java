package com.revature.web;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.revature.dao.BookDAO;
import com.revature.dao.BookDAOImpl;
import com.revature.model.Book;



@Controller
public class DownloadController {
	
	@RequestMapping(value = "/DownloadBook", method = RequestMethod.GET)
	public ResponseEntity<Resource> downloadPdf( @RequestParam("isbn13") String isbn13){
		
		
		BookDAO dao = new BookDAOImpl();
		Book book = dao.getBookByISBN(isbn13);
		
		try {
			return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/pdf"))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + book.getTitle() + ".pdf" +"\"")
					.body(new ByteArrayResource(book.getContent()));
			
		}
		catch (Exception e)
		{
			String message = "Error nel download del file";
		}
		return null;
	}
}
