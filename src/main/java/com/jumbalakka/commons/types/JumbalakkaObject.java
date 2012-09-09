package com.jumbalakka.commons.types;

import java.io.Serializable;

public class JumbalakkaObject implements Serializable, Cloneable
{
	private String	jumbType;

	public String getJumbType()
	{
		return jumbType;
	}

	public void setJumbType( String jumbType )
	{
		this.jumbType = jumbType;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException
	{
		return super.clone();
	}
}
