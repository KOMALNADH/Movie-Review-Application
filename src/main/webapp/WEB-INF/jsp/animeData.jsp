<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/resources/css/animeData.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
/*   .rating { */
/*     direction: rtl; /* Reverse the order of stars */ */
/*     unicode-bidi: bidi-override; /* Adjust for RTL text */ */
/*   } */

/*   .rating input { */
/*     display: none; /* Hide the radio inputs */ */
/*   } */

/*   .rating label { */
/*     display: inline-block; */
/*     cursor: pointer; */
/*     width: 20px; */
/*     height: 20px; */
/*     background-image: url('star.png'); /* Replace with your star image */ */
/*     background-size: cover; */
/*   } */

/*   .rating label::before { */
/*     content: "\2606"; /* Unicode character for an empty star */ */
/*     position: absolute; */
/*   } */

/*   .rating label:hover::before, */
/*   .rating label:hover ~ label::before, */
/*   .rating input:checked ~ label::before { */
/*     content: "\2605"; /* Unicode character for a filled star */ */
/*   } */
.wrapper {
  display: inline-block;
}
.wrapper * {
  float: right;
}
 .wrapper input {
  display: none;
}
label {
  font-size: 30px;
}

input:checked ~ label {
  color: red;
}
/*
label:hover,
label:hover ~ label {
  color: red;
}
*/
/*
input:checked ~ label:hover,
input:checked ~ label:hover ~ label {
  color: lime !important;
}
*/
</style>
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
<c:set var="Anime" value="AnimeData"></c:set>
<center><img src="${AnimeData.link}" width="175" height="230" alt="${AnimeData.name}"></center>
<h3>${AnimeData.name}</h3>
<h2 class="rating1">rating:-  ${AnimeData.rating}</h2>
<h2 class="rating1">${AnimeData.description}</h2>
<br><br>
<h2 class="rating1">comments</h2>
<form:form action="/addReview/${AnimeData.id}" method="post" modelAttribute="reviewForm">
<table>
	  <tr>
	  <td class="rating1">UserName:-</td>
	<td><form:input type="text" path="userName" /></td>
	</tr>
	<tr>
	<td class="rating1">rating:-</td>
<!-- 	<td><input type="radio" id="star5" name="rating" value="1"> -->
<!--   <label for="star5"></label> -->
<!--   <input type="radio" id="star4" name="rating" value="2"> -->
<!--   <label for="star4"></label> -->
<!--   <input type="radio" id="star3" name="rating" value="3"> -->
<!--   <label for="star3"></label> -->
<!--   <input type="radio" id="star2" name="rating" value="4"> -->
<!--   <label for="star2"></label> -->
<!--   <input type="radio" id="star1" name="rating" value="5"> -->
<!--   <label for="star1"></label> -->
<td><div class="wrapper">
  <input type="radio" id="r1" name="rating" value="5">
  <label for="r1">&#10038;</label>
  <input type="radio" id="r2" name="rating" value="4">
  <label for="r2">&#10038;</label>
  <input type="radio" id="r3" name="rating" value="3">
  <label for="r3">&#10038;</label>
  <input type="radio" id="r4" name="rating" value="2">
  <label for="r4">&#10038;</label>
  <input type="radio" id="r5" name="rating" value="1">
  <label for="r5">&#10038;</label>
</div></td>
	</tr>
	<tr>
	<td class="rating1">comment:-</td>
	<td><form:input type="text" path="comment" /></td>
	</tr>
</table>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
       
    </form:form>
<h2 class="rating1">Reviews:-</h2>
<c:forEach items="${reviewList}" var="review">
<h5 class="rating1">${review.userName}</h5>
<h5 class="rating1">${review.rating}/5</h5>
<h5 class="rating1">${review.comment}</h5>
</c:forEach>
</body>
</html>