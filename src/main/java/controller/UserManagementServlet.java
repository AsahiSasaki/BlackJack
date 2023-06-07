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

@WebServlet("/UserManagementServlet")
public class UserManagementServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		
		int loginID = Integer.parseInt((String)(session.getAttribute("userId")));
		
		try{
			UserDao userDao = new UserDao();
			userDao.deleteUser(loginID);	
			request.setAttribute("message", "ユーザーを削除しました");
		
			
		}catch(DataBaseException e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("TopPage.jsp");
		rd.forward(request, response);
	}
}
