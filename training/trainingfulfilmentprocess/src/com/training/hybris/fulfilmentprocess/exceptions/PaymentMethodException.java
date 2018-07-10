/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.training.hybris.fulfilmentprocess.exceptions;

/**
 * 
 */
public class PaymentMethodException extends RuntimeException
{

	/**
	 * 
	 * @param message
	 */
	public PaymentMethodException(final String message)
	{
		super(message);
	}

}
