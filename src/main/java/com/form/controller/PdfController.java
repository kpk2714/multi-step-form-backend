package com.form.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.form.entities.Pdf;
import com.form.service.PdfService;
import com.form.service.TokenService;

@RestController
@CrossOrigin("http://localhost:4200")
public class PdfController {

	@Autowired
	private PdfService pdfService;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/savePdf")
	public ResponseEntity<Pdf> savePdf(@RequestParam("file") MultipartFile file,@RequestParam("userId") String userId, @RequestParam("documentName") String documentName,@RequestParam("status") String status , @RequestHeader("Authorization") String tokenId) throws Exception{
		
		Pdf pdf = pdfService.getPdf(userId,documentName);
		
		tokenService.validateToken(userId, tokenId);
		
		try {
				if(tokenId!=null && userId!=null) {
					if(pdf==null) {
						pdf = pdfService.savePdf(file, userId , documentName , status);
						return ResponseEntity.ok(pdf);
					}
					else {
						pdf.setUserId(userId);
						pdf.setFilename(file.getOriginalFilename());
						pdf.setData(file.getBytes());
						pdf.setDocumentName(documentName);
						pdf.setStatus(status);
						
						pdf = pdfService.updatePdf(pdf);
						return ResponseEntity.ok(pdf);
					}
				}
				else {
					throw new Exception("Un-Authorized Access !!!");
				}
				
				
		} catch (IOException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	@GetMapping("/getPdf")
	public ResponseEntity<byte[]> getPdf(@RequestHeader("Authorization") String tokenId , @RequestParam("userId") String userId , @RequestParam("documentName") String documentName) throws Exception{
		
		tokenService.validateToken(userId, tokenId);
		
		Pdf pdf = null;
		
		if(tokenId!=null && userId!=null) {
			pdf = pdfService.getPdf(userId,documentName);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(pdf.getData());
	}
	
	@GetMapping("/getPdfByDocumentName")
	public Pdf getDocument(@RequestParam("documentName") String name , @RequestParam("userId") String userId , @RequestHeader("Authorization") String tokenId) throws Exception {
		
		tokenService.validateToken(userId, tokenId);
		
		Pdf pdf = null;

		if(tokenId!=null && userId!=null) {
			pdf = pdfService.getPdfByDocumentName(name);
		}
		else {
			throw new Exception("Un-Authorized Access !!!");
		}
		
		return pdf;
	}
}
