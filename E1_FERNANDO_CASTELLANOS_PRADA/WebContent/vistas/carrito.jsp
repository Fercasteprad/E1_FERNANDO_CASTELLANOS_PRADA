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
<title>Insert title here</title>
</head>
<body>
    <%Pedido pedido = (Pedido) request.getAttribute("pedido");%>
 
     <div class="menu-top">
         <div class="menu-top-p titulo">
            <a href="<%=request.getContextPath()%>/?login=ir">
            <img src="<%=request.getContextPath()%>/resources/img/login.png"></a>
        </div>
        <div class="menu-top-p titulo">
            <a href="<%=request.getContextPath()%>/">
            <img src="<%=request.getContextPath()%>/resources/img/tienda.png"></a>
        </div>
        <div class="menu-top-p titulo">
            <a href="<%=request.getContextPath()%>/vistas/altausuario.jsp">
            <img src="<%=request.getContextPath()%>/resources/img/logout.png"></a>
        </div>
    </div>
 
     <table>
        <tr>
            <th>Nombre</th>
            <th>unidades</th>
            <th>Precio unitario</th>
            <th>Precio total</th>
        </tr>
            <%
            if (pedido != null){
                if(pedido.getProductos() != null){
            ArrayList<Producto> listaProductos = new ArrayList<Producto>();
            listaProductos = pedido.getProductos();
           
            int total = 0;    
            
            for (int i = 0; i<listaProductos.size();i++){
            Producto auxiliar = listaProductos.get(i);
            int precioUnitario = auxiliar.getPrecio()/auxiliar.getStock();
            if (auxiliar.getStock() > 0){
            	total = total + auxiliar.getPrecio();
            %>
        <tr>
            <td><%=auxiliar.getNombre()%></td>
            <td><%=auxiliar.getStock()%></td>
            <td><%=precioUnitario%></td>
            <td><%=auxiliar.getPrecio()%></td>
        </tr>

            <%}}%>
          <tr>
            <td>Total</td>
            <td><%=total%></td>
        </tr>
            <%}}%>

    </table>
  <div>
  <form action="<%=request.getContextPath()%>/" method="post">
    <input type="text" hidden="true" name="compra" value="comprar"> 
    <input type="submit" name="boton" value="Comprar">
  </form>
  </div>
  

</body>
</html>