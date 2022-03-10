package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PurchaseConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PurchaseConfirmServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		購入確認画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("purchase.jsp");
		rd.forward(request, response);
	}

}
