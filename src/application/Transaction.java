package application;

public class Transaction {

	int id;
	String date;
	String payee;
	String category;
	String note;
	double amount;
	
	public Transaction(int id, String date, String payee, String category, String note, double amount) {
		
		this.id = id;
		this.date = date;
		this.payee = payee;
		this.category = category;
		this.note = note;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPayee() {
		return payee;
	}

	public void setPayee(String payee) {
		this.payee = payee;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return ("[id: " + id + ", date: " + date + ", payee: " + payee + ", category: " + category + ", note: " + note + ", amount: " + amount + "]");
	}
	

}
