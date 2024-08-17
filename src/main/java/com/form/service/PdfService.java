package com.form.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.form.entities.Pdf;
import com.form.repository.PdfRepository;

@Service
public class PdfService {

	@Autowired
	private PdfRepository pdfRepository;
	
	public Pdf savePdf(MultipartFile file , String id , String name , String status) throws IOException {
		Pdf pdf = new Pdf();
		pdf.setFilename(file.getOriginalFilename());
		pdf.setUserId(id);
		pdf.setData(file.getBytes());
		pdf.setDocumentName(name);
		pdf.setStatus(status);
		
		return pdfRepository.save(pdf);
	}
	
	public Pdf updatePdf(Pdf pdf) {
		return pdfRepository.save(pdf);
	}
	
	public Pdf getPdf(String id,String name) {
		return pdfRepository.findPdfByUserIdAndDocumentName(id,name);
	}
	
	public Pdf getPdfByDocumentName(String name) {
		return pdfRepository.findPdfByDocumentName(name);
	}
}
