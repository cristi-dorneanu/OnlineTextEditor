package com.texteditor.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.texteditor.model.business.Authenticator;
import com.texteditor.model.config.Resource;
import com.texteditor.model.dao.UserDAO;
import com.texteditor.model.dao.jdbc.UserDAOImpl;
import com.texteditor.model.domain.User;
import com.texteditor.utils.ControllerUtils;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6984848559109328172L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUri = req.getRequestURI();
		String url = "/";
		
		if(ControllerUtils.isPath(requestUri, Resource.login)) {
			url = "/authentication/login.jsp";
		} else if (ControllerUtils.isPath(requestUri, Resource.signup)) {
			url = "/authentication/signup.jsp";
		}
		
		this.getServletContext().getRequestDispatcher(url).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUri = req.getRequestURI();
		String url = "/authentication/login.jsp";
		UserDAO userDao = new UserDAOImpl();
		
		if(ControllerUtils.isPath(requestUri, Resource.login)) {
			User user = ControllerUtils.constructUserFromRequest(req);
			req.setAttribute("user", user);
			
			if(Authenticator.login(user, userDao)) {
				User currentLoggedUser = userDao.getUserByUsername(user.getUsername());
				
				req.getSession(true).setAttribute("currentLoggedUser", currentLoggedUser);
				resp.sendRedirect(Resource.textedit.url());
				
				return;
			}
		} else if (ControllerUtils.isPath(requestUri, Resource.signup)) {
			String signupStatus = "unsuccessfull";
			
			User user = ControllerUtils.constructUserFromRequest(req);
			
			req.setAttribute("user", user);
			url = "/authentication/signup.jsp";
			
			if(userDao.insert(user)) {
				req.getSession(true).setAttribute("currentLoggedUser", user);
				resp.sendRedirect(Resource.textedit.url());
				
				return;
			}
			
			req.setAttribute("signupStatus", signupStatus);
		}
		
		this.getServletContext().getRequestDispatcher(url).forward(req, resp);
	}

}
