package application;

public class Product {
    
	// Each of these variables will become a column in our TableView
	private String name;
	private double price;
	private int quantity;
	
	// First constructor to initialize instance variables
	public Product () { // This constructor sets default values
		this.name = "UNKNOWN PRODUCT";
		this.price = 0.00;
		this.quantity = 0;
		
	}
	
	// Second constructor to initialize instance variables to desired values
	// The two constructors are overloaded
	public Product(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	/**
	 * JavaFX tables will look for (behind the scenes) getters and setters for the 
	 * table values. The naming convention get"PropertyName" must be followed.
	 * Ex. getName Ex. getPrice Ex. getQuantity
	 */
	
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
	
	
	
}