package com.chainsys.taskpayrollapp.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chainsys.taskpayrollapp.exceptions.DBExceptions;
import com.chainsys.taskpayrollapp.util.GetDataUtil;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api")
public class DesignationController {

	@PostMapping("/displaymail")
	public ArrayList<String> getDesignation() throws DBExceptions, SQLException
	{
		GetDataUtil get = new GetDataUtil();
		return get.getAllEmail();
	}
}
