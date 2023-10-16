<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
  .rating {
    direction: rtl; /* Reverse the order of stars */
    unicode-bidi: bidi-override; /* Adjust for RTL text */
  }

  .rating input {
    display: none; /* Hide the radio inputs */
  }

  .rating label {
    display: inline-block;
    cursor: pointer;
    width: 20px;
    height: 20px;
    background-image: url('star.png'); /* Replace with your star image */
    background-size: cover;
  }

  .rating label::before {
    content: "\2606"; /* Unicode character for an empty star */
    position: absolute;
  }

  .rating label:hover::before,
  .rating label:hover ~ label::before,
  .rating input:checked ~ label::before {
    content: "\2605"; /* Unicode character for a filled star */
  }
</style>
</head>
<body>
<a href="/">home</a>
<a href="/login">logout</a>
<h2>you don't have permission to access this page</h2>
</body>
<div class="rating">
	<form:form action="/star/${rating}" method="get">
 <input type="radio" id="star5" name="rating" value="5">
  <label for="star5"></label>
  <input type="radio" id="star4" name="rating" value="4">
  <label for="star4"></label>
  <input type="radio" id="star3" name="rating" value="3">
  <label for="star3"></label>
  <input type="radio" id="star2" name="rating" value="2">
  <label for="star2"></label>
  <input type="radio" id="star1" name="rating" value="1">
  <label for="star1"></label>
          <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
   </form:form>
</div>
</html>