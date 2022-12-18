<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="dam2.e1.model.Producto"%>
<%@ page import="dam2.e1.model.Pedido"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
<title>MUCHOPHONE</title>
</head>
<body>
    
    <div class="menu-top">
         <div class="menu-top-p titulo">
            <a href="<%=request.getContextPath()%>/?login=ir">
            <img src="<%=request.getContextPath()%>/resources/img/login.png"></a>
        </div>
        <div class="menu-top-p titulo">
            <a href="<%=request.getContextPath()%>/?carrito=si">
            <img src="<%=request.getContextPath()%>/resources/img/carro.png"></a>
        </div>
        <div class="menu-top-p titulo">
            <a href="<%=request.getContextPath()%>/vistas/altausuario.jsp">
            <img src="<%=request.getContextPath()%>/resources/img/logout.png"></a>
        </div>
    </div>
    <div class="centrar">
    <div><%Pedido pedido = (Pedido) request.getAttribute("pedido");
    if (pedido != null){
    	if (pedido.getUsuario() != null){
    	%> Hola <%=pedido.getUsuario().getNombre() %>
    <%}}
    %></div>
        <table>
        <!-- "tr" crea cada fila de la tabla-->
        <tr>
            <!--Utilizo "th" para definir los encabezados de la tabla, estarán en negrita y centrados-->
            <th>Nombre</th>
            <th>Descripción</th>
            <th>Precio</th>
            <th>Stock</th>
            <th>Acción</th>
        </tr>
            <%ArrayList<Producto> listaProductos = new ArrayList<Producto>();
            listaProductos = (ArrayList<Producto>) request.getAttribute("productos");
            if (listaProductos != null) {
            	
            
            for (int i = 0; i<listaProductos.size();i++){
        	Producto auxiliar = listaProductos.get(i);
        	if (auxiliar.getStock() > 0){
        	%>
        <tr>
            <!--"td" son columnas de la fila, en este caso no están en negrita y estarán alineadas a la izquierda-->
            <td><%=auxiliar.getNombre()%></td>
            <td><%=auxiliar.getDescripcion()%></td>
            <td><%=auxiliar.getPrecio()%></td>
            <td><%=auxiliar.getStock()%></td>
            <td>
                <a href="<%=request.getContextPath()%>/?id=<%=auxiliar.getId()%>">
                <img id = "botonAnadir" src="<%=request.getContextPath()%>/resources/img/añadir.png"></a>
            </td>
        </tr>
        	<%}}}%>

    </table>
    </div>
    
    
</body>
</html>