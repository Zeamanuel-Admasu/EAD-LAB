package com.itsc.ioc.OnlineBookstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SearchBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DBConnectionManager dbManager;

	public SearchBookServlet(DBConnectionManager dbManager) {
		this.dbManager = dbManager;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String query = request.getParameter("title");
		try (Connection connection = dbManager.openConnection()) {
			String sql = "SELECT * FROM Books WHERE title LIKE ?";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, "%" + query + "%");
			ResultSet resultSet = statement.executeQuery();
			PrintWriter out = response.getWriter();

			out.println("<html><body>");
			out.println("<table border='1'><tr><th>ID</th><th>Title</th><th>Author</th><th>Price</th></tr>");
			while (resultSet.next()) {
				out.println("<tr><td>" + resultSet.getInt("id") + "</td><td>" + resultSet.getString("title")
						+ "</td><td>" + resultSet.getString("author") + "</td><td>" + resultSet.getDouble("price")
						+ "</td></tr>");
			}
			out.println("</table></body></html>");
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error searching tasks");
		}
	}
}