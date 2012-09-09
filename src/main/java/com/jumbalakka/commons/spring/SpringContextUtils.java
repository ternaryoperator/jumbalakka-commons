package com.jumbalakka.commons.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtils implements ApplicationContextAware
{
	private static ApplicationContext	ctx;

	public void setApplicationContext( ApplicationContext applicationContext )
			throws BeansException
	{
		SpringContextUtils.ctx = applicationContext;
	}

	public static < T > T getBean( String beanId, Class< T > clazz )
	{
		return ctx.getBean( beanId, clazz );
	}
}
