<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/home.css">
</head>
<body>
<c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
<div>
<ul>
	  
      <li>  <h2>Welcome ${pageContext.request.userPrincipal.name} </h2></li>
      <li> <a class="active" href="/login"><h3>log out</h3></a></li>
      <li>  <a class="active" href="/addMovie"><h3>Add Movie/anime</h3></a></li>
      <li>  <a class="active" href="/getAnime"><h3>Anime</h3></a></li>
      <li>  <a class="active" href="/getMovie"><h3>Movies</h3></a></li>
      <li>  <a class="active" href="/getMovieList"><h3>MovieList</h3></a></li>
      <li>  <a class="active" href="/getAnimeList"><h3>AnimeList</h3></a></li>
</ul>
</div>
<div>
<table>
<tr>
<td><a href="/getAnime" title="into the Anime world"><img alt="" src="anime.jpg" height="500" width="620"></a></td>
<td><a href="/getMovie" title="into the Hollywood"><img alt="" src="https://wallpapers.com/images/high/hollywood-background-j4n1g2es20s70vnq.webp" height="500" width="620"></a></td>
</tr>
</table>
</div>
    </c:if>

</body>
</html>