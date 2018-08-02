/**
 *
 */
package com.training.hybris.core.event;

import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.event.ClusterAwareEvent;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;

import com.training.hybris.core.model.TrainingCustomerUpdateProfileProcessModel;


/**
 * @author Mrk.Chaitanya
 *
 */
public class UpdateProfileEventListener extends AbstractAcceleratorSiteEventListener<UpdateProfileEvent>
		implements ClusterAwareEvent
{

	private static final Logger LOG = Logger.getLogger(UpdateProfileEventListener.class);

	private ModelService modelService;
	private BusinessProcessService businessProcessService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.commerceservices.event.AbstractSiteEventListener#onSiteEvent(de.hybris.platform.servicelayer.
	 * event.events.AbstractEvent)
	 */
	@Override
	protected void onSiteEvent(final UpdateProfileEvent event)
	{
		LOG.info("Sendng update profile email");
		// YTODO Auto-generated method stub
		final TrainingCustomerUpdateProfileProcessModel storeFrontCustomerProcessModel = (TrainingCustomerUpdateProfileProcessModel) getBusinessProcessService()
				.createProcess("customerUpdateProfileEmailProcess-" + "" + "-" + System.currentTimeMillis(),
						"customerUpdateProfileEmailProcess");
		storeFrontCustomerProcessModel.setSite(event.getSite());
		storeFrontCustomerProcessModel.setCustomer(event.getCustomer());
		storeFrontCustomerProcessModel.setLanguage(event.getLanguage());
		storeFrontCustomerProcessModel.setCurrency(event.getCurrency());
		storeFrontCustomerProcessModel.setStore(event.getBaseStore());
		storeFrontCustomerProcessModel.setOldMobileNumber(event.getOldMobileNumber());
		getModelService().save(storeFrontCustomerProcessModel);
		getBusinessProcessService().startProcess(storeFrontCustomerProcessModel);
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener#getSiteChannelForEvent(de.hybris.
	 * platform.servicelayer.event.events.AbstractEvent)
	 */
	@Override
	protected SiteChannel getSiteChannelForEvent(final UpdateProfileEvent event)
	{
		final BaseSiteModel site = event.getSite();
		ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
		return site.getChannel();
	}


	protected BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	@Required
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

	/**
	 * @return the modelService
	 */
	protected ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}



	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.servicelayer.event.ClusterAwareEvent#publish(int, int)
	 */
	@Override
	public boolean publish(final int arg0, final int arg1)
	{
		// YTODO Auto-generated method stub
		return arg0 == arg1 || true;
	}

}
