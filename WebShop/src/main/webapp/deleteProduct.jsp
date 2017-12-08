<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param value="Delete Product Confirmation" name="title" />
</jsp:include>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Delete Product Confirmation" />
		</jsp:include>
		<main>
		<p>Are you sure you want to delete this product:</p>
		<ul>
			<li>Name: ${productToDelete.name}</li>
			<li>Description: ${productToDelete.description}</li>
			<li>Price: ${productToDelete.price}</li>
		</ul>
		<form action="Controller?action=deleteProductConfirmed" method="POST">
			<input type="hidden" name="productId"
				value="${productToDelete.productId}" /> <input type="submit"
				value="Sure" name="button"> <input type="submit"
				value="No, thanks" name="button" />
		</form>
		</main>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>