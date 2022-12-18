<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
<title>REGISTRO</title>
</head>
<body>

    <div class="centrar">
        <h1>Agenda de contactos</h1>
    </div>
    <div class="centrar">
        <form action="<%=request.getContextPath()%>/" method="post">

            <input class="barras" type="text" name="nombreForm" placeholder="Introduce nombre"
                <%String name = (String) request.getAttribute("nombre");
					if (name != null) {
						out.print("value='" + name + "'");
					}%>><br>
            <input class="barras" type="text" name="apellidosForm" placeholder="Introduce apellidos"
                <%String lastname = (String) request.getAttribute("apellidos");
					if (lastname != null) {
						out.print("value='" + lastname + "'");
					}%>><br>
            <input class="barras" type="email" name="emailForm" placeholder="Introduce email"
                <%String mail = (String) request.getAttribute("email");
					if (mail != null) {
						out.print("value='" + mail + "'");
					}%>><br>
            <input class="barras" type="number" name="telefonoForm" placeholder="Introduce teléfono"
                <%String phone = (String) request.getAttribute("telefono");
					if (phone != null) {
						out.print("value='" + phone + "'");
					}%>><br>
             <input class="barras" type="password" name="pass1" placeholder="Introduce contraseña"><br>
             <input class="barras" type="password" name="pass2" placeholder="Repite contraseña"><br>
            <div class="centrar">
                <input id="boton" type="submit" name="boton" value="Crear usuario"><br>
            </div>
                        <div class="centrar">
                <%
                String error = (String) request.getAttribute("error");
                if (error != null && !error.equals("")) {%>
                    <p id="mensaje_error"><%=error%></p>
                <%}%>
            </div>
    </form>
    </div>  
</body>
</html>