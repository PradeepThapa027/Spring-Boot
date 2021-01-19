package com.example.demo.restcontroller;


import java.io.IOException;


import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.getschduler.Position;

@RestController
public class RestPosition {
	
	@Autowired
	private Position position;
	
	
	
	@RequestMapping(value = "/position")
	public void getPosition() {
		try {
			position.myGetRequest();
		} 
		catch (IOException | org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
	}
	
	//@PostMapping("/personnel/api/positions/")
	//@GetMapping("/personnel/api/positions/")
	@RequestMapping(value = "/personnel/api/positions/")
	public Object ajaxPosition() {
		
		JSONArray jsonArray = position.positionDetails();
		/*
		 * try { return position.myGetRequest(); } catch (IOException |
		 * org.json.simple.parser.ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		return jsonArray;
		//return position;
		
	}
}
