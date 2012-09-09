package com.jumbalakka.commons.web.tag;

import javax.servlet.jsp.JspException;
import com.jumbalakka.commons.security.types.JumbalakkaUser;
import com.jumbalakka.commons.web.JumbalakkaAbstractTag;

public class JumbalakkaCheckUserSession extends JumbalakkaAbstractTag
{

	@Override
	protected int doJumbEndTag() throws JspException
	{
		return 0;
	}

	@Override
	protected int doJumbStartTag() throws JspException
	{
		JumbalakkaUser user = getSessionAttribute( USER_SESSION, JumbalakkaUser.class );
		if( user == null )
		{
			write( "You are not authorized to view the page" );
			return SKIP_BODY;
		}
		else
		{
			setRequestAttribute( USER_SESSION, user );
			setRequestAttribute( USES_SESSION_HAS_BEEN_CHECKED, true );
			return EVAL_BODY_INCLUDE;
		}
	}

	@Override
	protected void jumbRelease()
	{
		// TODO Auto-generated method stub
		
	}

}
