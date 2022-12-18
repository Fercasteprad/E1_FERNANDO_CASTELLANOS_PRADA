Se han creado todos los requisitos necesarios en la examen

Se han realizado los siguientes paquetes y clases:
	- dam2.e1.controller: En este paquete está el controlador web que gestiona las vistas.
	- dam2.e1.DAO: Contiene todas las clases de los accesos a datos.
	- dam2.e1.model: Contiene todas las clases POJO de los que dispone el programa.
	- dam2.e1.service: Contiene todas las clases de servicio para gestionar el DAO y el controlador.

Los recursos y vistas se alojan en WebContent:
	- index.jsp: Desde este JSP se accede a la tienda, mostrando todos los productos disponibles en Stock. Contiene un menú
		con tres opciones, ir a hacer login, ir al carrito o darse de alta.
	- carrito.jsp: Si hay un carrito disponible muestra los productos que tiene el carrito y se puede proceder a la compra,
		si previamente no se ha logeado un usuario el controlador te mandará a la vista de login.jsp.
	- login.jsp: Esta vista contiene un formulario que comprueba el usuario y contraseña, en el caso de no coincidir te avisara
		con un mensaje, se puede acceder a realizar el registro previo.
	- altausuario.jsp: Esta vista contiene un formulario para realizar un nuevo registro, si el correo existe en la base de datos
		no te deja acabar el registro.

El flujo del programa es el siguiente:
	1. Se accede a la tienda a través del index.
	2. añades productos al carrito.
	3. Desde el menú accedes al carrito.
	4. Pinchas en comprar y accedes a la vista de Login.
	5. Se realiza el Login o el alta de usuario.
	6. En el caso de loguearse correctamente vuelves al punto 3.
	7. En el caso de un alta de usuario nuevo vuelves al punto 3.