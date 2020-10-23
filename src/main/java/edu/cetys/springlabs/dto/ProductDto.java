package edu.cetys.springlabs.dto;

public class ProductDto {

	private int sku; 
	private String name;
	private double price;
	private String currency; 
	private String image;
	
	public int getSku() {
		return sku;
	}
	
	public void setSku(int sku) {
		this.sku = sku;
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
	
	public String getCurrency() {
		return currency;
	}
	
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
}
