package com.form.controller;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.form.entities.Image;
import com.form.service.ImageService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	@PostMapping("/upload/userId={id}")
	public ResponseEntity<Image> uplaodImage(@PathVariable String id,@RequestParam("file") MultipartFile file) {

		Image image = imageService.getImage(id);
		
		try {
			if(image==null) {
				image = imageService.saveImage(file,id);
				return ResponseEntity.ok(image);
			}
			else {
				image.setName(file.getOriginalFilename());
				image.setData(file.getBytes());
				image.setUserId(id);
				
				image = imageService.updateImage(image);
				return ResponseEntity.ok(image);
			}
		}
		catch(IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GetMapping("/get/image/userId={id}")
	public ResponseEntity<byte[]> getImage(@PathVariable String id){
		
		Image image = imageService.getImage(id);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).contentType(MediaType.IMAGE_PNG).contentType(MediaType.IMAGE_GIF).body(image.getData());
	}
	
	
}
