package com.jumbalakka.commons.file.types;

import java.util.Date;
import com.jumbalakka.commons.types.JumbalakkaObject;

public class JumbalakkaFile extends JumbalakkaObject
{
	Integer		id;
	String	orignalFileName;
	String	newFileName;
	String 	newFileAbsPath;
	Date	fileUpload;
	String	comment;
	String	domain;
	String	status;
	String	uploadedBy;
	String	securityType;
	String  security;
	String 	catalog;
	String 	fileStatus;
	Double 	size;
	
	public Double getSize()
	{
		return size;
	}

	public void setSize( Double size )
	{
		this.size = size;
	}

	public String getNewFileAbsPath()
	{
		return newFileAbsPath;
	}

	public void setNewFileAbsPath( String newFileAbsPath )
	{
		this.newFileAbsPath = newFileAbsPath;
	}

	public String getSecurityType()
	{
		return securityType;
	}

	public void setSecurityType( String securityType )
	{
		this.securityType = securityType;
	}

	public String getFileStatus()
	{
		return fileStatus;
	}

	public void setFileStatus( String fileStatus )
	{
		this.fileStatus = fileStatus;
	}

	public String getCatalog()
	{
		return catalog;
	}

	public void setCatalog( String catalog )
	{
		this.catalog = catalog;
	}

	public String getSecurity()
	{
		return security;
	}

	public void setSecurity( String security )
	{
		this.security = security;
	}

	public String getUploadedBy()
	{
		return uploadedBy;
	}

	public void setUploadedBy( String uploadedBy )
	{
		this.uploadedBy = uploadedBy;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId( Integer id )
	{
		this.id = id;
	}

	public String getOrignalFileName()
	{
		return orignalFileName;
	}

	public void setOrignalFileName( String orignalFileName )
	{
		this.orignalFileName = orignalFileName;
	}

	public String getNewFileName()
	{
		return newFileName;
	}

	public void setNewFileName( String newFileName )
	{
		this.newFileName = newFileName;
	}

	public Date getFileUpload()
	{
		return fileUpload;
	}

	public void setFileUpload( Date fileUpload )
	{
		this.fileUpload = fileUpload;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment( String comment )
	{
		this.comment = comment;
	}

	public String getDomain()
	{
		return domain;
	}

	public void setDomain( String domain )
	{
		this.domain = domain;
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
