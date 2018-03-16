package com.simulation.ken.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "KenIndexController")
@RequestMapping("/ken")
public class IndexController {

	@RequestMapping("/test")
	public String test() {
		return "hello test!";
	}
}
