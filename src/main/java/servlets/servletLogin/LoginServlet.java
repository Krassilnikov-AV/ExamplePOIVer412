/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package servlets.servletLogin;

/**
 * Класс LoginServlet
 */

import dbConnection.User;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

@WebServlet(name = "Login", urlPatterns = { "/Login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static Logger logger = Logger.getLogger(LoginServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String errorMsg = null;
		if(email == null || email.equals("")){
			errorMsg ="User Email can't be null or empty";
		}
		if(password == null || password.equals("")){
			errorMsg = "Password can't be null or empty";
		}

		if(errorMsg != null){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>"+errorMsg+"</font>");
			rd.include(request, response);
		}else{

			Connection con = (Connection) getServletContext().getAttribute("DBConnection");
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement("select id, name, email,country from Users where email=? and password=? limit 1");
				ps.setString(1, email);
				ps.setString(2, password);
				rs = ps.executeQuery();

				if(rs != null && rs.next()){

					User user = new User(rs.getString("name"), rs.getString("email"), rs.getString("country"), rs.getInt("id"));
					logger.info("User found with details="+user);
					HttpSession session = request.getSession();
					session.setAttribute("User", user);
					response.sendRedirect("home.jsp");
				}else{
					RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
					PrintWriter out= response.getWriter();
					logger.error("User not found with email="+email);
					out.println("<font color=red>No user found with given email id, please register first.</font>");
					rd.include(request, response);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error("Database connection problem");
				throw new ServletException("DB Connection problem.");
			}finally{
				try {
					rs.close();
					ps.close();
				} catch (SQLException e) {
					logger.error("SQLException in closing PreparedStatement or ResultSet");;
				}
			}
		}
	}

}