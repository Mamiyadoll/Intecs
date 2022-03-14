package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import beans.CartBean;
import beans.ProductBean;
import beans.UserBean;

public class DAO {

//	DBアクセスメソッド
	public static Connection accessDB() throws ClassNotFoundException,SQLException{
		Connection con = null;
		String drivername = "com.mysql.cj.jdbc.Driver";
		Class.forName(drivername);
		String url = "jdbc:mysql://localhost:3306/freeDB?characterEncodeing=utf-8&serverTimezone=JST";
		String username = "root";
		String password = "";
		con = DriverManager.getConnection(url, username, password);
		return con;
	}

//	DB切断メソッド
	public static void closeDB(Connection con,PreparedStatement ps,ResultSet rs) throws SQLException{
		if(rs != null) {
			rs.close();
		}if(ps != null) {
			ps.close();
		}if(con != null) {
			con.close();
		}
	}

//	UserBeanインスタンス生成メソッド
	public static UserBean createUserBeanInstance(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserBean UserBean = null;
		try {
//			ログインIDに紐づくユーザー情報を取得
			con =  accessDB();
			String sql = "SELECT * FROM user WHERE loginId = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

//			取得データを変数に格納
			String loginId;
			String password;
			String userName;
			String postCode;
			String address;
			String tel;
			String mail;
			int loginFlg;

			rs.next();
				loginId = rs.getString("loginId");
				password = rs.getString("password");
				userName = rs.getString("userName");
				postCode = rs.getString("postCode");
				address = rs.getString("address");
				tel = rs.getString("tel");
				mail = rs.getString("mail");
				loginFlg = rs.getInt("loginFlg");

//			インスタンス生成
			UserBean = new UserBean(
					loginId,
					password,
					userName,
					postCode,
					address,
					tel,
					mail,
					loginFlg
					);
		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				closeDB(con, ps, rs);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return UserBean;
	}


//	書籍一覧取得メソッド
	public static List<ProductBean> showProduct(){
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<ProductBean> list = new ArrayList<ProductBean>();
		try {
//			商品全件取得
			con = accessDB();
			String sql = "SELECT * FROM product ORDER BY isbn";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

//			取得データを変数に格納
			String isbn;
			String productName;
			String productNameKana;
			int price;
			String genre;
			String authorName;
			String authorNameKana;
			String keyword;

			while(rs.next()) {
				isbn = rs.getString("isbn");
				productName = rs.getString("productName");
				productNameKana = rs.getString("productNameKana");
				price = rs.getInt("price");
				genre = rs.getString("genre");
				authorName = rs.getString("authorName");
				authorNameKana = rs.getString("authorNameKana");
				keyword = rs.getString("keyword");

//			インスタンス生成	、戻り値のListに追加
				ProductBean ProductBean = new ProductBean(
						isbn,
						productName,
						productNameKana,
						price,
						genre,
						authorName,
						authorNameKana,
						keyword
						);
				list.add(ProductBean);
			}

		} catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				closeDB(con, ps, rs);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return list;
	}


	public static List<ProductBean> searchProduct(String searchWord) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

//		検索結果をどんどん格納するList
		List<ProductBean> addList = new ArrayList<ProductBean>();
//		addListから重複するレコードを排除した戻り値用のList
		List<ProductBean> returnList = new ArrayList<ProductBean>();
//		引数の検索文字列を空白で区切って単語ごとに格納したList
		List<String> wordList = new ArrayList<String>();
//		検索する対象のカラム名を格納したList
		String[] columnList = {"productName","productNameKana","authorName","authorNameKana","keyword"};

		try {

//			引数の検索文字列を空白で区切ってwordListに格納する
			wordList = Arrays.asList(searchWord.replaceAll("　", " ").trim().split(" +"));

//			DB接続
			con =  accessDB();

//			columnListの各カラムを対象に、wordListの単語に曖昧一致するレコードを検索し、データを取得する
			for(String column : columnList) {
				for(String word : wordList) {

					String sql = "SELECT * FROM product WHERE ? LIKE %?%";
					ps = con.prepareStatement(sql);
					ps.setString(1, column);
					ps.setString(2, word);
					rs = ps.executeQuery();

//					取得データの分だけProductBeanのインスタンス生成し、addListにどんどんaddする
					while(rs.next()) {
						ProductBean productBean = new ProductBean(
								rs.getString("isbn"),
								rs.getString("productName"),
								rs.getString("productNameKana"),
								rs.getInt("price"),
								rs.getString("genre"),
								rs.getString("authorName"),
								rs.getString("authorNameKana"),
								rs.getString("keyword")
								);

						addList.add(productBean);
					}
				}
			}

//			addListの各レコードについて、returnListに同じ値があるかを確認し、
//			同じ値がなければaddListのレコードをreturnListに格納する
//			（boolean b をフラグとして用いる）

			for(int i = 0 ; i < addList.size() ; i++) {
				boolean b = true;

				for(int j = 0 ; j < returnList.size() && b ; j++) {
					if(addList.get(i).equals(returnList.get(j))) {
						b = false;
						}
					}

				if(b) {
					returnList.add(addList.get(i));
					}

				}

		}catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				closeDB(con, ps, rs);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return returnList;
	}


//	ログインメソッド
	public static Integer login(String id,String pass) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer i = -1;
		int ret = -1;

