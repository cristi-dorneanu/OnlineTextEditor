package com.texteditor.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.texteditor.model.business.Authenticator;
import com.texteditor.model.config.Resource;
import com.texteditor.model.dao.FileDAO;
import com.texteditor.model.dao.FileDAOManager;
import com.texteditor.model.dao.filesystem.FileDAOManagerImpl;
import com.texteditor.model.dao.jdbc.FileDAOImpl;
import com.texteditor.model.dao.jdbc.UserDAOImpl;
import com.texteditor.model.domain.File;
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
		FileDAO fileDao = new FileDAOImpl();
		String url = "/";
		
		User loggedUser = (User) req.getSession().getAttribute("currentLoggedUser");

		if (!Authenticator.login(loggedUser, new UserDAOImpl())) {
			resp.sendRedirect(Resource.login.url());
			return;
		}
		
		if(ControllerUtils.isPath(uri, Resource.textedit)) {
			url = "/editor/textedit.jsp";
		} else if (ControllerUtils.isPath(uri,  Resource.manage)) {
			List<File> files = fileDao.getFilesFromUser(loggedUser.getId());
			
			req.setAttribute("files", files);
			url = "/editor/manage.jsp";
		}
		
		getServletContext().getRequestDispatcher(url).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String url = "/";
		String action = req.getParameter("action");
		FileDAO fileDao = new FileDAOImpl();
		FileDAOManager fileManager = new FileDAOManagerImpl();
		
		User loggedUser = (User) req.getSession().getAttribute("currentLoggedUser");
		
		if (!Authenticator.login(loggedUser, new UserDAOImpl())) {
			resp.sendRedirect(Resource.login.url());
			return;
		}
		
		if(ControllerUtils.isPath(uri, Resource.textedit)) {
			if (action.equals("save")) {
				File file = new File();
				file.setFileName(req.getParameter("filename"));
				file.setFilePath(loggedUser.getServerRelativePath());
				
				if(fileManager.writeToDisk(new StringBuffer(req.getParameter("textarea")), file)) {
					if(fileDao.saveFile(loggedUser.getId(), file)) {
						req.setAttribute("status", "success");
					}
				}
				
				req.setAttribute("status", "shit");
				url = "/editor/textedit.jsp";
			}
		}
		
		getServletContext().getRequestDispatcher(url).forward(req, resp);
	}
	
}
