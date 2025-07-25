package com.kh.model.vo;

public class Product {
	
	private String product_Id;
	private String p_Name;
	private Number price;
	private String description;
	private Number stock;
	
	public Product() {}

	public Product(String product_Id, String p_Name, Number price, String description, Number stock) {
		super();
		this.product_Id = product_Id;
		this.p_Name = p_Name;
		this.price = price;
		this.description = description;
		this.stock = stock;
	}

	public String getProduct_Id() {
		return product_Id;
	}

	public void setProduct_Id(String product_Id) {
		this.product_Id = product_Id;
	}

	public String getP_Name() {
		return p_Name;
	}

	public void setP_Name(String p_Name) {
		this.p_Name = p_Name;
	}

	public Number getPrice() {
		return price;
	}

	public void setPrice(Number price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Number getStock() {
		return stock;
	}

	public void setStock(Number stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Product [product_Id=" + product_Id + ", p_Name=" + p_Name + ", price=" + price + ", description="
				+ description + ", stock=" + stock + "]";
	}
	
	
	
}
