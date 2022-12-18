<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
<title>Login</title>
</head>
<body>
    <div class="centrar">
        <h1>Login</h1>
    </div>
    <% String error = (String) request.getAttribute("error"); %>
    <div class ="centrar">
        <form action="<%=request.getContextPath()%>/" method="post">
            <input class="barras" type ="text" id="name" name="nombre" placeholder="Introduce usuario"><br>
            <input class="barras" type ="password" id= "pass01" name="pass" placeholder="Introduce contraseña"><br>
            <div class ="centrar">
                <input id="boton" type ="submit" name="boton" placeholder="Enviar">
            </div>
            <div><p>
            <%if (error != null){
            	out.print(error);
            }%>
            </p></div>
        </form>
    </div>
    <div class ="centrar">
    <a href="<%=request.getContextPath()%>/vistas/altausuario.jsp">Registrarse</a>
    </div>
        <div class ="centrar">
    <a href="<%=request.getContextPath()%>/">Ir a Tienda</a>
    </div>
     
      
</body>
</html>
