package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBean;
import dao.DAO;

/**
 * Servlet implementation class SignUpServlet
 */
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignUpServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
//    	indexにリダイレクト
    	response.sendRedirect("index.jsp");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		遷移先URL格納用変数の宣言
		String url ="";
//		エラーメッセージ格納用変数を宣言
		String errorMessage;

//		リクエスト変数をもとにサインアップ処理
//		signupメソッドの戻り値をもとに処理を分ける
		if(DAO.signup(
				(String)request.getAttribute("loginId"),
				(String)request.getAttribute("password"),
				(String)request.getAttribute("userName"),
				(String)request.getAttribute("postCode"),
				(String)request.getAttribute("address"),
				(String)request.getAttribute("tel"),
				(String)request.getAttribute("mail")
				) == 0) {
//			サインアップ成功した場合
//			セッション生成
			HttpSession session = request.getSession();
//			セッション変数から、ユーザIDを取得
			UserBean user = (UserBean)session.getAttribute("user");
//			UserBeanインスタンスを生成し、セッション変数に格納
			session.setAttribute("user",DAO.createUserBeanInstance(user.getloginId()));
			url = "signupComplete.jsp";

		}else if((DAO.signup(
				(String)request.getAttribute("loginId"),
				(String)request.getAttribute("password"),
				(String)request.getAttribute("userName"),
				(String)request.getAttribute("postCode"),
				(String)request.getAttribute("address"),
				(String)request.getAttribute("tel"),
				(String)request.getAttribute("mail")
				) == 1)) {
//			同一ログインIDが既に存在していた場合
			errorMessage = "このログインIDは既に使われています。";
			request.setAttribute("errorMessage", errorMessage);
			url = "signup.jsp";

		} else {
//			それ以外のエラー
			errorMessage = "予期せぬエラーが発生しました。";
			request.setAttribute("errorMessage", errorMessage);
			url = "signup.jsp";
		}

//		ページ遷移
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
