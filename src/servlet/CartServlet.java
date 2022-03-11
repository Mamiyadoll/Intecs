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

public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CartServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		セッション生成
		HttpSession session = request.getSession();
//		セッション変数からユーザIDを取得
		UserBean user = (UserBean)session.getAttribute("user");
//		セッション変数から、ログイン中のユーザのログインIDを取得
//		取得したログインIDを引数に、カート内表示処理
//		取得したリストをリクエスト変数にセット
		request.setAttribute("cartProducts",
				DAO.showCart(
						user.getloginId()
				)
		);
//		取得したリストの大きさを、リクエスト変数にセット
		request.setAttribute("productCount",
				DAO.showCart(user.getloginId()).size()
		);

//		ページ遷移
		RequestDispatcher rd = request.getRequestDispatcher("cart.jsp");
		rd.forward(request, response);
	}

}
