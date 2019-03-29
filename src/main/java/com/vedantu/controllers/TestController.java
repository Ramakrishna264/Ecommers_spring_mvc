package com.vedantu.controllers;

import com.vedantu.daos.CustomerMongoDAO;
import com.vedantu.daos.EmployeeDAO;
import com.vedantu.daos.EmployeeMongoDAO;
import com.vedantu.models.CustomerMongo;
import com.vedantu.models.Employee;
import com.vedantu.models.EmployeeMongo;
import com.vedantu.models.ProductMongo;
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
@RequestMapping("test")
public class TestController {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Autowired
	private CustomerMongoDAO customerMongoDAO;

//customer APIS	
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomerMongo addParam(@RequestBody CustomerReq param) throws Exception {

		CustomerMongo e2 = new CustomerMongo();

		e2.setName(param.getName());
		e2.setPhno(param.getPhno());
		e2.setAddress(param.getAddress());
		e2.setAmount(param.getAmount());
		customerMongoDAO.create(e2);

		return e2;
	}

	// update
	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomerMongo updateParam(@RequestBody CustomerReq param) throws Exception {

		CustomerMongo e2 = customerMongoDAO.getById(param.getId());

		e2.setName(param.getName());
		e2.setPhno(param.getPhno());
		e2.setAddress(param.getAddress());
		e2.setAmount(param.getAmount());

		customerMongoDAO.update(e2, null);

		return e2;

	}

	// delete
	@RequestMapping(value = "/deleteCustomer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String deleteParam(@RequestBody CustomerReq param) throws Exception {

		CustomerMongo e2 = customerMongoDAO.getEntityById(param.getId(), CustomerMongo.class);

		customerMongoDAO.delete(e2, null);

		return "Delete Success";
	}

	// get
	@RequestMapping(value = "/getCustomers", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Collection<CustomerMongo> getParam() throws Exception {
		Collection<CustomerMongo> customer_info = customerMongoDAO.getAll();
		return customer_info;
	}
	
	// getbyId
	@RequestMapping(value = "/getCustomer/{customerId}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public CustomerMongo getParam(@PathVariable("customerId") String id) throws Exception {
		CustomerMongo e2 = customerMongoDAO.getById(id);
		return e2;

	}
}
