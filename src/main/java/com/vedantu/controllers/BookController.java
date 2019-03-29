package com.vedantu.controllers;

import com.vedantu.daos.BookMongoDAO;
import com.vedantu.daos.CustomerMongoDAO;
import com.vedantu.daos.EmployeeDAO;
import com.vedantu.daos.EmployeeMongoDAO;
import com.vedantu.models.BookMongo;
import com.vedantu.models.CustomerMongo;
import com.vedantu.models.Employee;
import com.vedantu.models.EmployeeMongo;
import com.vedantu.models.ProductMongo;
import com.vedantu.requests.BookReq;
import com.vedantu.requests.CustomerReq;
import com.vedantu.requests.EmployeeReq;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.servlet.tags.Param;

@RestController
@RequestMapping("test4")
public class BookController {

	@Autowired
	private BookMongoDAO bookDAO;

//customer APIS	
	@RequestMapping(value = "/addBook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BookMongo addParam(@RequestBody BookReq param) throws Exception {

		BookMongo b = new BookMongo();
		b.setTitle(param.getTitle());
		b.setAuthor(param.getAuthor());
		b.setImage(param.getImage());
		b.setStatus(param.getStatus());
		bookDAO.create(b);

		return b;
	}

	// update
	@RequestMapping(value = "/updateBook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BookMongo updateParam(@RequestBody BookReq param) throws Exception {

		BookMongo b = bookDAO.getById(param.getId());
		b.setTitle(param.getTitle());
		b.setAuthor(param.getAuthor());
		b.setImage(param.getImage());
		b.setStatus(param.getStatus());
		bookDAO.update(b,null);

		return b;

	}


	// get
	@RequestMapping(value = "/getBooks", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Collection<BookMongo> getParam() throws Exception {
		Collection<BookMongo> book_info = bookDAO.getAll();
		return book_info;
	}
}
