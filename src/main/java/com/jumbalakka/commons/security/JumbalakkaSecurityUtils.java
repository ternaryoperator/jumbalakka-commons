package com.jumbalakka.commons.security;

import org.jasypt.util.password.StrongPasswordEncryptor;

public class JumbalakkaSecurityUtils
{
	private static StrongPasswordEncryptor passwordEncryptor = null;
	
	static{
		passwordEncryptor = new StrongPasswordEncryptor();
	}
	
	/**
	 * Used to save the password
	 * @param passwordPlainText
	 * @return
	 */
	public static String encryptPassword( String passwordPlainText )
	{
		return passwordEncryptor.encryptPassword( passwordPlainText );
	}
	
	/**
	 * Used to check if plaintext user entered password
	 * same as saved encrypted password
	 * @param plainTextPassword
	 * @param encryptedPassword
	 * @return
	 */
	public static boolean validPassword( String plainTextPassword, String encryptedPassword )
	{
		if( passwordEncryptor.checkPassword( plainTextPassword, encryptedPassword ) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
