package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import exception.DataBaseException;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String next = "Register.jsp";
		request.setCharacterEncoding("UTF-8");
		
		int loginId = Integer.parseInt(request.getParameter("loginID"));
		String userPassword = request.getParameter("loginPassword");
		String confirmationPassword = request.getParameter("confirmationLoginPassword");
		String userNickname = request.getParameter("nickname");
		
		if (userPassword.equals(confirmationPassword)) {
			try{
				UserDao userDao = new UserDao();
				//データベースにユーザーIDがあるか探す
				String userId = userDao.findUserId(loginId); 
				//なかったら新規登録
				if(userId == null) {
					//新規登録
					userDao.createStudent(loginId, userPassword, userNickname);
					next = "TopPage.jsp";
					request.setAttribute("message", "登録しました");
				//あったら更新
				}else {
					request.setAttribute("message", "入力されたIDは既に使われています");
				}
				
			}catch (DataBaseException e) {
				request.setAttribute("error", "true");
				e.printStackTrace();
			}
		}else {
			request.setAttribute("message", "パスワードが一致しません");
		}
		
		
		
		
		RequestDispatcher rd = request.getRequestDispatcher(next);
		rd.forward(request, response);
	}
}