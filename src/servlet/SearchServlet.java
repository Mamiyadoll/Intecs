package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAO;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		リクエスト変数の検索文字列が空文字かどうか判定
		if(request.getParameter("keyword") == "") {
//			空文字の場合は商品全件取得する
			request.setAttribute("product",DAO.showProduct() );
//			商品一覧の大きさを取得し、リクエスト変数にセット
			request.setAttribute("number",DAO.showProduct().size());
		}else {
//			検索文字列を使って、検索処理
//			戻り値を、リクエスト変数にセット
			request.setAttribute("product", DAO.searchProduct(request.getParameter("keyword")));
//			取得したリストの大きさを取得し、リクエスト変数にセット
			request.setAttribute("number",DAO.searchProduct(request.getParameter("keyword")).size());
//			検索文字列をリクエスト属性にセット
			request.setAttribute("keyword", request.getParameter("keyword"));
		}

//		ページ遷移
		RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
