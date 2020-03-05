package com.chainsys.taskpayrollapp.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api")
public class DesignationController {

	@PostMapping("/getDesignation")
	public void getDesignation(@RequestParam("Deisgnation") String deg)
	{
		
	}
}
