package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
//		リクエスト変数の検索文字列が空文字かどうか判定
		if(request.getParameter("searchWords") == "") {
			request.setAttribute("searchResult",DAO.showProduct() );
//			商品一覧の大きさを取得し、リクエスト変数にセット
			request.setAttribute("number",DAO.showProduct().size());
		}else {
//			検索文字列を使って、検索処理
//			戻り値を、リクエスト変数にセット
			request.setAttribute("searchResult", DAO.searchProduct(request.getParameter("searchWords")));
//			取得したリストの大きさを取得し、リクエスト変数にセット
			request.setAttribute("number",DAO.searchProduct(request.getParameter("searchWords").size()));
		}

//		ページ遷移
		RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
