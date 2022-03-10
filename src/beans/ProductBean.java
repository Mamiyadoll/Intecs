package beans;

import java.io.Serializable;

public class ProductBean implements Serializable {

	//フィールド
	private static final long serialVersionUID = 1L;
	private static final int TAX_RATE = 10;
	private String isbn;
	private String productName;
	private String productNameKana;
	private int price;
	private String genre;
	private String authorName;
	private String authorNameKana;
	private String keyword;
	private int taxPrice;

	//コンストラクタ
	public ProductBean() {}
	public ProductBean(String isbn, String productName, String productNameKana, int price, String genre, String authorName, String authorNameKana, String keyword) {
		this.isbn = isbn;
		this.productName = productName;
		this.productNameKana = productNameKana;
		this.price = price;
		this.genre = genre;
		this.authorName = authorName;
		this.authorNameKana = authorNameKana;
		this.keyword = keyword;
		this.taxPrice = 0;
	}

	//メソッド
	public String getIsbn() {
		return isbn;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductNameKana() {
		return productNameKana;
	}

	public int getPrice() {
		return price;
	}

	public String getGenre() {
		return genre;
	}

	public String getAuthorName() {
		return authorName;
	}

	public String getAuthorNameKana() {
		return authorNameKana;
	}

	public String getKeyword() {
		return keyword;
	}

	public int getTaxPrice() {
		return price * (TAX_RATE + 100)/100;
	}
}



