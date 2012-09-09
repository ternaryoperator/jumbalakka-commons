package com.jumbalakka.commons.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jumbalakka.commons.web.JumbalakkaAbstractServlet;
import com.jumbalakka.commons.web.JumbalakkaCommonWeb;

public class JumbalakkaLogonServlet extends JumbalakkaAbstractServlet implements JumbalakkaCommonWeb
{

	@Override
	protected void jumbDoGet( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException, IOException
	{
		throw new ServletException( "Undefined method" );
	}

	@Override
	protected void jumbDoPost( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		
	}
	
}
