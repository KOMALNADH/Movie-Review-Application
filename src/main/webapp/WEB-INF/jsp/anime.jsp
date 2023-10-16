<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/anime.css">
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
<br>
<div class="genre-container">
<button type="submit" class="button"><a href="/anime/action">Action</a></button>
<button type="submit" class="button"><a href="/anime/adventure">Adventure</a></button>
<button type="submit" class="button"><a href="/anime/sports">Sports</a></button>
<button type="submit" class="button"><a href="/anime/sifi">Sifi</a></button>
<button type="submit" class="button"><a href="/anime/shoenen">Shoenen</a></button>
<form:form method="Get" action="searchByName">
<div class="search">
	<input type="text" name="display"  class="text-field" >
	<button type="submit" class="button" >Search</button>
</div>
</form:form>
</div>
<br>
<div class="image-container">
<c:forEach items="${alist}" var="anime">
<div class="image-wrapper">
<a href="/animeData/${anime.id}"><img src="${anime.link}" width="175" height="230" alt="${anime.name}"></a>
<h5>${anime.name}</h5>
</div>
</c:forEach>
</div>
<script type="text/javascript">
</script>
</body>
</html>