/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package servlets;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Logout", urlPatterns = { "/Logout" })
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(LogoutServlet.class);

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals("JSESSIONID")){
					logger.info("JSESSIONID="+cookie.getValue());
					break;
				}
			}
		}
		//invalidate the session if exists
		HttpSession session = request.getSession(false);
		logger.info("User="+session.getAttribute("User"));
		if(session != null){
			session.invalidate();
		}
		response.sendRedirect("login.html");
	}
}