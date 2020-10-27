package edu.cetys.springlabs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products", schema="wine-tour-planner")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sku;
	
	@Column(name="winery_id", nullable=false, unique=false)
	private int wineryId;
	
	@Column(name="name", length=100, nullable=false, unique=false)
	private String name;
	
	@Column(name="price", nullable=true, unique=false)
	private double price;
	
	@Column(name="currency", length=10, nullable=true, unique=false)
	private String currency;
	
	@Column(name="image", length=25, nullable=true, unique=false)
	private String image;

	public int getSku() {
		return sku;
	}

	public void setSku(int sku) {
		this.sku = sku;
	}

	public int getWineryId() {
		return wineryId;
	}

	public void setWineryId(int wineryId) {
		this.wineryId = wineryId;
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
