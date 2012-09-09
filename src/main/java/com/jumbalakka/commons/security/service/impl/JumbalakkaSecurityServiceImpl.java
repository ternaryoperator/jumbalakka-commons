package com.jumbalakka.commons.security.service.impl;

import org.apache.commons.lang.StringUtils;
import com.jumbalakka.commons.RecordStatus;
import com.jumbalakka.commons.security.JumbalakkaSecurityException;
import com.jumbalakka.commons.security.JumbalakkaSecurityUtils;
import com.jumbalakka.commons.security.dao.JumbalakkaSecurityDAO;
import com.jumbalakka.commons.security.service.JumbalakkaSecurityService;
import com.jumbalakka.commons.security.types.JumbalakkaUser;

public class JumbalakkaSecurityServiceImpl implements JumbalakkaSecurityService
{
	JumbalakkaSecurityDAO securityDAO;
	
	public JumbalakkaUser getUser( String userId )
	{
		return getUser( userId, false );
	}
	
	public JumbalakkaUser getUser( String userId, boolean ignoreStatus )
	{
		JumbalakkaUser user = securityDAO.getUser( userId );
		if( ignoreStatus == false )
		{
			if( user != null && 
					StringUtils.equals( 
							user.getStatus(), 
							RecordStatus.DISABLED.toString() 
					) )
			{
				return null;
			}
		}
		return user;
	}
	
	private String findDomain( String email )
	{
		String domain = "JUMBALAKKA";
		String emailSplit[] = StringUtils.split( email, '@' );
		if( emailSplit.length == 2  )
		{
			domain = emailSplit[1];
		}
		return domain;
	}
	
	public void addUser( JumbalakkaUser user ) throws JumbalakkaSecurityException
	{
		if( securityDAO.getUser( user.getUserId() ) == null )
		{
			String passwordPlainText = user.getPassword();
			String encryptedPasswd = 
					JumbalakkaSecurityUtils.encryptPassword( passwordPlainText );
			user.setPassword( encryptedPasswd );
			user.setDomain( 
					StringUtils.defaultIfBlank( 
							user.getDomain(), 
							findDomain( user.getEmail() ) 
					) );
			securityDAO.addUser( user );
		}
		else
		{
			throw new JumbalakkaSecurityException( "User ID already exists" );
		}
	}

	public void updateUser( JumbalakkaUser user ) throws JumbalakkaSecurityException
	{
		if( securityDAO.getUser( user.getUserId() ) == null )
		{
			throw new JumbalakkaSecurityException( "User ID does not exists" );
		}
		else
		{
			securityDAO.updateUser( user );
		}
	}

	public void deleteUser( 
			String userId, 
			boolean hardDelete ) throws JumbalakkaSecurityException
	{
		JumbalakkaUser user = getUser( userId, true );
		if( user == null )
		{
			throw new JumbalakkaSecurityException( "No such user found" );
		}
		if( hardDelete )
		{
			securityDAO.deleteUser( user );
		}
		else
		{
			user.setStatus( RecordStatus.DISABLED.toString() );
			securityDAO.updateUser( user );
		}
	}

	public boolean isAuthorizedUser( 
			String userId, 
			String passwordPlainText ) throws JumbalakkaSecurityException
	{
		JumbalakkaUser user = getUser( userId );
		if( user == null )
		{
			throw new JumbalakkaSecurityException( "User not registered in the system" );
		}
		if( JumbalakkaSecurityUtils.validPassword( passwordPlainText, user.getPassword() ) )
		{
			return true;
		}
		return false;
	}
	
}
