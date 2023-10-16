<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" href="/resources/css/addMovie.css">
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
<form:form method="POST" modelAttribute="MovieForm" class="form-signin">
        <h2 class="form-signin-heading">Add a Movie</h2>
        <spring:bind path="name">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="name" class="form-control" placeholder="Moviename"
                            autofocus="true"></form:input>
                <form:errors path="name"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="rating">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="rating" class="form-control" placeholder="rating"></form:input>
                <form:errors path="rating"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="genre">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="genre" class="form-control"
                            placeholder="genre"></form:input>
                <form:errors path="genre"></form:errors>
            </div>
        </spring:bind>
         <spring:bind path="description">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="description" class="form-control"
                            placeholder="description"></form:input>
                <form:errors path="description"></form:errors>
            </div>
        </spring:bind>
		 <spring:bind path="link">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="link" class="form-control"
                            placeholder="link"></form:input>
                <form:errors path="link"></form:errors>
            </div>
        </spring:bind>
         <spring:bind path="movieType">
            <div class="form-group ${status.error ? 'has-error' : ''}">
               <form:checkboxes items = "${movieTypeList}" value="${movieTypeList.key}" path = "movieType" />
                <form:errors path="movieType"></form:errors>
            </div>
        </spring:bind> 
        
<%--         <spring:bind path="roles"> --%>
<%--             <div class="form-group ${status.error ? 'has-error' : ''}"> --%>
              
<%--                <form:checkbox path="roles" value="1" />USER --%>
<%--                <form:checkbox path="roles" value="2" />CREATOR --%>
<%--                <form:checkbox path="roles" value="3" />EDITOR --%>
<%--                 <form:checkbox path="roles" value="4" />ADMIN --%>
<!--             </div> -->
<%--         </spring:bind> --%>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>
</div>
</body>
</html>