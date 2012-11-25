package com.jumbalakka.commons.web.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.math.NumberUtils;
import com.jumbalakka.commons.JumbalakkaException;
import com.jumbalakka.commons.file.types.JumbalakkaFile;
import com.jumbalakka.commons.web.JumbalakkaAbstractServlet;
import com.jumbalakka.commons.web.upload.FileUploadUtils;
import com.jumbalakka.commons.web.upload.JumbalakkaFileUploadException;

public abstract class JumbalakkaFileUploadServlet extends JumbalakkaAbstractServlet
{
	private int maxMemorySize;
	private int maxFileUploadSize;
	private String tempDirectory;
	private String onErrorForwardUrl;
	private String onSucessForwardUrl;
	private int maxRequestSize;
	
	
	@Override
	public void init( ServletConfig config ) throws ServletException
	{
		maxMemorySize = NumberUtils.toInt( config.getInitParameter( "maxMemorySize" ) ); 
		maxFileUploadSize = NumberUtils.toInt( config.getInitParameter( "maxFileUploadSize" ) ); 
		maxRequestSize = NumberUtils.toInt( config.getInitParameter( "maxRequestSize" ) ); 
		tempDirectory = config.getInitParameter( "tempDirectory" ); 
		onErrorForwardUrl = config.getInitParameter( "onErrorForwardUrl" );
		onSucessForwardUrl = config.getInitParameter( "onSucessForwardUrl" );
	}

	@Override
	protected void jumbDoGet( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException, IOException
	{
		throw new ServletException( "Not valid method" );
		
	}

	@Override
	protected void jumbDoPost( HttpServletRequest req, HttpServletResponse resp )
			throws ServletException, IOException
	{
		//checkUserSession();
		
		try
		{
			doSecurityCheck();
		
			FileUploadUtils fileUploadUtils = new FileUploadUtils();
			fileUploadUtils.setMaxFileUploadSize( maxFileUploadSize );
			fileUploadUtils.setMaxMemorySize( maxMemorySize );
			fileUploadUtils.setTempDirectory( tempDirectory );
			fileUploadUtils.setMaxRequestSize( maxRequestSize );
			fileUploadUtils.doFileUpload( req );
			
			List<JumbalakkaFile> files = postPrcoessOfSavedFileLocally( fileUploadUtils );
		}
		catch ( JumbalakkaFileUploadException e )
		{
			setErrorMessage( req, e.getMessage() );
			doForward( req, resp, onErrorForwardUrl );
			return;
		}
		catch ( JumbalakkaException e )
		{
			setErrorMessage( req, e.getMessage() );
			doForward( req, resp, onErrorForwardUrl );
			return;
		}
		
		setInfoMessage( req, "Upload was successful" );
		doForward( req, resp, onSucessForwardUrl );
		
	}
	
	protected abstract List< JumbalakkaFile > 
				postPrcoessOfSavedFileLocally( FileUploadUtils fileUploadUtils ) 
					throws JumbalakkaException;
	
	protected abstract void doSecurityCheck() throws JumbalakkaException;
	
}
