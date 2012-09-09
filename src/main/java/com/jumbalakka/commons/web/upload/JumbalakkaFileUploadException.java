package com.jumbalakka.commons.web.upload;

import com.jumbalakka.commons.JumbalakkaException;

@SuppressWarnings( "serial" )
public class JumbalakkaFileUploadException extends JumbalakkaException
{
	public JumbalakkaFileUploadException( Exception e )
	{
		super( e );
	}
	
	public JumbalakkaFileUploadException( String message )
	{
		super( message );
	}
}
