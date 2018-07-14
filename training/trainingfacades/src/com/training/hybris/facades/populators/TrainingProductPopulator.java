/**
 *
 */
package com.training.hybris.facades.populators;

import de.hybris.platform.commercefacades.product.data.ImageData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

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

	@Resource(name = "imagePopulator")
	private Populator imagePopulator;

	@Resource(name = "imageConverter")
	private Converter<MediaModel, ImageData> imageConverter;

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

		if (null != source.getPicture())
		{
			final MediaModel pictureModel = source.getPicture();
			//			final ImageData imagedata = new ImageData();
			//			imagePopulator.populate(pictureModel, imagedata);
			//			target.setPicture(imagedata);

			final ImageData imagedata = imageConverter.convert(pictureModel);
			target.setPicture(imagedata);
		}

	}

}
