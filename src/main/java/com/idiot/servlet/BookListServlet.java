package com.idiot.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
	private static final String query="select * from bookdata";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///book","root","root");
				PreparedStatement ps=con.prepareStatement(query)){
				ResultSet rs=ps.executeQuery();
				out.print("<table border='1' align='center'>");
				out.print("<tr>");
				out.print("<th>Book Id</th>");
				out.print("<th>Book Name</th>");
				out.print("<th>Book Edition</th>");
				out.print("<th>Book Price</th>");
				out.print("<th>Edit</th>");
				out.print("<th>Delete</th>");
				out.print("</tr>");
				while(rs.next()) {
					out.print("<tr>");
					out.print("<td>"+rs.getInt(1)+"</td>");
					out.print("<td>"+rs.getString(2)+"</td>");
					out.print("<td>"+rs.getString(3)+"</td>");
					out.print("<td>"+rs.getFloat(4)+"</td>");
					out.print("<td><a href='editScreen?id="+rs.getInt(1)+"'>Edit</a></td>");
					out.print("<td><a href='deleteurl?id="+rs.getInt(1)+"'>Delete</a></td>");
					out.print("</tr>");
				}
				out.print("</table>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("<h1>"+e.getMessage()+"</h1>");
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.print("<h1>"+e.getMessage()+"</h1>");
			}
		out.print("<a href='home.html'>Home</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
