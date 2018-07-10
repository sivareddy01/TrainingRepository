/**
 *
 */
package com.training.hybris.facades.populators;

import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;


/**
 * @author Raja
 *
 */
public class TrainingCustomerPopulator implements Populator<CustomerModel, CustomerData>
{

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final CustomerModel source, final CustomerData target) throws ConversionException
	{
		// YTODO Auto-generated method stub
		target.setMobileNumber(source.getMobileNumber());

	}

}