		try {
//			引数のログインIDとパスワードに一致するユーザー情報を取得
			con =  accessDB();
			String sql = "SELECT * FROM user WHERE loginId = ? AND password = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1,id);
			ps.setString(2, pass);
			rs = ps.executeQuery();

//			ログインIDとパスワードの組み合わせに一致するユーザーがいればログインフラグをみる、
//			ユーザーがいなければiに１（ID、パスの組み合わせが間違っていることを表す）を代入
			if(rs.next()) {
				int loginFlg = -1;
				loginFlg = rs.getInt("loginFlg");

//				ログインフラグが１（ログイン済）ならiに２（ログイン済であることを表す）を代入
//				０（未ログイン）ならログインフラグを１に変更して、
//				成功したらiに０（ログイン成功を表す）を代入
				if(loginFlg == 1) {
					i = 2;
				}else {
					String sql2 = "UPDATE user SET loginFlg = 1 WHERE loginId = ? AND password = ?";
					ps.setString(1,id);
					ps.setString(2, pass);
					ret = ps.executeUpdate();
					if(ret != 0) {
						i = 0;
					}
				}
			}else {
				i = 1;
			}

		}catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				closeDB(con, ps, rs);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return i;
	}


//	ログアウトメソッド
	public static boolean logout(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean logout = false;
		int ret = -1;

		try {
//		該当するユーザーのログインフラグを０（ログアウト状態）にする
			con =  accessDB();
			String sql = "UPDATE user SET loginFlg=0 WHERE loginId = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ret = ps.executeUpdate();

//		成功したら戻り値trueにする
			if(ret != 0) {
				logout = true;
			}

		}catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				closeDB(con, ps, rs);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return logout;
	}


//	新規登録メソッド
	public static Integer signup(String id,String pass,String name,String post,String add,String telnum,String mailadd) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer i = -1;
		int ret = -1;

		try {
//			ログインIDが同一のユーザーがいるかどうか確認
			con =  accessDB();
			String sql = "SELECT * FROM user WHERE loginId = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

//			取得データがある場合（同一ログインIDが既に使用されている場合）はiに１（ログインID既出を表す）を代入、
//			取得データがない場合は新規登録処理を行い、成功したらiに０（ログイン成功を表す）を代入
			if(rs.next()) {
				i = 1;
			}else {
				String sql2 = "INSERT INTO user VALUES(?,?,?,?,?,?,?,1)";
				ps = con.prepareStatement(sql2);
				ps.setString(1, id);
				ps.setString(2, pass);
				ps.setString(3, name);
				ps.setString(4, post);
				ps.setString(5, add);
				ps.setString(6, telnum);
				ps.setString(7, mailadd);
				ret = ps.executeUpdate();
				if(ret != 0) {
					i = 0;
				}
			}

		}catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				closeDB(con, ps, rs);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return i;
	}


