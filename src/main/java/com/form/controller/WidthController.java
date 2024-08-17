package com.form.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.form.entities.Width;
import com.form.service.TokenService;
import com.form.service.WidthService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WidthController {
	
	@Autowired
	private WidthService widthService;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping("/saveWidth")
	public Width saveWidth(@RequestBody Width width, @RequestHeader("Authorization") String tokenId) throws Exception {
		
		Width newWidth = null;
		
		tokenService.validateToken(width.getUserId(), tokenId);
		
		if(width.getUserId()!=null && tokenId!=null) {
			newWidth = widthService.saveWidth(width);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return newWidth;
	}
	
	@GetMapping("/getWidth/userId={var1}/form={var2}")
	public int getWidth(@PathVariable String var1 , @PathVariable String var2) throws Exception {

		Width width = widthService.getWidth(var1, var2);
		if(width==null) {
			throw new Exception("There is an error !!!");
		}
		return width.getWidth();
	}
	
	
	@GetMapping("/getWidth/userId={id}")
	public int getWidthById(@PathVariable String id , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		tokenService.validateToken(id, tokenId);
				
		int max = 0;
		
		List<Width> width = widthService.getAllWidthById(id);
		for(int i=0;i<width.size();i++) {
			if(width.get(i).getWidth()>=max) {
				max = width.get(i).getWidth();
			}
		}
		
		return max;
	}
}
