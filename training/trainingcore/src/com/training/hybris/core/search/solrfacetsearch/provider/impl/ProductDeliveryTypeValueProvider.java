/**
 *
 */
package com.training.hybris.core.search.solrfacetsearch.provider.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.enumeration.EnumerationService;
import de.hybris.platform.solrfacetsearch.config.IndexConfig;
import de.hybris.platform.solrfacetsearch.config.IndexedProperty;
import de.hybris.platform.solrfacetsearch.config.exceptions.FieldValueProviderException;
import de.hybris.platform.solrfacetsearch.provider.FieldNameProvider;
import de.hybris.platform.solrfacetsearch.provider.FieldValue;
import de.hybris.platform.solrfacetsearch.provider.FieldValueProvider;
import de.hybris.platform.solrfacetsearch.provider.impl.AbstractPropertyFieldValueProvider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Required;

import com.training.hybris.core.enums.DeliveryTypeEnum;




/**
 * @author Raja
 *
 */
public class ProductDeliveryTypeValueProvider extends AbstractPropertyFieldValueProvider
		implements FieldValueProvider, Serializable
{
	private FieldNameProvider fieldNameProvider;

	@Resource(name = "enumerationService")
	private EnumerationService enumerationService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.solrfacetsearch.provider.FieldValueProvider#getFieldValues(de.hybris.platform.solrfacetsearch.
	 * config.IndexConfig, de.hybris.platform.solrfacetsearch.config.IndexedProperty, java.lang.Object)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public Collection<FieldValue> getFieldValues(final IndexConfig var1, final IndexedProperty indexedProperty, final Object model)
			throws FieldValueProviderException
	{
		// YTODO Auto-generated method stub
		ProductModel product = null;
		if (model instanceof ProductModel)
		{
			product = (ProductModel) model;
			DeliveryTypeEnum deliveryType = product.getDeliveryType();
			//final String enumerationName = enumerationService.getEnumerationName(productModel.getDeliveryType());
			if (deliveryType == null)
			{
				deliveryType = enumerationService.getEnumerationValue(DeliveryTypeEnum.class, DeliveryTypeEnum.STANDARD.getCode());
			}
			final Collection<FieldValue> fieldValues = new ArrayList<FieldValue>();
			fieldValues.addAll(createFieldValue(deliveryType, indexedProperty));
			return fieldValues;
		}
		return Collections.EMPTY_LIST;
	}

	protected List<FieldValue> createFieldValue(final DeliveryTypeEnum deliveryType, final IndexedProperty indexedProperty)
	{
		final List<FieldValue> fieldValues = new ArrayList<FieldValue>();
		final Object value = enumerationService.getEnumerationName(deliveryType);
		final Collection<String> fieldNames = fieldNameProvider.getFieldNames(indexedProperty, null);
		for (final String fieldName : fieldNames)
		{
			fieldValues.add(new FieldValue(fieldName, value));
		}
		return fieldValues;

	}

	@Required
	public void setFieldNameProvider(final FieldNameProvider fieldNameProvider)
	{
		this.fieldNameProvider = fieldNameProvider;
	}

}
