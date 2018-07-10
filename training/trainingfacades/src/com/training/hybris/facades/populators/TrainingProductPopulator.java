/**
 *
 */
package com.training.hybris.facades.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import javax.annotation.Resource;

import com.training.hybris.core.model.MyProductModel;


/**
 * @author Raja
 *
 */
public class TrainingProductPopulator implements Populator<ProductModel, ProductData>
{

	@Resource(name = "enumerationService")
	private EnumerationService enumerationService;

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final ProductModel source, final ProductData target) throws ConversionException
	{
		if (null != source.getDeliveryType())
		{
			final String enumerationName = enumerationService.getEnumerationName(source.getDeliveryType());
			target.setDeliveryType(enumerationName);
		}

		//if source is instance of product model then populate test
		if (source instanceof MyProductModel)
		{
			final String test = ((MyProductModel) source).getTest();
			target.setTest(test);
		}
	}

}
