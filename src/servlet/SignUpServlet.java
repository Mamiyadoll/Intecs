package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SignUpServlet
 */
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SignUpServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		遷移先URL格納用変数の宣言
		String url;
//		エラーメッセージ格納用変数を宣言
		String errorMessage;
//		リクエスト変数をもとにサインアップ処理
		if(DAO.signUp(
				request.getAttribute("loginId"),
				request.getAttribute("password"),
				request.getAttribute("userName"),
				request.getAttribute("postCode"),
				request.getAttribute("address"),
				request.getAttribute("tel"),
				request.getAttribute("mail")
				) == 0) {
//			セッション生成
			HttpSession session = request.getSession();
//			UserBeanインスタンスを生成し、セッション変数に格納
			session.setAttribute("user",DAO.createUserBeanInstance(request.getAttribute("loginId")));

			url = "signupComplete.jsp";
		}else if((DAO.signUp(
				request.getAttribute("loginId"),
				request.getAttribute("password"),
				request.getAttribute("userName"),
				request.getAttribute("postCode"),
				request.getAttribute("address"),
				request.getAttribute("tel"),
				request.getAttribute("mail")
				) == 1)) {
			errorMessage = "このログインIDは既に使われています。";
			url = "signup.jsp";
		}

//		ページ遷移
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
