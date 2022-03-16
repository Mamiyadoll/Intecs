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

public class CartDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CartDeleteServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
//    	indexにリダイレクト
    	response.sendRedirect("index.jsp");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		セッション取得
		HttpSession session = request.getSession();
//		セッション変数を取得
		UserBean user = (UserBean)session.getAttribute("user");
//		カート削除処理を実行
		DAO.deleteCart(user.getloginId());

//		カート画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("CartServlet");
		rd.forward(request, response);
	}

}
