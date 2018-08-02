/**
 *
 */
package com.training.hybris.core.event;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.event.AbstractCommerceUserEvent;


/**
 * @author Mrk.Chaitanya
 *
 */
public class UpdateProfileEvent extends AbstractCommerceUserEvent<BaseSiteModel>
{
	// Empty class
	String oldMobileNumber;

	/**
	 * @return the oldMobileNumber
	 */
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


}
