<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/responsive/template"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
<%@ taglib prefix="register"
	tagdir="/WEB-INF/tags/responsive/user"%>
<%@ taglib prefix="login"
	tagdir="/WEB-INF/tags/responsive/user"%>
	

<template:page pageTitle="${pageTitle}">
	<div class="row">
		<div class="col-md-6">
		<%-- <register:register action="/register" actionNameKey="register.register"/> --%>
			<cms:pageSlot position="LeftContentSlot" var="feature" element="div" class="login-left-content-slot">
				
				<cms:component component="${feature}"  element="div" class="login-left-content-component"/>
				
			</cms:pageSlot>
		</div>
		<div class="col-md-6">
		<%-- <login:login action="/login" actionNameKey="login"/> --%>
			<cms:pageSlot position="RightContentSlot" var="feature" element="div" class="login-right-content-slot">
				<cms:component component="${feature}"  element="div" class="login-right-content-component"/>
			</cms:pageSlot>
		</div>
	</div>
</template:page>