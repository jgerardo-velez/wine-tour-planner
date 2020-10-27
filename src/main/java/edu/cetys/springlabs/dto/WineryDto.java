package edu.cetys.springlabs.dto;

import java.util.List;

public class WineryDto {

	private int id;
	private String name;
	private String address;
	private String phone;
	private String website;
	private RegionDto region;
	private List<ProductDto> products;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getWebsite() {
		return website;
	}
	
	public void setWebsite(String website) {
		this.website = website;
	}
	
	public RegionDto getRegion() {
		return region;
	}

	public void setRegion(RegionDto region) {
		this.region = region;
	}

	public List<ProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}
	
}
