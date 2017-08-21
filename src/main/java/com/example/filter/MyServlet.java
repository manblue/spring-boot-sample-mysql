package com.example.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "myServlet",urlPatterns = "/")
public class MyServlet extends HttpServlet {

	private static Logger logger = LoggerFactory.getLogger(MyServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.debug("servlet:{} method{}", MyServlet.class.getName(),"doGet");
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.debug("servlet:{} method{}", MyServlet.class.getName(),"doPost");
		super.doPost(req, resp);
	}
}
