package com.jumbalakka.commons.file.dao.rdbms.impl;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.jumbalakka.commons.file.dao.JumbalakkaFileDAO;
import com.jumbalakka.commons.file.types.JumbalakkaFile;

public class JumbalakkaFileDAOImpl extends HibernateDaoSupport implements JumbalakkaFileDAO
{

	public JumbalakkaFile getJumballakaFile( int id, String domain,
			String catalog )
	{
		DetachedCriteria criteria = DetachedCriteria.forClass( JumbalakkaFile.class );
		criteria.add( Restrictions.eq( "id", id ) );
		criteria.add( Restrictions.eq( "domain", domain ) );
		criteria.add( Restrictions.eq( "catalog", catalog ) );
		List< JumbalakkaFile > files = getHibernateTemplate().findByCriteria( criteria );
		if( files.isEmpty() )
		{
			return null;
		}
		return files.get( 0 );
	}

	public void addJumballakaFile( JumbalakkaFile file )
	{
		getHibernateTemplate().save( file );
	}

	public void updateJumballakaFile( JumbalakkaFile file )
	{
		getHibernateTemplate().update( file );
	}

	public void deleteJumballakaFile( JumbalakkaFile file )
	{
		getHibernateTemplate().delete( file );
	}

}
