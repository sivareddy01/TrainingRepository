<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/responsive/common"%>

<cms:pageSlot position="HomePageTopPromoSection" var="feature">
	<cms:component component="${feature}" element="div"
		class="span-24 section1 cms_disp-img_slot" />
</cms:pageSlot>
<template:page pageTitle="${pageTitle}">

	

	<cms:pageSlot position="HomePageHeaderBannerSection" var="feature">
		<cms:component component="${feature}" element="div"
			class="span-24 section1 cms_disp-img_slot" />
	</cms:pageSlot>
	<cms:pageSlot position="AboutUsSection" var="feature">
		<cms:component component="${feature}" element="div"
			class="span-24 section1 cms_disp-img_slot" />
	</cms:pageSlot>

</template:page>