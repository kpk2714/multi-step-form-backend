package com.form.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.form.entities.Image;
import com.form.repository.ImageRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;
	
	
	public Image saveImage(MultipartFile file,String id) throws IOException {
		
		Image image = new Image();
		image.setName(file.getOriginalFilename());
		image.setData(file.getBytes());
		image.setUserId(id);
		
		return imageRepository.save(image);
	}
	
	public Image updateImage(Image image) {
		return imageRepository.save(image);
	}
	
	public Image getImage(String id) {
		Image image = imageRepository.findImageByUserId(id);
		System.out.println(image);
		return image;
	}
}
