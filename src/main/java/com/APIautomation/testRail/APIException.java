/**
 * TestRail API binding for Java (API v2, available since TestRail 3.0)
 *
 * Learn more:
 *
 * http://docs.gurock.com/testrail-api2/start
 * http://docs.gurock.com/testrail-api2/accessing
 *
 * Copyright Gurock Software GmbH. See license.md for details.
 */

package com.APIautomation.testRail;
 
public class APIException extends Exception
{
	/**
	 * serialize ID
	 */
	private static final long serialVersionUID = -6575048337177779145L;

	public APIException(String message)
	{
		super(message);
	}
}
