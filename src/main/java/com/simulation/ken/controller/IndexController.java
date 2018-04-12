package com.simulation.ken.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "KenIndexController")
@RequestMapping("/ken")
public class IndexController {

	Logger logger = Logger.getLogger(this.getClass().getName());

	@RequestMapping("/test")
	public ResponseEntity<Map<String, Object>> test() {
		Map<String, Object> result = new HashMap<String, Object>();
		logger.info("test handle step 1");
		logger.info("test handle step 2");
		result.put("code", "1000");
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

	@RequestMapping("/test2")
	public ResponseEntity<Map<String, Object>> test2() {
		Map<String, Object> result = new HashMap<String, Object>();
		logger.info("test2 handle step 1");
		logger.info("test2 handle step 2");
		result.put("code", "1000");
		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}
