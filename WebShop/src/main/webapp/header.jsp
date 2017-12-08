<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<header>
	<h1>
		<span>My Webshop</span>
	</h1>
	<nav>
		<ul>
			<c:choose>
				<c:when test="${param.title == 'Home'}">
					<li id="actual"><a href="Controller">Home</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="Controller">Home</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${param.title == 'Users'}">
					<li id="actual"><a href="Controller?action=overview">Users</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="Controller?action=overview">Users</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${param.title == 'Products'}">
					<li id="actual"><a href="Controller?action=products">Products</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="Controller?action=products">Products</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${param.title == 'Add Product'}">
					<li id="actual"><a href="Controller?action=addProduct">Add Product</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="Controller?action=addProduct">Add Product</a></li>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${param.title == 'Sign Up'}">
					<li id="actual"><a href="Controller?action=signUp">Sign up</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="Controller?action=signUp">Sign up</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
	<h2>${param.title }</h2>

</header>