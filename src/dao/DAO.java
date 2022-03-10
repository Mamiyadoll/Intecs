package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {

//	DBアクセス用メソッド
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

//	DB切断用メソッド
	public static void closeDB(Connection con,PreparedStatement ps,ResultSet rs) throws SQLException{
		if(rs != null) {
			rs.close();
		}if(ps != null) {
			ps.close();
		}if(con != null) {
			con.close();
		}
	}

}
