package com.jumbalakka.commons.web.upload;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileUploadUtils
{
	protected Log logger = LogFactory.getLog( this.getClass() );
    
	Map< String, String >	param	= new HashMap< String, String >();
	//Sets the size threshold beyond which files are written directly to disk.
	int						maxMemorySize = 1024;	//1MB Default		
	//Sets the directory used to temporarily store files that are larger than the configured size threshold.
	String					tempDirectory;
	int						maxFileUploadSize = 1024;	//1MB Default
	List<Map<String, Object>>		uploadedFiles = new ArrayList<Map< String, Object >>();
	public static final String UPD_FIELD_NAME = "FIELD_NAME";
	public static final String UPD_FILE_NAME = "FILE_NAME";
	public static final String UPD_CONTENT_TYPE = "CONTENT_TYPE";
	public static final String UPD_IS_IN_MEM = "IS_IN_MEM";
	public static final String UPD_SIZE_IN_BYTES = "SIZE_IN_BYTES";
	public static final String UPD_FILE_EXT	= "fileExt";
	public static final String UPD_FILE = "FILE";
	int fileUploadCount;
	//Set the request size
	int maxRequestSize = 1024;
	
	public void setMaxRequestSize( int maxRequestSize )
	{
		this.maxRequestSize = maxRequestSize;
	}

	public List<Map<String, Object>> getUploadedFiles()
	{
		return uploadedFiles;
	}

	public void setMaxMemorySize( int maxMemorySize )
	{
		this.maxMemorySize = maxMemorySize;
	}

	public void setTempDirectory( String tempDirectory )
	{
		this.tempDirectory = tempDirectory;
	}

	public void setMaxFileUploadSize( int maxFileUploadSize )
	{
		this.maxFileUploadSize = maxFileUploadSize;
	}

	private boolean isFileUploadRequest( HttpServletRequest request )
	{
		return ServletFileUpload.isMultipartContent( request );
	}

	public Map< String, String > getParam()
	{
		return param;
	}

	public void doFileUpload( HttpServletRequest request )
			throws JumbalakkaFileUploadException
	{
		if ( isFileUploadRequest( request ) == false )
		{
			throw new JumbalakkaFileUploadException(
					"Request does not support multipart content" );
		}

		// Create a factory for disk-based file items
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Set factory constraints
		factory.setSizeThreshold( maxMemorySize );

		File tempDir = new File( tempDirectory );
		if ( tempDir.exists() == false )
		{
			throw new JumbalakkaFileUploadException(
					"The temp directory does not exists" + tempDirectory );
		}
		else if ( tempDir.isDirectory() == false )
		{
			throw new JumbalakkaFileUploadException(
					"The temp directory specified is not a directory"
							+ tempDirectory );
		}
		factory.setRepository( tempDir );

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload( factory );

		// Set overall request size constraint
		upload.setSizeMax( maxRequestSize );
		// Sets the maximum file size
		upload.setFileSizeMax( maxFileUploadSize );
		List /* FileItem */items;
		// Parse the request
		try
		{
			items = upload.parseRequest( request );
		}
		catch ( FileUploadException e )
		{
			logger.error( e );
			throw new JumbalakkaFileUploadException( e.getMessage() );
		}
		// Process the uploaded items
		Iterator iter = items.iterator();
		FileItem item;
		File storeFile;
		while ( iter.hasNext() )
		{
			item = (FileItem) iter.next();

			if ( item.isFormField() )
			{
				param.put( item.getName(), item.getString() );
			}
			else
			{
				Map<String, Object> uploadedFile = new HashMap< String, Object >();
				uploadedFile.put( UPD_FIELD_NAME, item.getFieldName() );
				uploadedFile.put( UPD_FILE_NAME, item.getName() );
				uploadedFile.put( UPD_FILE_EXT, StringUtils.substringAfterLast( item.getName(), "." ) );
				uploadedFile.put( UPD_CONTENT_TYPE, item.getContentType() );
				uploadedFile.put( UPD_IS_IN_MEM, item.isInMemory() );
				uploadedFile.put( UPD_SIZE_IN_BYTES, item.getSize() );
				
				storeFile = new File( tempDirectory, 
						( ++fileUploadCount ) + "_" + request.getSession().getId() + "_" + 
						getUniqueStringLiteral() );
				try
				{
					item.write( storeFile );
				}
				catch ( Exception e )
				{
					logger.error( e );
					throw new JumbalakkaFileUploadException( e.getMessage() );
				}
				uploadedFile.put( UPD_FILE, storeFile );
				
				uploadedFiles.add( uploadedFile );
			}
		}
	}
	
	private String getUniqueStringLiteral()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyMMddHHmmss" );
		return dateFormat.format( new Date() );
	}
}
