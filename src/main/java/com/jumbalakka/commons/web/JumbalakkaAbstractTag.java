package com.jumbalakka.commons.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.jumbalakka.commons.security.types.JumbalakkaUser;
import com.jumbalakka.commons.web.servlet.JumbalakkaLogonServlet;

public abstract class JumbalakkaAbstractTag extends TagSupport implements JumbalakkaCommonWeb
{
	protected Log logger = LogFactory.getLog( this.getClass() );
    
	private ServletRequest request;
    private ServletResponse response;
    private HttpSession session;
    private JumbalakkaUser user;
	private JspWriter out;
	
    protected void write( String msgToWrite )
    {
    	try
		{
			out.write( msgToWrite.toCharArray() );
		}
		catch ( IOException e )
		{
			//should not happen in ideal case so eat up the message
			logger.error( e.getMessage() );
		}
    }
    
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
			user = getSessionAttribute( 
					JumbalakkaLogonServlet.USER_SESSION, JumbalakkaUser.class );
    	}
    	else
    	{
    		user = getRequestAttribute(  
					JumbalakkaLogonServlet.USER_SESSION, JumbalakkaUser.class );
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

	protected ServletRequest getRequest()
	{
		return request;
	}
	
	protected ServletResponse getResponse()
	{
		return response;
	}
	
	protected HttpSession getSession()
	{
		return session;
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
    
	@Override
	public int doEndTag() throws JspException
	{
		super.doEndTag();
		return doJumbEndTag();
	}

	protected abstract int doJumbEndTag() throws JspException;

	@Override
	public int doStartTag() throws JspException
	{
		super.doStartTag();
		return doJumbStartTag();
	}

	protected abstract int doJumbStartTag() throws JspException;
	
	@Override
	public void release()
	{
		super.release();
		jumbRelease();
	}

	protected abstract void jumbRelease();

	@Override
	public void setPageContext( PageContext pageContext )
	{
		super.setPageContext( pageContext );
		request = pageContext.getRequest();
		response = pageContext.getResponse();
		session = pageContext.getSession();
		out = pageContext.getOut();
	}
	
}