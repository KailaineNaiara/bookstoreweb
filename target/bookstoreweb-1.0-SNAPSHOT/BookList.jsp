<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="./contents/HeaderTags.jsp"/>
        <title>Aplicação Book</title>
    </head>
    <body>
        <div class="container">

            <jsp:include page="./contents/Cabecalho.jsp"/>

            <div clas="table-responsive">
                <table class ="table table-hover">
                    <caption><h2>List of Users</h2></caption>
                    <tr>
                        
                        <th>ID</th>
                        <th>Email</th>
                        <th>Password</th>
                        <th>Fullname</th>
                        
                    </tr>

                    <c:forEach var="book" items="${listaBook}">
                        <tr>
                            <td><c:out value="${book.id}" /></td>
                            <td><c:out value="${book.titulo}" /></td>
                            <td><c:out value="${book.autor}" /></td>
                            <td><c:out value="${book.preco}" /></td>
                            <td>
                                <a href="<%=request.getContextPath()%>
                                   /edit?id=<c:out value='${book.id}' />">
                                    <span class="glyphicon glyphicon-pencil"></span></a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="<%=request.getContextPath()%>
                                   /delete?id=<c:out value='${book.id}' />">
                                    <span class="glyphicon glyphicon-trash"></span></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <jsp:include page="./contents/Rodape.jsp"/>
        </div>
    </body>
</html>

