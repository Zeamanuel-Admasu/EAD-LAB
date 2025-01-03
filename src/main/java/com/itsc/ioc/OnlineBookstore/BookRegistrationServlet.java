package com.itsc.ioc.OnlineBookstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/BookRegistrationServlet")
public class BookRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DBConnectionManager dbManager;
	/*
	 * // Question 7
	 * 
	 * @Autowired public BookRegistrationServlet(DBConnectionManager dbManager) {
	 * this.dbManager = dbManager; }
	 */

	

//         public BookRegistrationServlet() {
//		// TODO Auto-generated constructor stub
//	}



	//post method used
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String price = request.getParameter("price");
		try (Connection conn = DBConnectionManager.openConnection()) {
			String sql = "INSERT INTO Books (title, author, price) VALUES (?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, title);
			stmt.setString(2, author);
			stmt.setString(3, price);
			stmt.executeUpdate();
			// used printwriter inorder to show the output
			PrintWriter out = response.getWriter();
			out.println("Book registered successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
