package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		遷移先URL格納用の変数の宣言
		String url ;
//		エラーメッセージ格納用の変数の宣言
		String errorMessage;
//		リクエスト変数のログインID、パスワードを使ってログイン処理
//		ログインできた場合、セッション変数に、そのログインIDのUserBeanのインスタンスを格納
		if(DAO.login() == 0) {
//			ログインできた場合
//			セッションの取得
			HttpSession session = request.getSession();
//			セッション変数に格納
			session.setAttribute("user",DAO.createUserBeanInstance(request.getAttribute("loginId")));
//			遷移先URLに遷移先を格納
			url = "main.jsp";
		}else if(DAO.login() == 1) {
//			DBにユーザ、パスワードの組み合わせがない場合
			errorMessage = "登録されたユーザーが存在しません";
//			遷移先URLに遷移先を格納
			url = "login.jsp";
		}else if(DAO.login() == 2) {
//			すでにユーザーがログイン中の場合
			errorMessage = "すでにログイン中です。";
//			遷移先URLに遷移先を格納
			url = "login.jsp";
		}else {
			url = "login.jsp";
		}
		RequestDispatcher rd  = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
