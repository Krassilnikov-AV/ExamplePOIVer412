/*
 * Copyright (c) 2021 Tander, All Rights Reserved.
 */

package servlets.servletFilters;

/**
 * Класс AuthenticationFilter
 */
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	private Logger logger = Logger.getLogger(AuthenticationFilter.class);

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		logger.info("AuthenticationFilter initialized");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String uri = req.getRequestURI();
		logger.info("Requested Resource::"+uri);

		HttpSession session = req.getSession(false);

		if(session == null && !(uri.endsWith("html") || uri.endsWith("Login") || uri.endsWith("Register"))){
			logger.error("Unauthorized access request");
			res.sendRedirect("login.html");
		}else{
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		//close any resources here
	}
}