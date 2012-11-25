package com.jumbalakka.commons.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.jumbalakka.commons.security.types.JumbalakkaUser;
import com.jumbalakka.commons.web.servlet.JumbalakkaLogonServlet;

@SuppressWarnings( "serial" )
public abstract class JumbalakkaAbstractServlet extends HttpServlet implements JumbalakkaCommonWeb
{
	protected Log logger = LogFactory.getLog( this.getClass() );
    
	protected void setErrorMessage( HttpServletRequest request, String message )
	{
		setRequestAttribute( request, REQ_ERROR_MSG, message );
	}
	
	protected void setInfoMessage( HttpServletRequest request, String message )
	{
		setRequestAttribute( request, REQ_INFO_MSG, message );
	}
	
	protected void appendErrorMessage( HttpServletRequest request, String message )
	{
		String errorMessage = StringUtils.defaultString( 
						getRequestAttribute( request, REQ_ERROR_MSG, String.class ) );
		if( StringUtils.isNotBlank( errorMessage ) )
		{
			errorMessage += "<br/>" + message;
		}
		else
		{
			errorMessage = message;
		}
		
		setErrorMessage( request, errorMessage );
	}
	
	protected void appendInfoMessage( HttpServletRequest request, String message )
	{
		String infoMessage = StringUtils.defaultString( 
						getRequestAttribute( request, REQ_INFO_MSG, String.class ) );
		if( StringUtils.isNotBlank( infoMessage ) )
		{
			infoMessage += "<br/>" + message;
		}
		else
		{
			infoMessage = message;
		}
		
		setInfoMessage( request, infoMessage );
	}
	
	protected <T> T getSessionAttribute( HttpSession session, String attrKey, Class< T > clazz )
	{
		Object obj = session.getAttribute( attrKey );
		if( obj != null && clazz.isInstance( obj ) )
		{
			return clazz.cast( obj );
		}
		return null;
	}
	
	protected <T> T getRequestAttribute( HttpServletRequest request, String attrKey, Class< T > clazz )
	{
		Object obj = request.getAttribute( attrKey );
		if( obj != null && clazz.isInstance( obj ) )
		{
			return clazz.cast( obj );
		}
		return null;
	}
	
	protected void setSessionAttribute( HttpSession session,String attrKey, Object value )
	{
		session.setAttribute( attrKey, value );
	}
	
	protected void setRequestAttribute( HttpServletRequest request, String attrKey, Object value )
	{
		request.setAttribute( attrKey, value );
	}
	
	protected void doRedirect( HttpServletResponse response, String url ) throws IOException
	{
		response.sendRedirect( url );
	}
	
	protected void doForward( HttpServletRequest request, 
			HttpServletResponse response, String url ) throws ServletException, IOException 
	{
		RequestDispatcher dispatcher = request.getRequestDispatcher( url );
	    dispatcher.forward( request , response );
	}
	
	@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException, IOException
	{
		jumbDoGet( req, resp );
	}

	protected abstract void jumbDoGet( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException, IOException;
	
	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException, IOException
	{
		jumbDoPost( req, resp );
	}

	protected abstract void jumbDoPost( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException, IOException;
	
}
