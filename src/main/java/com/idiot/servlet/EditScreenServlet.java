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
@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
	private static final String query="select bookname,bookedition,bookprice from bookdata where id=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		int id=Integer.parseInt(req.getParameter("id"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try(Connection con=DriverManager.getConnection("jdbc:mysql:///book","root","root");
				PreparedStatement ps=con.prepareStatement(query)){
				ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
				out.print("<form action='editurl?id="+id+"' method='post'>");
				out.print("<table align='center'>");
				out.print("<tr>");
				out.print("<td>Book Name</td>");
				out.print("<td><input type='text' name='bookName' value='"+rs.getString(1)+"'></td>");
				out.print("</tr>");
				out.print("<tr>");
				out.print("<td>Book Edition</td>");
				out.print("<td><input type='text' name='bookEdition' value='"+rs.getString(2)+"'></td>");
				out.print("</tr>");
				out.print("<tr>");
				out.print("<td>Book Price</td>");
				out.print("<td><input type='text' name='bookPrice' value='"+rs.getString(3)+"'></td>");
				out.print("</tr>");
				out.print("<tr>");
				out.print("<td><input type='submit' value='edit'></td>");
				out.print("<td><input type='reset' value='cancel'></td>");
				out.print("</tr>");
				out.print("</table>");
				out.print("</form>");
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
