package com.jumbalakka.commons.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.jumbalakka.commons.RecordStatus;
import com.jumbalakka.commons.security.dao.JumbalakkaSecurityDAO;
import com.jumbalakka.commons.security.types.JumbalakkaUser;
import com.jumbalakka.commons.spring.SpringContextUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={ "/context.xml", "/hibernate.cfg.xml" })
public class LiveSecurityTest
{
	@Test
	public void testA()
	{
		JumbalakkaSecurityDAO jumbalakkaSecurityDAO =
				SpringContextUtils.getBean( "jumbalakkaSecurityDAO", JumbalakkaSecurityDAO.class );
		JumbalakkaUser user = new JumbalakkaUser();
		user.setUserId( "amit" );
		user.setEmail( "amit.bhavadas@gmail.com" );
		user.setStatus( RecordStatus.ENABLED.toString() );
		user.setPassword( JumbalakkaSecurityUtils.encryptPassword( "amit" ) );
		jumbalakkaSecurityDAO.addUser( user );
	}
}
