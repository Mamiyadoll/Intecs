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

public class CartAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CartAddServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		セッション取得
		HttpSession session = request.getSession();
//		セッションからuserインスタンスを取得
		UserBean user = (UserBean)session.getAttribute("user");
//		リクエスト変数（追加商品のisbn,購入数）とセッション変数を利用して、カート追加処理
		DAO.addCart(
				user.getloginId(),
				request.getParameter("isbn"),
				Integer.parseInt(request.getParameter("count"))
				);

//		ページ遷移
		RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
		rd.forward(request, response);

	}

}
