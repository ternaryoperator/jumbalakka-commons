package com.jumbalakka.commons.security.service;

import com.jumbalakka.commons.security.JumbalakkaSecurityException;
import com.jumbalakka.commons.security.types.JumbalakkaUser;

public interface JumbalakkaSecurityService
{
	/**
	 * will get a user if status not JumbalakkaUser.STATUS.DISABLED
	 * @param userId
	 * @return
	 */
	public JumbalakkaUser getUser( String userId );
	
	/**
	 * if ignoreStatus = true then will 
	 * will get a user irrespective of status
	 * @param userId
	 * @return
	 */
	public JumbalakkaUser getUser( String userId, boolean ignoreStatus );
	
	/**
	 * will add a user 
	 * encrypt the password before save
	 * check if user exists if it does then throws JumbalakkaSecurityException
	 * @param user
	 * @throws JumbalakkaSecurityException
	 */
	public void addUser( JumbalakkaUser user ) throws JumbalakkaSecurityException;
	
	/**
	 * will update user object
	 * will also update the password so make sure correct encrypted password
	 * 	is being passed to this routine.
	 * if user does not exists will throw JumbalakkaSecurityException
	 * @param user
	 * @throws JumbalakkaSecurityException
	 */
	public void updateUser( JumbalakkaUser user ) throws JumbalakkaSecurityException;
	
	/**
	 * will delete the user object
	 * if user not exists throw JumbalakkaSecurityException
	 * @hardDelete if set to true will delete even on status JumbalakkaUser.STATUS.DISABLED
	 * @param userId
	 * @throws JumbalakkaSecurityException
	 */
	public void deleteUser( String userId, boolean hardDelete ) throws JumbalakkaSecurityException;
	
	public boolean isAuthorizedUser( String userId, String passwordPlainText ) throws JumbalakkaSecurityException;
}
