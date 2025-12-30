package com.auth.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.model.Order;
import com.auth.repo.OrderRepo;
import com.auth.service.PdfService;

@RestController
@RequestMapping("/pdf")
@CrossOrigin(origins="http://localhost:4200")
public class PdfController {
	@Autowired
	OrderRepo orderRepo;
@Autowired
private PdfService pdfService;
@GetMapping("/download/{orderId}")
public ResponseEntity<byte[]>downloadPdf(@PathVariable Integer orderId){
	Order order=orderRepo.findById(orderId).orElseThrow(()->new RuntimeException("Order id no found"));
	byte[] pdf=pdfService.generatePdf(order);
	return ResponseEntity.ok()
			 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order.pdf")
             .contentType(MediaType.APPLICATION_PDF)
             .body(pdf);
}
}
