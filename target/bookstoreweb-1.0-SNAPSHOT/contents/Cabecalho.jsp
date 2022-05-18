<%-- 
    Document   : cabecalho
    Created on : 25 de abr de 2022, 13:50:47
    Author     : knv
--%>

<!-- Inicio cabecalho-->
<div class="jumbotron"><h1>BookStoreWeb</h1></div>



<p>
    <a href="<%=request.getContextPath()%>/bstore/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus"></span>Adicionar novo Livro</a>

        <a href="<%=request.getContextPath()%>/list" class="btn btn-default">
        <span class="glyphicon glyphicon-th-list"></span>
        Lista todos livros </a>
        
        <a href="<%=request.getContextPath()%>/list" class="btn btn-default">
        <span class="glyphicon glyphicon-th-list"></span>
        Lista todos usuarios</a>
        
        <a href="<%=request.getContextPath()%>/logout" class="btn btn-danger">
            <span class="glyphicon glyphicon-th-list"></span> Sair </a>
            
</p>

<!-- Fim cabecalho-->