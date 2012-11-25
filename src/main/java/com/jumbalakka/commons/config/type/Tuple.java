package com.jumbalakka.commons.config.type;

import java.util.Date;
import com.jumbalakka.commons.types.JumbalakkaObject;

public class Tuple extends JumbalakkaObject
{
	Integer id;
	String key;
	String type;
	String value;
	Date createDate;
	
	public Tuple(){}
	public Tuple( String type, String key, String value )
	{
		this.type = type;
		this.key = key;
		this.value = value;
	}
	public String getType()
	{
		return type;
	}
	public void setType( String type )
	{
		this.type = type;
	}
	public Date getCreateDate()
	{
		return createDate;
	}
	public void setCreateDate( Date createDate )
	{
		this.createDate = createDate;
	}
	public Integer getId()
	{
		return id;
	}
	public void setId( Integer id )
	{
		this.id = id;
	}
	public String getKey()
	{
		return key;
	}
	public void setKey( String key )
	{
		this.key = key;
	}
	public String getValue()
	{
		return value;
	}
	public void setValue( String value )
	{
		this.value = value;
	}
}
