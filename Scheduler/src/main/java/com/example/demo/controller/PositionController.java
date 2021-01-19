package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PositionController {
	
	@GetMapping("/position/api")
	public String positionDetails() {
		return "Position/position";
	}

}
