package com.jumbalakka.commons.security;

import com.jumbalakka.commons.JumbalakkaException;

@SuppressWarnings( "serial" )
public class JumbalakkaSecurityException extends JumbalakkaException
{

	public JumbalakkaSecurityException( Exception e )
	{
		super( e );
	}

	public JumbalakkaSecurityException( String message )
	{
		super( message );
	}
}
