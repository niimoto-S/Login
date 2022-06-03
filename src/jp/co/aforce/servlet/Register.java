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
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/Login/jsp/register.jsp";
		response.sendRedirect(path);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = "/Login/jsp/register.jsp";
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		if(id.equals("") || password.equals("")) {
			response.sendRedirect(path);
		}
		MD5 md5 = new MD5();
		String hashPassword = md5.doHash(id, password);
		LoginBean bean = new LoginBean(id, hashPassword);
		LoginDAO dao = new LoginDAO();
		boolean isLogin = false;
		session.setAttribute("isLogin", isLogin);
		try {
			int line = dao.addUser(bean);
			if(line > 0) {
				session.setAttribute("message", "登録完了しました");
			} else {
				session.setAttribute("message", "登録できませんでした。もう一度やり直してください");
			}
		} catch (Exception e) {
			session.setAttribute("message", "IDが重複しています");
			e.printStackTrace();
		}
		path = "/Login/jsp/login.jsp";
		response.sendRedirect(path);

	}

}
