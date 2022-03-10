package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.UserBean;

public class LoginFilter implements Filter {

    public LoginFilter() {
    }

	@Override
	public void destroy() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest)request).getSession();
		final UserBean loginIdFromSession = (UserBean)session.getAttribute("user");
		if(loginIdFromSession == null) {
			/****** アクセス不可 ******/

			// トップページに遷移
			// RequestDispatcher
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			// FORWARD
			rd.forward(request, response);

		}else {
			/****** アクセス可能 ******/
			// pass the request along the filter chain
			chain.doFilter(request, response);

		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自動生成されたメソッド・スタブ

	}

}
