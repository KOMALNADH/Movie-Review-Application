<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/animeList.css">

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
<table border=2 align="center">
<tr>
<th>Movie id</th>
<th>Movie Name</th>
<th>Movie Rating</th>
<th>Movie Genre</th>
<th>Movie Link</th>
<th>Action</th>
<th>Edit</th>
</tr>
<c:forEach items="${hlist}" var="movie">
<tr>
<td>${movie.id}</td>
<td>${movie.name}</td>
<td>${movie.rating}</td>
<td>${movie.genre}</td>
<td><a href=""><img src="${movie.link}" width="50" height="50" alt="${movie.name}"></a></td>
<td><a href="/delete/${movie.id}">delete</a></td>
<td><a href="/edit/${movie.id}">edit</a></td>
</tr>
</c:forEach>
</table>
</body>
</html>