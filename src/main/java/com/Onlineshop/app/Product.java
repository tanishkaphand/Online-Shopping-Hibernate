package com.Onlineshop.app;

	import jakarta.persistence.*;

	@Entity
	@Table(name = "products")
	public class Product {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @Column(name = "name", nullable = false)
	    private String name;

	    @Column(name = "price")
	    private double price;

	    @Column(name = "quantity")
	    private int quantity;

	    // Default constructor (required by Hibernate)
	    public Product() {
	    }

	    // Constructor
	    public Product(String name, double price, int quantity) {
	        this.name = name;
	        this.price = price;
	        this.quantity = quantity;
	    }

	    // Getters and setters
	    public int getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public void setPrice(double price) {
	        this.price = price;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }

	    // toString
	    @Override
	    public String toString() {
	        return "Product [id=" + id + ", name=" + name +
	               ", price=" + price + ", quantity=" + quantity + "]";
	    }
	}



