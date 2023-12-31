<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an account</title>

        <link rel="stylesheet" href="/resources/css/registration.css">


    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">

<%--     <form:form method="POST" modelAttribute="userForm" class="form-signin"> --%>
<!--         <h2 class="form-signin-heading">Create your account</h2> -->
<%--         <spring:bind path="user"> --%>
<%--             <div class="form-group ${status.error ? 'has-error' : ''}"> --%>
<%--                 <form:input type="text" path="user" class="form-control" placeholder="Username" --%>
<%--                             autofocus="true"></form:input> --%>
<%--                 <form:errors path="user"></form:errors> --%>
<!--             </div> -->
<%--         </spring:bind> --%>

<%--         <spring:bind path="password"> --%>
<%--             <div class="form-group ${status.error ? 'has-error' : ''}"> --%>
<%--                 <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input> --%>
<%--                 <form:errors path="password"></form:errors> --%>
<!--             </div> -->
<%--         </spring:bind> --%>

<%--         <spring:bind path="passwordConfirm"> --%>
<%--             <div class="form-group ${status.error ? 'has-error' : ''}"> --%>
<%--                 <form:input type="password" path="passwordConfirm" class="form-control" --%>
<%--                             placeholder="Confirm your password"></form:input> --%>
<%--                 <form:errors path="passwordConfirm"></form:errors> --%>
<!--             </div> -->
<%--         </spring:bind> --%>
<div class="header">
	<h2>Registration page</h2>
	<form:form action="/registration" method="post" modelAttribute="userForm">
	  UserName:-
	<form:input type="text" path="user" />
	<h5 style="color : red;"><form:errors path="user" /></h5>
	Password:-
	<form:input type="text" path="password" />
	<h5 style="color : red;"><form:errors path="password" /></h5>
	Confirm Password:-
	<form:input type="text" path="passwordConfirm" />
	<h5 style="color : red;"><form:errors path="passwordConfirm" /></h5>

         <spring:bind path="roles">
            <div class="form-group ${status.error ? 'has-error' : ''}">
               <form:checkboxes items = "${roleList}" value="${roleList.key}" path = "roles" />
                <form:errors path="roles"></form:errors>
            </div>
        </spring:bind> 
         <br>
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
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>