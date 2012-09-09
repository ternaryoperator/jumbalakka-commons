package com.jumbalakka.commons.security.dao;

import com.jumbalakka.commons.security.types.JumbalakkaUser;

public interface JumbalakkaSecurityDAO
{
	public JumbalakkaUser getUser( String userId );
	
	public void addUser( JumbalakkaUser user );
	
	public void updateUser( JumbalakkaUser user );
	
	public void deleteUser( JumbalakkaUser user );
	
}
