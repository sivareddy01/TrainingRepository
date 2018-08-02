/**
 *
 */
package com.training.hybris.facades.process.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.commerceservices.model.process.StoreFrontCustomerProcessModel;

import com.training.hybris.core.model.TrainingCustomerUpdateProfileProcessModel;


/**
 * @author Mrk.Chaitanya
 *
 */
public class TrainingUpdateProfileEmailContext extends CustomerEmailContext
{

	private String oldMobileNumber;

	public String getOldMobileNumber()
	{
		return oldMobileNumber;
	}


	/**
	 * @param oldMobileNumber
	 *           the oldMobileNumber to set
	 */
	public void setOldMobileNumber(final String oldMobileNumber)
	{
		this.oldMobileNumber = oldMobileNumber;
	}


	@Override
	public void init(final StoreFrontCustomerProcessModel storeFrontCustomerProcessModel, final EmailPageModel emailPageModel)
	{

		super.init(storeFrontCustomerProcessModel, emailPageModel);
		if (storeFrontCustomerProcessModel instanceof TrainingCustomerUpdateProfileProcessModel)
		{
			final TrainingCustomerUpdateProfileProcessModel customerProcessModel = (TrainingCustomerUpdateProfileProcessModel) storeFrontCustomerProcessModel;
			oldMobileNumber = customerProcessModel.getOldMobileNumber();
		}
	}


}
