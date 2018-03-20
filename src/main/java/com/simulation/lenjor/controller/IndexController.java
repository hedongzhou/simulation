package com.simulation.lenjor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
public class IndexController {

	@RequestMapping(value = "/index")
	public Object index(@RequestParam String id,@RequestParam String name) {
		
		JSONObject result = new JSONObject();
		result.put("code", 1000);
		result.put("message","success");
		return result;
	}
}
