package com.auth.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
@Entity
public class OrderItem {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	 private Integer order_item_id;
	    private Integer quantity;
	    private Double unit_price;
	    private Double totalprice;
	    @JsonBackReference
	    @ManyToOne
	    private Order order;

	    @ManyToOne
	    private Product product;
	    

		public Integer getOrder_item_id() {
			return order_item_id;
		}

		public void setOrder_item_id(Integer order_item_id) {
			this.order_item_id = order_item_id;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public Double getUnit_price() {
			return unit_price;
		}

		public void setUnit_price(Double unit_price) {
			this.unit_price = unit_price;
		}

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		public Double getTotalprice() {
			return totalprice;
		}

		public void setTotalprice(Double totalprice) {
			this.totalprice = totalprice;
		}
		
	    
}
