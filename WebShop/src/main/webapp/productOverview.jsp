<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param value="Products" name="title" />
</jsp:include>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param value="Products" name="title" />
		</jsp:include>

		<main> <c:choose>
			<c:when test="${products.size()==0 }">
				<p>There are no products in the list.</p>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<th>Name</th>
						<th>Description</th>
						<th>Price</th>
						<th>Delete</th>
					</tr>
					<c:forEach var="product" items="${products }">
						<tr>
							<td><a
								href="Controller?action=updateProduct&productId=${product.productId}">${product.name}</a></td>
							<td>${product.description}</td>
							<td>${product.price}</td>
							<td><a
								href="Controller?action=deleteProduct&productId=${product.productId}">Delete</a>
						</tr>
					</c:forEach>
					<caption>Products</caption>
				</table>
			</c:otherwise>
		</c:choose> </main>
		<footer> &copy; Webontwikkeling 3, UC Leuven-Limburg </footer>
	</div>
</body>
</html>