package com.jumbalakka.commons.web.tag.ui;

import javax.servlet.jsp.JspException;
import org.apache.commons.lang.StringUtils;
import com.jumbalakka.commons.web.JumbalakkaAbstractTag;

public class JumbalakkaUserFeedbackTag extends JumbalakkaAbstractTag
{

	@Override
	protected int doJumbEndTag() throws JspException
	{
		return 0;
	}

	@Override
	protected int doJumbStartTag() throws JspException
	{
		String errorMsg = getRequestAttribute( REQ_ERROR_MSG, String.class );
		String infoMsg = getRequestAttribute( REQ_INFO_MSG, String.class );
		
		if( StringUtils.isNotBlank( errorMsg ) )
		{
			write( errorMsg, REQ_ERROR_MSG );
		}
		else if( StringUtils.isNotBlank( infoMsg ) )
		{
			write( infoMsg, REQ_INFO_MSG );
		}
		return 0;
	}

	protected void write( String msgToWrite, String type )
	{
		super.write( 
				"<span class='jmb" + type + "'>" + 
				msgToWrite +
				"</span>" );
	}

	@Override
	protected void jumbRelease()
	{
		// TODO Auto-generated method stub
		
	}

}
