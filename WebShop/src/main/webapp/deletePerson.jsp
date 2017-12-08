<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param value="Delete Person Confirmation" name="title" />
</jsp:include>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="Delete Person Confirmation" />
		</jsp:include>
		<main>
		<p>Are you sure you want to delete ${personToDelete.firstName}
			${personToDelete.lastName}?</p>
		<form action="Controller?action=deletePersonConfirmed" method="POST">
			<input type="hidden" name="userId"
				value="${personToDelete.userid}" /> <input type="submit"
				value="Sure" name="button"> <input type="submit"
				value="No, thanks" name="button" />
		</form>
		</main>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>