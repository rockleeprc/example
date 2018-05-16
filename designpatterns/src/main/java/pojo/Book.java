package pojo;

import java.io.Serializable;

public class Book implements Serializable {

	private static final long serialVersionUID = 8817610572141063463L;
	private String title;
	private double price;

	public Book() {
		super();
	}

	public Book(String title, double price) {
		super();
		this.title = title;
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
