package com.texteditor.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.texteditor.model.business.Authenticator;
import com.texteditor.model.domain.User;

public class TextEditorServlet  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 523464349490699732L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String url = "/";
		
		if(uri.endsWith("/textedit")) {
			User loggedUser = (User) req.getSession().getAttribute("currentLoggedUser");
			
			if (!Authenticator.isValidUser(loggedUser)) {
				resp.sendRedirect("/login");
				return;
			}
			
			url = "/textedit.jsp";
		}
		
		getServletContext().getRequestDispatcher(url).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String url = "/";
		
		if(uri.endsWith("/textedit")) {
			User loggedUser = (User) req.getSession().getAttribute("currentLoggedUser");
			
			if (!Authenticator.isValidUser(loggedUser)) {
				resp.sendRedirect("/login");
				return;
			}
		}
	}
	
}
