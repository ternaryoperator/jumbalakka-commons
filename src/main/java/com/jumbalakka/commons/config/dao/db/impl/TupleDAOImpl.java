package com.jumbalakka.commons.config.dao.db.impl;

import java.util.Date;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.jumbalakka.commons.config.dao.TupleDAO;
import com.jumbalakka.commons.config.type.Tuple;

public class TupleDAOImpl extends HibernateDaoSupport implements TupleDAO
{
	public void addTuple( String type, String key, String value )
	{
		Tuple t = new Tuple( type, key, value );
		t.setCreateDate( new Date() );
		getHibernateTemplate().save( t );
	}
	
	public void updateTuple( Tuple e )
	{
		getHibernateTemplate().update( e );
	}
	
	public List< Tuple > getByType( String type )
	{
		DetachedCriteria criteria = DetachedCriteria.forClass( Tuple.class );
		criteria.add( Restrictions.eq( "type", type ) );
		return getHibernateTemplate().findByCriteria( criteria );
	}
	
	public List<Tuple> getByFilter( String type, String key )
	{
		DetachedCriteria criteria = DetachedCriteria.forClass( Tuple.class );
		criteria.add( Restrictions.eq( "type", type ) );
		criteria.add( Restrictions.eq( "key", key ) );
		return getHibernateTemplate().findByCriteria( criteria );
	}
	
	public void delete( Tuple t )
	{
		getHibernateTemplate().delete( t );
	}

	public List<Tuple> getAll()
	{
		DetachedCriteria criteria = DetachedCriteria.forClass( Tuple.class );
		return getHibernateTemplate().findByCriteria( criteria );
	}

	public Tuple getById( int id )
	{
		DetachedCriteria criteria = DetachedCriteria.forClass( Tuple.class );
		criteria.add( Restrictions.eq( "id", id ) );
		List<Tuple> tuples = getHibernateTemplate().findByCriteria( criteria );
		if( tuples.isEmpty() )
		{
			return null;
		}
		return tuples.get( 0 );
	}
}
