package com.jumbalakka.commons.config.dao;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import com.jumbalakka.commons.config.type.Tuple;

public interface TupleDAO
{
	public void addTuple( String type, String key, String value );
	public void updateTuple( Tuple e );
	public List< Tuple > getByType( String type );
	public List<Tuple> getByFilter( String type, String key );
	public void delete( Tuple t );
	public List<Tuple> getAll();
	public Tuple getById( int id );
}