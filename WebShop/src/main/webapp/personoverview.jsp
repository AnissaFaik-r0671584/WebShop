<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param value="User Overview" name="title" />
</jsp:include>
<body>
	<div id="container">
		<jsp:include page="header.jsp">
			<jsp:param name="title" value="User Overview" />
		</jsp:include>
		<main>
		<c:choose>
			<c:when test="${personList.size()==0 }">
				<p>There are no persons in the list.</p>
			</c:when>
			<c:otherwise>
				<table>
					<tr>
						<th>E-mail</th>
						<th>First Name</th>
						<th>Last Name</th>
					</tr>
					<c:forEach var="user" items="${personList}">
						<tr>
							<td><c:out value="${user.email }" /></td>
							<td><c:out value="${user.firstName }" /></td>
							<td><c:out value="${user.lastName }" /></td>
							<td><a
								href="Controller?action=deletePerson&userid=<c:out value='${user.userid}'/>">Delete</a>
						</tr>

					</c:forEach>
					<caption>Users Overview</caption>
				</table>
			</c:otherwise>
		</c:choose> 
		</main>
		<jsp:include page="footer.jsp" />
	</div>
</body>
</html>