package com.Onlineshop.app;

	import jakarta.persistence.*;
	import java.time.LocalDate;

	@Entity
	@Table(name = "orders")
	public class Order {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @Column(name = "product_id")
	    private int productId;

	    private int quantity;

	    @Column(name = "order_date")
	    private LocalDate orderDate;

	    public Order() {
	    }

	    public Order(int productId, int quantity, LocalDate orderDate) {
	        this.productId = productId;
	        this.quantity = quantity;
	        this.orderDate = orderDate;
	    }

	    // Getters
	    public int getId() {
	        return id;
	    }

	    public int getProductId() {
	        return productId;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    public LocalDate getOrderDate() {
	        return orderDate;
	    }

	    // Setters
	    public void setProductId(int productId) {
	        this.productId = productId;
	    }

	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }

	    public void setOrderDate(LocalDate orderDate) {
	        this.orderDate = orderDate;
	    }

	    @Override
	    public String toString() {
	        return id + "\t" + productId + "\t" + quantity + "\t" + orderDate;
	    }
	}


