package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.CartBean;
import beans.UserBean;
import dao.DAO;

public class PurchaseConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public PurchaseConfirmServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
//    	indexにリダイレクト
    	response.sendRedirect("index.jsp");
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		セッション取得
		HttpSession session = request.getSession();
//		セッション変数をを取得
		UserBean user = (UserBean)session.getAttribute("user");

//		セッション変数から、ログイン中のユーザのログインIDを取得
//		取得したログインIDを引数に、カート内表示処理
//		取得したリストをリクエスト変数にセット

		List<CartBean> CartBean = new ArrayList<CartBean>();
		CartBean = DAO.showCart(user.getloginId());
		request.setAttribute("cart",CartBean);

//		取得したリストの大きさを、リクエスト変数にセット
		request.setAttribute("productCount",CartBean.size());

//		カート内容の合計金額を計算してリクエスト属性にセット
		Integer totalPrice = 0;
		for(int i = 0 ; i < CartBean.size() ; i++) {
			totalPrice += (CartBean.get(i).getPrice() * CartBean.get(i).getQuantity());
		}
		request.setAttribute("totalPrice", totalPrice);

//		購入確認画面に遷移
		RequestDispatcher rd = request.getRequestDispatcher("purchase.jsp");
		rd.forward(request, response);
	}

}
