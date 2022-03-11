package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.CartBean;
import beans.ProductBean;
import beans.UserBean;

public class DAO {

//	メソッド型！
//	public static 戻り値型 メソッド名(引数) {
//		Connection con = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		戻り値宣言
//
//		try {
//			データ取得
//			con =  accessDB();
//			String sql = "SQL文";
//			ps = con.prepareStatement(sql);
//			ps.setString(1, 変数名);
//			rs = ps.executeQuery();
//
//			取得データ格納変数宣言
//
//			while(rs.next()) {
//				rs.getString();
//			}
//			インスタンス生成
//
//		}catch(Exception e) {
//			return null;
//		} finally {
//			try {
//				closeDB(con, ps, rs);
//			} catch (Exception e) {
//				System.err.println(e.getMessage());
//			}
//		}
//		return 戻り値;
//	}


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
			return null;
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


//	ログインメソッド
	public static Integer login(String id,String pass) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer i = -1;

		try {
//			引数のログインIDとパスワードに一致するユーザー情報を取得
			con =  accessDB();
			String sql = "SELECT * FROM user WHERE loginId = ? AND password = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1,id);
			ps.setString(2, pass);
			rs = ps.executeQuery();

//			ログインIDとパスワードの組み合わせに一致するユーザーがいればログインフラグをみる、
//			いなければiに１（ID、パスの組み合わせが間違っていることを表す）を代入
			if(rs.next()) {
				int loginFlg = -1;
				loginFlg = rs.getInt("loginFlg");

//				ログインフラグが１（ログイン済）ならiに２（ログイン済であることを表す）を代入
//				０（未ログイン）ならログインフラグを１に変更
				if(loginFlg == 1) {
					i = 2;
				}else {
					String sql2 = "UPDATE user SET loginFlg = 1 WHERE loginId = ? AND password = ?";
					ps.setString(1,id);
					ps.setString(2, pass);
					rs = ps.executeQuery();
					i = 0;
				}
			}else {
				i = 1;
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
		return i;
	}


	public static boolean logout(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean logout = false;

		try {
//		該当するユーザーのログインフラグを０（ログアウト状態）にする
			con =  accessDB();
			String sql = "UPDATE user SET loginFlg=0 WHERE loginId = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

//		成功したら戻り値trueにする
			logout = true;

		}catch(Exception e) {
			return logout;
		} finally {
			try {
				closeDB(con, ps, rs);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
		return logout;
	}


	public static Integer signup(String id,String pass,String name,String post,String add,String telnum,String mailadd) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer i = -1;

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
				rs = ps.executeQuery();
				i = 0;
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
		return i;
	}




	//recommendメソッド

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
