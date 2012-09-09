package com.jumbalakka.commons.web.servlet;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import com.jumbalakka.commons.config.JumbalakkaPropertyPlaceHolderConfigure;
import com.jumbalakka.commons.web.JumbalakkaAbstractServlet;

@SuppressWarnings( "serial" )
public class JumbalakkaServletActionProcessor extends JumbalakkaAbstractServlet
{
	protected static String	paramEvent	= "EVENT";

	
	
	@Override
	public void init( ServletConfig config ) throws ServletException
	{
		super.init( config );
		String userSetparamEvent = config.getInitParameter( "paramEvent" );
		paramEvent = StringUtils.defaultString( userSetparamEvent, paramEvent );
	}

	public static void setParamEvent( String paramEvent )
	{
		JumbalakkaServletActionProcessor.paramEvent = paramEvent;
	}

	@Override
	protected void jumbDoGet( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException, IOException
	{
		throw new ServletException( "Operation not supported" );
	}

	@Override
	protected void jumbDoPost( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException, IOException
	{
		String event = req.getParameter( paramEvent );
		
		if( StringUtils.isBlank( event ) )
		{
			throw new ServletException( "No event has been defined" );
		}
		
		String newEvent = 
				JumbalakkaPropertyPlaceHolderConfigure.getPropertyValue( event );
		
		if( StringUtils.isBlank( newEvent ) )
		{
			throw new ServletException( "No corresponding page has been defined for this event" );
		}
		
		doForward( newEvent );
	}
	
	

}
