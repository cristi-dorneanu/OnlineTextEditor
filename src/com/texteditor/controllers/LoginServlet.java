package com.texteditor.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.texteditor.model.dao.UserDAO;
import com.texteditor.model.dao.jdbc.UserDAOImpl;
import com.texteditor.model.domain.User;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6984848559109328172L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUri = req.getRequestURI();
		String url = "/";
		
		if(requestUri.endsWith("/login")) {
			url = "/authentication/login.jsp";
		} else if (requestUri.endsWith("/signup")) {
			url = "/authentication/signup.jsp";
		}
		
		this.getServletContext().getRequestDispatcher(url).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUri = req.getRequestURI();
		String url = "/";
		
		if(requestUri.endsWith("/login")) {
			String username = req.getParameter("username");
			
			User user = new User();
			user.setUsername(username);
			
			req.setAttribute("user", user);
			
			if(isLoginSucc(username)) {
				url = "/authentication/loginSucc.jsp";
			} else {
				url = "/authentication/loginErr.jsp";
			}
		} else if (requestUri.endsWith("/signup")) {
			User user = new User();
			UserDAO userDao = new UserDAOImpl();
			String signupStatus = "unsuccessfull";
			
			user.setFirstName(req.getParameter("firstName"));
			user.setLastName(req.getParameter("lastName"));
			user.setEmail(req.getParameter("email"));
			user.setUsername(req.getParameter("username"));
			user.setPassword(req.getParameter("password"));
			
			req.setAttribute("user", user);
			url = "/authentication/signupSucc.jsp";
			
			if(userDao.insert(user)) {
				signupStatus = "successfull";
			}
			
			req.setAttribute("signupStatus", signupStatus);
		}
		
		this.getServletContext().getRequestDispatcher(url).forward(req, resp);
	}

	private boolean isLoginSucc(String username) {
		return username.equalsIgnoreCase("test");
	}
}
