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

public class PurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PurchaseServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		セッションの生成
		HttpSession session = request.getSession();
//		セッション変数からユーザIdを取得
		UserBean user = (UserBean)session.getAttribute("user");
//		それをもとに、購入処理
		DAO.purchase(user.getloginId());

//		購入確認画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("purchaseComplete.jsp");
		rd.forward(request,response);
	}

}
