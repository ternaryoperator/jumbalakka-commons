package com.jumbalakka.commons.file.dao;

import com.jumbalakka.commons.file.types.JumbalakkaFile;

public interface JumbalakkaFileDAO
{
	public JumbalakkaFile getJumballakaFile( int id, String domain, String catalog );
	
	public void addJumballakaFile( JumbalakkaFile file );
	
	public void updateJumballakaFile( JumbalakkaFile file );
	
	public void deleteJumballakaFile( JumbalakkaFile file );
}
