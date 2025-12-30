package com.auth.service;


import java.io.ByteArrayOutputStream;

import org.springframework.stereotype.Service;

import com.auth.model.Address;
import com.auth.model.Order;
import com.auth.model.OrderItem;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PdfService {
public byte[] generatePdf(Order order) {
	
	ByteArrayOutputStream out=new ByteArrayOutputStream();
	Document document =new Document();
	PdfWriter.getInstance(document, out);
	document.open();
	Font titleFont=new Font(Font.HELVETICA,16,Font.BOLD);
	document.add(new Paragraph("Order Invoice",titleFont));
	 document.add(new Paragraph("Customer Name: "+order.getUsers().getFullname()));
	 document.add(new Paragraph("Order id: "+order.getOrder_id()));
	 document.add(new Paragraph("Order status: "+order.getStatus()));
	 for(OrderItem item:order.getOrderItems()) {
		 document.add(new Paragraph("---------------------------------"));
		 document.add(new Paragraph("item details",titleFont));
		 document.add(new Paragraph("Product Name :"+item.getProduct().getName()));
		 document.add(new Paragraph("Unit price: "+item.getUnit_price()));
		 document.add(new Paragraph("item details",titleFont));
		 document.add(new Paragraph("Total price: "+item.getTotalprice()));
	 }
	 for(Address address:order.getUsers().getAddress()) {
		 document.add(new Paragraph("-------------------------------------"));
		 document.add(new Paragraph("ADDRESS",titleFont));
		 document.add(new Paragraph("State: "+address.getState()));
		 document.add(new Paragraph("Buidling Name: "+address.getBuilding_name()));
		 document.add(new Paragraph("City: "+address.getCity()));
		 document.add(new Paragraph("Pincode: "+address.getPincode()));
		 
		 
	 }
     document.close();
     return out.toByteArray();
   }
}
