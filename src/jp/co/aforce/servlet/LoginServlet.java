package jp.co.aforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.aforce.bean.LoginBean;
import jp.co.aforce.dao.LoginDAO;
import jp.co.aforce.hash.MD5;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		response.sendRedirect("/Login/jsp/login.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String password = request.getParameter("password");
		boolean isLogin = false;
		String path = "/Login/jsp/login.jsp";
		HttpSession session = request.getSession();

		if(id.equals("") || password.equals("")) {
			session.setAttribute("isLogin", isLogin);
			session.setAttribute("message", "idとpassword両方入力してください");
			response.sendRedirect(path);
		} else {

		MD5 md5 = new MD5();
		String hashPassword = md5.doHash(id, password);

		LoginBean bean = new LoginBean(id, hashPassword);
		LoginDAO dao = new LoginDAO();
		try {
			isLogin = dao.login(bean);
			if(isLogin) {
				path = "/jsp/success.jsp";
				session.setAttribute("success", id);
				session.setAttribute("isLogin", isLogin);
				request.getRequestDispatcher(path).forward(request, response);
			} else {
				session.setAttribute("isFail", id);
				session.setAttribute("isLogin", isLogin);
				session.setAttribute("message", "IDもしくはパスワードが違います");
//				request.getRequestDispatcher(path).forward(request, response);
				response.sendRedirect(path);
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("isLogin", isLogin);
			session.setAttribute("message", "ログインエラー:もう一度やり直してください");
			response.sendRedirect(path);
//			request.getRequestDispatcher(path).forward(request, response);
		}
		}

	}

}
