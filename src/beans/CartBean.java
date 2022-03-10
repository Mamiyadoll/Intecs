package beans;

import java.io.Serializable;

public class CartBean implements Serializable {

	//フィールド
	private static final long serialVersionUID = 1L;
	private String isbn;
	private String productName;
	private String authorName;
	private int price;
	private int taxPrice;
	private int quantity;

	//コンストラクタ
	public CartBean() {}
	public CartBean(String isbn, String productName, String authorName, int price, int taxPrice, int quantity) {
		this.isbn = isbn;
		this.productName = productName;
		this.authorName = authorName;
		this.price = price;
		this.taxPrice = taxPrice;
		this.quantity = quantity;
	}

	//メソッド
	public String getIsbn() {
		return isbn;
	}

	public String getProductName() {
		return productName;
	}

	public String getauthorName() {
		return authorName;
	}

	public int getPrice() {
		return price;
	}

	public int getTaxPrice() {
		return taxPrice;
	}

	public int getQuantity() {
		return quantity;
	}



}
