package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MainServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		遷移先URL格納用の変数を宣言
		String url;
//		セッション生成
		HttpSession session = request.getSession();
//		セッション変数が存在するかどうか確認
		if(session.getAttribute("userId") != null) {
//			おすすめ本をリクエスト変数に格納
			request.setAttribute("reccomend", DAO.reccomend(request.getAttribute("user")));
//			遷移先URLを格納
			url = "main.jsp";
		}else {
			url = "index.jsp";
		}

		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
