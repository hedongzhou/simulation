package com.simulation.my.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {

	@RequestMapping("/test")
	public String test() {
		return "hello test!";
	}
}
