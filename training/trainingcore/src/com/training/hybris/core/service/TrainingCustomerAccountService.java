/**
 *
 */
package com.training.hybris.core.service;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.customer.impl.DefaultCustomerAccountService;
import de.hybris.platform.core.model.user.CustomerModel;

import org.apache.commons.lang.StringUtils;

import com.training.hybris.core.event.UpdateProfileEvent;


public class TrainingCustomerAccountService extends DefaultCustomerAccountService
{
	@Override
	public void updateProfile(final CustomerModel customerModel, final String titleCode, final String name, final String login)
			throws DuplicateUidException
	{
		validateParameterNotNullStandardMessage("customerModel", customerModel);

		customerModel.setUid(login);
		customerModel.setName(name);
		if (StringUtils.isNotBlank(titleCode))
		{
			customerModel.setTitle(getUserService().getTitleForCode(titleCode));
		}
		else
		{
			customerModel.setTitle(null);
		}

		internalSaveCustomer(customerModel);
		final UpdateProfileEvent event = new UpdateProfileEvent();

		event.setOldMobileNumber(customerModel.getMobileNumber());
		getEventService().publishEvent(initializeEvent(event, customerModel));

	}
}
