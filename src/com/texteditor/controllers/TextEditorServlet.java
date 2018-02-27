package com.texteditor.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.texteditor.model.business.Authenticator;
import com.texteditor.model.config.Resource;
import com.texteditor.model.dao.jdbc.UserDAOImpl;
import com.texteditor.model.domain.User;
import com.texteditor.utils.ControllerUtils;

public class TextEditorServlet  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 523464349490699732L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String url = "/";
		
		if(ControllerUtils.isPath(uri, Resource.textedit)) {
			User loggedUser = (User) req.getSession().getAttribute("currentLoggedUser");
			
			if (!Authenticator.login(loggedUser, new UserDAOImpl())) {
				resp.sendRedirect(Resource.login.url());
				return;
			}
			
			url = "/editor/textedit.jsp";
		}
		
		getServletContext().getRequestDispatcher(url).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		
		if(ControllerUtils.isPath(uri, Resource.textedit)) {
			User loggedUser = (User) req.getSession().getAttribute("currentLoggedUser");
			
			if (!Authenticator.login(loggedUser, new UserDAOImpl())) {
				resp.sendRedirect(Resource.login.url());
				return;
			}
		}
	}
	
}
