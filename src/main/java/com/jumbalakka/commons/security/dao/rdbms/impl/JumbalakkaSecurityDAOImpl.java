package com.jumbalakka.commons.security.dao.rdbms.impl;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.jumbalakka.commons.security.dao.JumbalakkaSecurityDAO;
import com.jumbalakka.commons.security.types.JumbalakkaUser;

public class JumbalakkaSecurityDAOImpl extends HibernateDaoSupport implements JumbalakkaSecurityDAO
{

	public JumbalakkaUser getUser( String userId )
	{
		DetachedCriteria criteria = DetachedCriteria.forClass( JumbalakkaUser.class );
		criteria.add( Restrictions.eq( "userId", userId ) );
		List< JumbalakkaUser > users = getHibernateTemplate().findByCriteria( criteria );
		if( users.isEmpty() )
		{
			return null;
		}
		return users.get( 0 );
	}
	
	public void addUser( JumbalakkaUser user )
	{
		getHibernateTemplate().save( user );
	}
	
	public void updateUser( JumbalakkaUser user )
	{
		getHibernateTemplate().update( user );
	}

	public void deleteUser( JumbalakkaUser user )
	{
		getHibernateTemplate().delete( user );
	}
}
