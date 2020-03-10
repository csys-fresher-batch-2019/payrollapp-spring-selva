package com.chainsys.taskpayrollapp.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chainsys.taskpayrollapp.util.GetDataUtil;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api")
public class DesignationController {

	@GetMapping("/displaymail")
	public ArrayList<String> getEmail() throws Exception {
		GetDataUtil get = new GetDataUtil();
		return get.getAllEmail();
	}

	@GetMapping("/displaypan")
	public ArrayList<String> getPan() throws Exception {
		GetDataUtil get = new GetDataUtil();
		return get.getAllPan();
	}

	@GetMapping("/displayid")
	public ArrayList<Integer> getId() throws Exception {
		GetDataUtil get = new GetDataUtil();
		return get.getAllId();
	}

}
