package com.itsc.ioc.OnlineBookstore;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		switch (path) {
		case "/BookRegistrationServlet":
			new BookRegistrationServlet().doPost(request, response);
			break;
		case "/DeleteBookServlet":
			new DeleteBookServlet().doPost(request, response);
			break;
		case "/DisplayBooksServlet":
			new DisplayBooksServlet().doGet(request, response);
			break;
		
		default:
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
	}
}
