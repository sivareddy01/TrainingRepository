/**
 *
 */
package com.training.hybris.facades.customer;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.RegisterData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.store.services.BaseStoreService;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.training.hybris.core.event.UpdateProfileEvent;


/**
 * @author Raja
 *
 */
public class TrainingCustomerFacade extends DefaultCustomerFacade
{
	private BaseStoreService baseStoreService;
	private BaseSiteService baseSiteService;
	private EventService eventService;



	/**
	 * @return the baseStoreService
	 */
	public BaseStoreService getBaseStoreService()
	{
		return baseStoreService;
	}

	/**
	 * @param baseStoreService
	 *           the baseStoreService to set
	 */
	public void setBaseStoreService(final BaseStoreService baseStoreService)
	{
		this.baseStoreService = baseStoreService;
	}

	/**
	 * @return the baseSiteService
	 */
	public BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	/**
	 * @param baseSiteService
	 *           the baseSiteService to set
	 */
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	/**
	 * @return the eventService
	 */
	public EventService getEventService()
	{
		return eventService;
	}

	/**
	 * @param eventService
	 *           the eventService to set
	 */
	public void setEventService(final EventService eventService)
	{
		this.eventService = eventService;
	}

	@Override
	public void register(final RegisterData registerData) throws DuplicateUidException
	{
		validateParameterNotNullStandardMessage("registerData", registerData);
		Assert.hasText(registerData.getFirstName(), "The field [FirstName] cannot be empty");
		Assert.hasText(registerData.getLastName(), "The field [LastName] cannot be empty");
		Assert.hasText(registerData.getLogin(), "The field [Login] cannot be empty");

		final CustomerModel newCustomer = getModelService().create(CustomerModel.class);
		newCustomer.setName(getCustomerNameStrategy().getName(registerData.getFirstName(), registerData.getLastName()));

		if (StringUtils.isNotBlank(registerData.getFirstName()) && StringUtils.isNotBlank(registerData.getLastName()))
		{
			newCustomer.setName(getCustomerNameStrategy().getName(registerData.getFirstName(), registerData.getLastName()));
		}
		final TitleModel title = getUserService().getTitleForCode(registerData.getTitleCode());
		newCustomer.setTitle(title);
		newCustomer.setMobileNumber(registerData.getMobileNumber());
		setUidForRegister(registerData, newCustomer);
		newCustomer.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
		newCustomer.setSessionCurrency(getCommonI18NService().getCurrentCurrency());
		getCustomerAccountService().register(newCustomer, registerData.getPassword());
	}

	@Override
	public void updateProfile(final CustomerData customerData) throws DuplicateUidException
	{
		validateDataBeforeUpdate(customerData);

		final String name = getCustomerNameStrategy().getName(customerData.getFirstName(), customerData.getLastName());
		final CustomerModel customer = getCurrentSessionCustomer();
		customer.setOriginalUid(customerData.getDisplayUid());
		customer.setMobileNumber(customerData.getMobileNumber());
		getCustomerAccountService().updateProfile(customer, customerData.getTitleCode(), name, customerData.getUid());
	}

	@Override
	public void updateFullProfile(final CustomerData customerData) throws DuplicateUidException
	{
		validateParameterNotNullStandardMessage("customerData", customerData);
		Assert.hasText(customerData.getTitleCode(), "The field [TitleCode] cannot be empty");
		Assert.hasText(customerData.getFirstName(), "The field [FirstName] cannot be empty");
		Assert.hasText(customerData.getLastName(), "The field [LastName] cannot be empty");
		Assert.hasText(customerData.getUid(), "The field [Uid] cannot be empty");

		final CustomerModel customer = getCurrentSessionCustomer();
		final String oldMobileNumber = customer.getMobileNumber();

		getCustomerReversePopulator().populate(customerData, customer);

		if (customer.getDefaultPaymentAddress() != null)
		{
			getModelService().save(customer.getDefaultPaymentAddress());
		}

		if (customer.getDefaultShipmentAddress() != null)
		{
			getModelService().save(customer.getDefaultShipmentAddress());
		}

		getModelService().save(customer);

		final UpdateProfileEvent event = new UpdateProfileEvent();
		event.setOldMobileNumber(oldMobileNumber);
		getEventService().publishEvent(initializeEvent(event, customer));
	}

	@Override
	protected void validateDataBeforeUpdate(final CustomerData customerData)
	{
		validateParameterNotNullStandardMessage("customerData", customerData);
		Assert.hasText(customerData.getTitleCode(), "The field [TitleCode] cannot be empty");
		Assert.hasText(customerData.getFirstName(), "The field [FirstName] cannot be empty");
		Assert.hasText(customerData.getLastName(), "The field [LastName] cannot be empty");
		Assert.hasText(customerData.getMobileNumber(), "The field [MobileNumber] cannot be empty");
		Assert.hasText(customerData.getUid(), "The field [Uid] cannot be empty");
	}

	protected AbstractCommerceUserEvent initializeEvent(final AbstractCommerceUserEvent event, final CustomerModel customerModel)
	{
		event.setBaseStore(getBaseStoreService().getCurrentBaseStore());
		event.setSite(getBaseSiteService().getCurrentBaseSite());
		event.setCustomer(customerModel);
		event.setLanguage(getCommonI18NService().getCurrentLanguage());
		event.setCurrency(getCommonI18NService().getCurrentCurrency());
		return event;
	}

}
