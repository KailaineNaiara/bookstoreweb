<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Aplicação User Store</title>
    </head>
    <body>
        <h1>Aplicação UserStoreWeb</h1>
        <p><a href="<%=request.getContextPath()%>/bstore/new">Adicionar novo
                User</a></p>
        <p><a href="<%=request.getContextPath()%>/list">Lista todos
                User</a></p>

        <div align="left">
            <c:if test="${book != null}">
                <form action="update" method="post">
                </c:if>
                <c:if test="${book == null}">
                    <form action="insert" method="post">
                    </c:if>
                    <table border="1" cellpadding="5">
                        <caption>
                            <h2>
                                <c:if test="${book != null}">
                                    Editar Book
                                </c:if>
                                <c:if test="${book == null}">
                                    Adicionar novo Book
                                </c:if>
                            </h2>
                        </caption>
                        <c:if test="${user != null}">
                            <input type="hidden" name="formId" value="<c:out
                                       value='${book.id}' />" />
                        </c:if>
                        <tr>
                            <th>Titulo: </th>
                            <td>
                                <input type="text" name="formTitulo" size="45"
                                       value="<c:out value='${book.titulo}' />"
                                       />
                            </td>
                        </tr>
                        <tr>
                            <th>Autor: </th>
                            <td>
                                <input type="text" name="formAutor" size="45"
                                       value="<c:out value='${book.autor}' />"
                                       />
                            </td>
                        </tr>
                        <tr>
                            <th>Preco: </th>
                            <td>
                                <input type="text" name="formPreco" size="5"
                                        value="<c:out value='${book.preco}' />"
                                       />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <input type="submit" value="Enviar" />
                            </td>
                        </tr>
                    </table>
                </form>
        </div>
    </body>
</html>

