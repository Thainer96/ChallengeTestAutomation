# language: es
@e2e @compra
Característica: Flujo de compra completo como invitado

  Como usuario invitado de la tienda OpenCart
  Quiero realizar una compra completa de dos productos
  Para verificar que el flujo E2E de compra funciona correctamente

  Escenario: Compra exitosa de dos productos como invitado
    Dado que "Guest Checkout" se encuentra en la pagina principal de la tienda
    Cuando agrega los siguientes productos al carrito
      | MacBook        |
      | iPhone         |
    Y visualiza el carrito de compras
    Entonces el carrito debe contener 2 productos
    Cuando procede al checkout como invitado
    Y completa los datos de facturacion
      | firstName | Juan               |
      | lastName  | Perez              |
      | email     | juan@test.com      |
      | telephone | 3001234567         |
      | address1  | Calle 123          |
      | city      | Bogota             |
      | postCode  | 110111             |
      | country   | Colombia           |
      | zone      | Bogota D.C.        |
    Y confirma la orden de compra
    Entonces debe ver el mensaje de confirmacion "Your order has been placed!"

  Esquema del escenario: Compra exitosa con diferentes datos de facturacion
    Dado que "Guest Checkout" se encuentra en la pagina principal de la tienda
    Cuando agrega los siguientes productos al carrito
      | MacBook Air afasdasd        |
      | HTC Touch HD       |
    Y visualiza el carrito de compras
    Entonces el carrito debe contener 2 productos
    Cuando procede al checkout como invitado
    Y completa los datos de facturacion
      | firstName | <nombre>           |
      | lastName  | <apellido>         |
      | email     | <correo>           |
      | telephone | <telefono>         |
      | address1  | <direccion>        |
      | city      | <ciudad>           |
      | postCode  | <codigoPostal>     |
      | country   | <pais>             |
      | zone      | <departamento>     |
    Y confirma la orden de compra
    Entonces debe ver el mensaje de confirmacion "Your order has been placed!"

    Ejemplos:
      | nombre  | apellido | correo              | telefono   | direccion     | ciudad    | codigoPostal | pais     | departamento    |
      | Carlos  | Lopez    | carlos@test.com     | 3109876543 | Carrera 45    | Medellin  | 050001       | Colombia | Antioquia       |
      | Maria   | Garcia   | maria@test.com      | 3201234567 | Avenida 68    | Cali      | 760001       | Colombia | Valle del Cauca |
