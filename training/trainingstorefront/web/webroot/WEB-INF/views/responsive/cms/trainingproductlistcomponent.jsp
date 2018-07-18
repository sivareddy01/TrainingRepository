<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>


<c:forEach items="${product}" var="product">
Name: ${product.name}<br>

	<a href="${product.url}"> <img title="${headline}"
		alt="${product.picture.altText}" src="${product.picture.url}"></br>
	</a>

Price: ${product.price.formattedValue}</br>

</c:forEach>

