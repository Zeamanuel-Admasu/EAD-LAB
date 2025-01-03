package com.itsc.ioc.OnlineBookstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DisplayBooksServlet")
public class DisplayBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (Connection conn = DBConnectionManager.openConnection()) {
			String sql = "SELECT * FROM Books";
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			//table created at the server and rendered on the user page
			PrintWriter out = response.getWriter();
			out.println("<html><body><table border='1'>");
			out.println("<tr><th>ID</th><th>title</th><th>author</th><th>price</th></tr>");
			while (rs.next()) {
				out.println("<tr><td>" + rs.getInt("id") + "</td>");
				out.println("<td>" + rs.getString("title") + "</td>");
				out.println("<td>" + rs.getString("author") + "</td>");
				out.println("<td>" + rs.getDouble("price") + "</td></tr>");
			}
			out.println("</table></body></html>");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
