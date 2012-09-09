package com.jumbalakka.commons.file.service;

import com.jumbalakka.commons.JumbalakkaException;
import com.jumbalakka.commons.file.types.JumbalakkaFile;
import com.jumbalakka.commons.security.JumbalakkaSecurityException;

public interface JumbalakkaFileService
{
	/**
	 * get the file with id, domain and catalog
	 * if ignorestatus = true then gets file irrespective of status its in
	 * if false then only gets active file
	 * @param id
	 * @param domain
	 * @param catalog
	 * @param ignoreStatus
	 * @return
	 */
	public JumbalakkaFile getJumballakaFile( 
			int id, String domain, 
			String catalog, boolean ignoreStatus ); 
	
	/**
	 * only gets active status file
	 * @param id
	 * @param domain
	 * @param catalog
	 * @return
	 */
	public JumbalakkaFile getJumballakaFile( 
			int id, String domain, String catalog ); 
	
	/**
	 * saves a file
	 * @param file
	 * @throws JumbalakkaSecurityException
	 */
	public void addJumbalakkaFile( JumbalakkaFile file );
	
	/**
	 * update the file
	 * @param file
	 * @throws JumbalakkaSecurityException
	 */
	public void updateJumbalakkaFile( JumbalakkaFile file );
	
	/**
	 * delete the file
	 * if hardDelete = false then simply set status to RecordStatus.DISABLED
	 * else deletes permanently
	 * @param file
	 * @param hardDelete
	 * @throws JumbalakkaSecurityException
	 */
	public void deleteJumbalakkaFile( JumbalakkaFile file, boolean hardDelete );
	
	public void addJumbalakkaFile( JumbalakkaFile file, boolean renameFileNameToID )
			throws JumbalakkaException;
}
