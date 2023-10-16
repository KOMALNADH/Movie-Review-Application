<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/editMovie.css">
</head>
<body>
<ul>
	     <li>  <h2>Welcome ${pageContext.request.userPrincipal.name} </h2></li>
      <li> <a class="active" href="/login"><h3>log out</h3></a></li>
      <li>  <a class="active" href="/addMovie"><h3>Add Movie/anime</h3></a></li>
      <li>  <a class="active" href="/getAnime"><h3>Anime</h3></a></li>
      <li>  <a class="active" href="/getMovie"><h3>Movies</h3></a></li>
      <li>  <a class="active" href="/getMovieList"><h3>MovieList</h3></a></li>
      <li>  <a class="active" href="/getAnimeList"><h3>AnimeList</h3></a></li>
       <li>  <a class="active" href="/"><h3>Home</h3></a></li>
      
       
</ul>
<br><br>
<div class="header">
<h1>Update Product</h1>
		<br/>
		<c:url var="action" value="/edit"/>
	<form:form action="${action}" method="post"  modelAttribute="editMovie" novalidate="novalidate">
     <table>
            <tr>
				<td>Movie ID:</td>
				<td><form:input path="id" readonly="readonly"/></td>
			</tr>
			<tr>
				<td>Movie Name:</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>Movie Rating:</td>
				<td><form:input path="rating" /></td>
				<%-- <font color="red"><form:errors path="age" /></font> --%>
			</tr>
			<tr>
				<td>Movie genre:</td>
				<td><form:input path="genre" /></td>
			</tr>
			<tr>
				<td>Movie Description:</td>
				<td><form:input path="description" /></td>
			</tr>
			<tr>
				<td>Movie Link:</td>
				<td><form:input path="link" /></td>
			</tr>
			<tr>
			<td></td>
			<spring:bind path="movieType">
               <td ><form:checkboxes items = "${movieTypeList}" value="${movieTypeList.key}" path = "movieType" />
                <form:errors path="movieType"></form:errors></td>
        </spring:bind>
			</tr>
			
			
			<tr>
				<td><input type="submit" value="Edit Movie" /></td>
				<td><input type="reset" value="Cancel" /></td>
			</tr>
		</table>
         
        
      </form:form>
      </div>
</body>
</html>