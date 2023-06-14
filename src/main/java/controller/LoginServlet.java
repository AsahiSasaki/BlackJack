package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import exception.DataBaseException;
import model.User;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.invalidate();
		
		request.setAttribute("message", "ログアウトしました");
		
		RequestDispatcher rd = request.getRequestDispatcher("TopPage.jsp");
		rd.forward(request, response);
		}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextpage = "null";
		request.setCharacterEncoding("UTF-8");

		int loginID = Integer.parseInt(request.getParameter("loginID"));
		String loginPassword = request.getParameter("loginPassword");
		//ログインIDとログインパスワードを取得
		
		try{
			UserDao userDao = new UserDao();
			User loginUser = userDao.doLogin(loginID, loginPassword);
			
			HttpSession session = request.getSession();
			session.setAttribute("loginuser", loginUser.getUserNickname());
			session.setAttribute("userId", loginUser.getUserId());
			session.setAttribute("loginPassword", loginUser.getLoginPassword());
			session.setAttribute("userChip", loginUser.getChip());
			
			nextpage = "Menu.jsp";
			
		}catch(DataBaseException e) {
			//ログインできなかったらログイン画面に戻す
			String message = e.getMessage();
			request.setAttribute("message", message);
			request.setAttribute("error", "true");
			nextpage = "TopPage.jsp";
		}catch (NumberFormatException e) {
			request.setAttribute("message", "IDには数字を入力してください");
			request.setAttribute("error", "true");
			nextpage = "TopPage.jsp";
		}
		
		//次の画面へ遷移
		
		RequestDispatcher rd = request.getRequestDispatcher(nextpage);
		rd.forward(request, response);
	}

}
