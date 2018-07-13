/**
 *
 */
package com.training.hybris.storefront.controllers.cms;

import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.core.model.product.ProductModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.training.core.model.components.TrainingProductListComponentModel;
import com.training.hybris.storefront.controllers.ControllerConstants;




/**
 * @author Raja
 *
 */
@Controller("TrainingProductListComponentController")
@RequestMapping(value = ControllerConstants.Actions.Cms.TrainingProductListComponent)
public class TrainingProductListComponentController
		extends AbstractAcceleratorCMSComponentController<TrainingProductListComponentModel>
{


	@Resource(name = "accProductFacade")
	private ProductFacade productFacade;

	protected static final List<ProductOption> PRODUCT_OPTIONS = Arrays.asList(ProductOption.PRICE);

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.acceleratorstorefrontcommons.controllers.cms.AbstractCMSComponentController#fillModel(javax.
	 * servlet.http.HttpServletRequest, org.springframework.ui.Model,
	 * de.hybris.platform.cms2.model.contents.components.AbstractCMSComponentModel)
	 */
	@Override
	protected void fillModel(final HttpServletRequest request, final Model model,
			final TrainingProductListComponentModel component)
	{
		final Collection<ProductModel> products = component.getProducts();
		final Collection<ProductData> data = new ArrayList<ProductData>();
		ProductData productData = null;
		for (final ProductModel productModel : products)
		{
			productData = productFacade.getProductForOptions(productModel, PRODUCT_OPTIONS);
			data.add(productData);

		}
		model.addAttribute("product", data);

		//siva committed code
	}

}
