<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>
hiii<br>
<c:forEach items="${component.products}" var="product">
${product.name}<br>
${product.picture}<br>
	<c:forEach items="${product.europe1Prices}" var="price">
		${price.price}
	</c:forEach>
</c:forEach>