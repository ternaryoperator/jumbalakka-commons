package com.jumbalakka.commons.web;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
    private HttpServletRequest request;
    private HttpServletResponse response;
    private JumbalakkaUser user;
    
	protected void setErrorMessage( String message )
	{
		setRequestAttribute( REQ_ERROR_MSG, message );
	}
	
	protected void setInfoMessage( String message )
	{
		setRequestAttribute( REQ_INFO_MSG, message );
	}
	
	protected void appendErrorMessage( String message )
	{
		String errorMessage = StringUtils.defaultString( 
						getRequestAttribute( REQ_ERROR_MSG, String.class ) );
		if( StringUtils.isNotBlank( errorMessage ) )
		{
			errorMessage += "<br/>" + message;
		}
		else
		{
			errorMessage = message;
		}
		
		setErrorMessage( errorMessage );
	}
	
	protected void appendInfoMessage( String message )
	{
		String infoMessage = StringUtils.defaultString( 
						getRequestAttribute( REQ_INFO_MSG, String.class ) );
		if( StringUtils.isNotBlank( infoMessage ) )
		{
			infoMessage += "<br/>" + message;
		}
		else
		{
			infoMessage = message;
		}
		
		setInfoMessage( infoMessage );
	}
	
	protected void checkUserSession() throws ServletException
	{
    	Boolean sessionChecked = getRequestAttribute( USES_SESSION_HAS_BEEN_CHECKED, Boolean.class );
    	if( sessionChecked == null || sessionChecked == false )
    	{
			user = getSessionAttribute( USER_SESSION, JumbalakkaUser.class );
    	}
    	else
    	{
    		user = getRequestAttribute( USER_SESSION, JumbalakkaUser.class );
    	}
    	
    	if( user == null )
		{
			throw new ServletException( "Not an authorized user" );
		}
	}
    
	public JumbalakkaUser getUser()
	{
    	try
		{
			checkUserSession();
		}
		catch ( ServletException e )
		{
			return null;
		}
    	return user;
	}
	
	protected HttpServletRequest getRequest()
	{
		return request;
	}
	
	protected HttpServletResponse getResponse()
	{
		return response;
	}
	
	protected HttpSession getSession()
	{
		return request.getSession();
	}
	
	protected <T> T getSessionAttribute( String attrKey, Class< T > clazz )
	{
		Object obj = getSession().getAttribute( attrKey );
		if( obj != null && clazz.isInstance( obj ) )
		{
			return clazz.cast( obj );
		}
		return null;
	}
	
	protected <T> T getRequestAttribute( String attrKey, Class< T > clazz )
	{
		Object obj = getRequest().getAttribute( attrKey );
		if( obj != null && clazz.isInstance( obj ) )
		{
			return clazz.cast( obj );
		}
		return null;
	}
	
	protected void setSessionAttribute( String attrKey, Object value )
	{
		getSession().setAttribute( attrKey, value );
	}
	
	protected void setRequestAttribute( String attrKey, Object value )
	{
		getRequest().setAttribute( attrKey, value );
	}
	
	protected void doRedirect( String url ) throws IOException
	{
		getResponse().sendRedirect( url );
	}
	
	protected void doForward( String url ) throws ServletException, IOException 
	{
		RequestDispatcher dispatcher = getRequest().getRequestDispatcher( url );
	    dispatcher.forward( getRequest() , getResponse() );
	}
	
	private void setVariables( 
			HttpServletRequest request,
			HttpServletResponse response )
	{
		this.request = request;
		this.response = response;
	}
	
	@Override
	protected void doGet( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException, IOException
	{
		setVariables( req, resp );
		jumbDoGet( req, resp );
	}

	protected abstract void jumbDoGet( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException, IOException;
	
	@Override
	protected void doPost( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException, IOException
	{
		setVariables( req, resp );
		jumbDoPost( req, resp );
	}

	protected abstract void jumbDoPost( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException, IOException;
	
}
