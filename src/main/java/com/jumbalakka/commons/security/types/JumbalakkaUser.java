package com.jumbalakka.commons.security.types;

import com.jumbalakka.commons.LoginSource;
import com.jumbalakka.commons.types.JumbalakkaObject;

public class JumbalakkaUser extends JumbalakkaObject
{
	private String	userId;
	private String	password;
	private String	email;
	private String	status;
	private String 	domain;
	private LoginSource	source;

	public void setDomain( String domain )
	{
		this.domain = domain;
	}

	public String getDomain()
	{
		return domain;
	}

	public JumbalakkaUser()
	{
		setJumbType( "Jumbalakka.User" );
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId( String userId )
	{
		this.userId = userId;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword( String password )
	{
		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail( String email )
	{
		this.email = email;
	}

	public LoginSource getSource()
	{
		return source;
	}

	public void setSource( LoginSource source )
	{
		this.source = source;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus( String status )
	{
		this.status = status;
	}
}
