package com.jumbalakka.commons;

@SuppressWarnings( "serial" )
public class JumbalakkaException extends Exception
{
	public JumbalakkaException( String message )
	{
		super( message );
	}
	
	public JumbalakkaException( Exception e )
	{
		super( e );
	}
}
