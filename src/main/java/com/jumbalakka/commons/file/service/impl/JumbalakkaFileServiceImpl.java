package com.jumbalakka.commons.file.service.impl;

import java.io.File;
import org.apache.commons.lang.StringUtils;
import com.jumbalakka.commons.JumbalakkaException;
import com.jumbalakka.commons.RecordStatus;
import com.jumbalakka.commons.file.dao.JumbalakkaFileDAO;
import com.jumbalakka.commons.file.service.JumbalakkaFileService;
import com.jumbalakka.commons.file.types.JumbalakkaFile;

public class JumbalakkaFileServiceImpl implements JumbalakkaFileService
{
	JumbalakkaFileDAO jumbFileDAO;

	public void setJumbFileDAO( JumbalakkaFileDAO jumbFileDAO )
	{
		this.jumbFileDAO = jumbFileDAO;
	}

	public JumbalakkaFile getJumballakaFile( int id, String domain,
			String catalog, boolean ignoreStatus )
	{
		JumbalakkaFile file = jumbFileDAO.getJumballakaFile( id, domain, catalog );
		if( ignoreStatus == false )
		{
			if( file != null && 
					StringUtils.equals( file.getStatus(), RecordStatus.ENABLED.toString() ) )
			{
				return file;
			}
		}
		return file;
	}

	public JumbalakkaFile getJumballakaFile( int id, String domain,
			String catalog )
	{
		return getJumballakaFile( id, domain, catalog, false );
	}

	public void addJumbalakkaFile( JumbalakkaFile file )
	{
		jumbFileDAO.addJumballakaFile( file );
	}

	public void addJumbalakkaFile( JumbalakkaFile file, boolean renameFileNameToID )
				throws JumbalakkaException
	{
		addJumbalakkaFile( file );
		if( renameFileNameToID )
		{
			File f = new File( file.getNewFileAbsPath() );
			if( f.exists() == false )
			{
				throw new JumbalakkaException( "File does not exists" );
			}
			if( f.isDirectory() )
			{
				throw new JumbalakkaException( "File is a directory" );
			}
			String fileName = file.getOrignalFileName();
			String extension = StringUtils.substringAfterLast( fileName, "." );
			String newFileName = 
					f.getParent() + 
					File.separator + 
					file.getId() + 
					"." + extension ;
			File newFile = new File( newFileName );
			f.renameTo( newFile );
			
			file.setNewFileAbsPath( newFile.getAbsolutePath() );
			file.setNewFileName( newFile.getName() );
			updateJumbalakkaFile( file );
		}
	}
	
	public void updateJumbalakkaFile( JumbalakkaFile file )
	{
		jumbFileDAO.updateJumballakaFile( file );
	}

	public void deleteJumbalakkaFile( JumbalakkaFile file, boolean hardDelete )
	{
		if( hardDelete == false )
		{
			file.setStatus( RecordStatus.DISABLED.toString() );
			jumbFileDAO.updateJumballakaFile( file );
		}
		else
		{
			jumbFileDAO.deleteJumballakaFile( file );
		}
	}
}
