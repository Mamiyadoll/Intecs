package beans;

import java.io.Serializable;

public class UserBean implements Serializable {

	//フィールド
	private static final long serialVersionUID = 1L;
	private String loginId;
	private String password;
	private String userName;
	private String postCode;
	private String address;
	private String tel;
	private String mail;
	private int loginFlg;

	//コンストラクタ
	public UserBean() {
	}

	public UserBean(String loginId, String password, String userName, String postCode, String address, String tel,
			String mail, int loginFlg) {
		this.loginId = loginId;
		this.password = password;
		this.userName = userName;
		this.postCode = postCode;
		this.address = address;
		this.tel = tel;
		this.mail = mail;
		this.loginFlg = loginFlg;
	}

	//メソッド
	public String getIsbn() {
		return loginId;
	}

	public String getPassword() {
		return password;
	}

	public String getUserName() {
		return userName;
	}

	public String getPostCode() {
		return postCode;
	}

	public String getAddress() {
		return address;
	}

	public String getTel() {
		return tel;
	}

	public String getMail() {
		return mail;
	}

	public int getLoginFlg() {
		return loginFlg;
	}
}
