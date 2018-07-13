package com.training.hybris.core.jalo;

import de.hybris.platform.cms2lib.components.GeneratedProductListComponent;
import de.hybris.platform.jalo.Item;
import de.hybris.platform.jalo.JaloBusinessException;
import de.hybris.platform.jalo.SessionContext;
import de.hybris.platform.jalo.type.ComposedType;

import java.util.List;

import org.apache.log4j.Logger;


public class TrainingProductListComponent extends GeneratedProductListComponent
{
	@SuppressWarnings("unused")
	private final static Logger LOG = Logger.getLogger(TrainingProductListComponent.class.getName());

	@Override
	protected Item createItem(final SessionContext ctx, final ComposedType type, final ItemAttributeMap allAttributes)
			throws JaloBusinessException
	{
		// business code placed here will be executed before the item is created
		// then create the item
		final Item item = super.createItem(ctx, type, allAttributes);
		// business code placed here will be executed after the item was created
		// and return the item
		return item;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.cms2lib.components.GeneratedProductListComponent#getCategoryCode(de.hybris.platform.jalo.
	 * SessionContext)
	 */
	@Override
	public String getCategoryCode(final SessionContext arg0)
	{
		// YTODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.cms2lib.components.GeneratedProductListComponent#getProductCodes(de.hybris.platform.jalo.
	 * SessionContext)
	 */
	@Override
	public List<String> getProductCodes(final SessionContext arg0)
	{
		// YTODO Auto-generated method stub
		return null;
	}

}