//	カート内情報取得メソッド
	public static List<CartBean> showCart(String loginId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CartBean> list = new ArrayList<>();
		int taxRate = 10;

		try {
//			ログインIDに紐づくユーザーのカート内の情報を取得する
			con =  accessDB();
			String sql = "SELECT * FROM cart INNER JOIN product ON cart.isbn = product.isbn WHERE cart.loginId = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, loginId);
			rs = ps.executeQuery();

//			データ格納用の変数を用意、取得したデータを格納
			String isbn;
			String productName;
			String authorName;
			int price;
			int taxPrice;
			int quantity;

			while(rs.next()) {
				isbn = rs.getString("isbn");
				productName = rs.getString("productName");
				authorName = rs.getString("authorName");
				price = rs.getInt("price");
				taxPrice = price * (taxRate + 100) /100;
				quantity = rs.getInt("quantity");

//				インスタンス生成してListに追加
				CartBean CartBean = new CartBean(
						isbn,
						productName,
						authorName,
						price,
						taxPrice,
						quantity
						);

				list.add(CartBean);
			}

		}catch(Exception e) {
			System.err.println(e.getMessage());;
		} finally {
			try {
				closeDB(con, ps, rs);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return list;
	}


//	カートに商品を追加するメソッド
	public static boolean addCart(String loginId,String isbn,int quantity) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean add = false;
		int ret = -1;

		try {
//			引数で指定されたログインID・isbnの組み合わせに一致する情報がカートテーブルにあるかをみる
			con =  accessDB();
			String sql = "SELECT * FROM cart WHERE loginId = ? AND isbn = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, isbn);
			rs = ps.executeQuery();

//			取得データがある場合（同一ユーザーが同一商品をすでにカートに入れている場合）はそのレコードの数量を変更し、
//			ない場合は新しいレコードを追加する
//			いずれも成功したら戻り値trueにする

			if(rs.next()) {
				int nowQuantity;
				nowQuantity = rs.getInt("quantity");
				quantity += nowQuantity;
				String sql2 = "UPDATE cart SET quantity = ? WHERE loginId = ? AND isbn = ?";
				ps = con.prepareStatement(sql2);
				ps.setInt(1, quantity);
				ps.setString(2, loginId);
				ps.setString(3, isbn);
				ret = ps.executeUpdate();
				if(ret != 0) {
					add = true;
				}

			}else {
				String sql3 = "INSERT INTO cart VALUES(?,?,?)";
				ps = con.prepareStatement(sql3);
				ps.setString(1, loginId);
				ps.setString(2, isbn);
				ps.setInt(3, quantity);
				ret = ps.executeUpdate();
				if(ret != 0) {
					add = true;
				}
			}

		}catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				closeDB(con, ps, rs);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return add;
	}


//	カート内の商品を全削除するメソッド
	public static boolean deleteCart(String loginId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean delete = false;
		int ret = -1;

		try {
//			引数で指定されたユーザーのカートの内容を全削除
			con =  accessDB();
			String sql = "DELETE FROM cart WHERE loginId = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1,loginId);
			ret = ps.executeUpdate();

//			成功したら戻り値trueにする
			if(ret != 0) {
				delete = true;
			}

		}catch(Exception e) {
			System.err.println(e.getMessage());
		} finally {
			try {
				closeDB(con, ps, rs);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return delete;
	}

	public static List<ProductBean> recommend(String loginId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
//		戻り値宣言
		List<ProductBean> list = new ArrayList<ProductBean>();

		try {
//			データ取得
			con =  accessDB();
			String sql = "SELECT history.isbn,productName,productNameKana,price,genre,authorName,authorNam eKana,SUM(quantity) FROM product INNER JOIN history ON product.isbn = history.isbn WHERE genre = (SELECT genre FROM product INNER JOIN history ON product.isbn = history.isbn GROUP BY genre ORDER BY SUM(quantity) DESC LIMIT 1) && loginId = ? GROUP BY isbn ORDER BY SUM(quantity) DESC LIMIT 3";
			ps = con.prepareStatement(sql);
			ps.setString(1, loginId);
			rs = ps.executeQuery();

//			取得データ格納変数宣言

			//カーソルを最後に持っていく
			rs.last();

			if(rs.getRow() != 3) {
				String sql2 = "SELECT history.isbn,productName,productNameKana,price,genre,authorName,authorNam eKana,SUM(quantity) FROM product INNER JOIN history ON product.isbn = history.isbn GROUP BY isbn ORDER BY SUM(quantity) DESC LIMIT 3";
				ps = con.prepareStatement(sql2);
				ps.setString(1, loginId);
				rs = ps.executeQuery();
			}
//			//カーソルを最初に戻す
			rs.beforeFirst();

			//取得データを変数に格納
			String isbn;
			String productName;
			String productNameKana;
			int price;
			String genre;
			String authorName;
			String authorNameKana;

			while(rs.next()) {
				isbn = rs.getString("isbn");
				productName = rs.getString("productName");
				productNameKana = rs.getString("productNameKana");
				price = rs.getInt("price");
				genre = rs.getString("genre");
				authorName = rs.getString("authorName");
				authorNameKana = rs.getString("authorNameKana");

//			インスタンス生成	、戻り値のListに追加
				ProductBean ProductBean = new ProductBean(
						isbn,
						productName,
						productNameKana,
						price,
						genre,
						authorName,
					    authorNameKana, authorNameKana
						);
				list.add(ProductBean);
			}


		}catch(Exception e) {
			return null;
		} finally {
			try {
				closeDB(con, ps, rs);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return list;
	}

//	購入確定
	public static boolean purchase(String loginId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con =  accessDB();
			String sql = "SELECT * FROM cart WHERE loginId = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, loginId);
			rs = ps.executeQuery();

//		取得データ格納変数宣言
			List<CartBean> list = new ArrayList<CartBean>();

//		cartテーブルのデータからCartBeanインスタンス生成
//		不要データはnullか0を一時的に格納
//		生成したインスタンスをlistに格納
			while(rs.next()) {
				CartBean cb = new CartBean(
						rs.getString("isbn"),
						null,
						null,
						0,
						0,
						rs.getInt("quantity")
						);
				list.add(cb);
			}

//		listのデータをhistoryテーブルに格納
//		listの大きさ分だけinsertする
			for(CartBean cb1 : list) {
				sql = "INSERT INTO history VALUES(?,?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, loginId);
				ps.setString(2, cb1.getIsbn());
				ps.setInt(3, cb1.getQuantity());

				ps.executeUpdate();
			}

//			cartテーブルの、ログイン中ユーザのユーザIDを持つレコードを削除
			sql = "DELETE FROM cart WHERE loginId = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.executeUpdate();

			return true;

		}catch(Exception e) {
			return false;
		} finally {
			try {
				closeDB(con, ps, rs);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}












}
