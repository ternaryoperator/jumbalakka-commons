package com.jumbalakka.commons.config;

import java.io.IOException;
import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class JumbalakkaPropertyPlaceHolderConfigure extends PropertyPlaceholderConfigurer
{
	public static Properties props; 
	
	@Override
	protected void loadProperties( Properties props ) throws IOException
	{
		super.loadProperties( props );
		if( JumbalakkaPropertyPlaceHolderConfigure.props == null )
		{
			JumbalakkaPropertyPlaceHolderConfigure.props = props;
		}
		else
		{
			JumbalakkaPropertyPlaceHolderConfigure.props.putAll( props );
		}
	}
	
	public static String getPropertyValue( String key )
	{
		if( StringUtils.isNotBlank( key ) && props != null )
		{
			if( props.containsKey( key ) )
			{
				return props.getProperty( key );
			}
		}
		return null;
	}
	
	public static String getPropertyValue( String key, String defaultValue )
	{
		return 
			StringUtils.defaultString( getPropertyValue( key ), defaultValue );
	}
	
	public static int parseIntPropertyValue( String key )
	{
		return NumberUtils.toInt( getPropertyValue( key ) ); 
	}
	
	public static int parseIntPropertyValue( String key, int defaultValue )
	{
		return NumberUtils.toInt( getPropertyValue( key ), defaultValue ); 
	}
}
