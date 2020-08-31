package com.techelevator.controller;

import java.util.List;

import com.techelevator.dao.UserDAO;
import com.techelevator.util.EmployeeDataTable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(path="/user")
public class TableExampleRestController {

	@GetMapping("/rest/employees")
	public EmployeeDataTable getTableEmployeeData() {

		return EmployeeDataTable.getInstance();
	}

}
