# language: es
@e2e @compra
Característica: Flujo de compra completo como invitado

  Como usuario invitado de la tienda OpenCart
  Quiero realizar una compra completa de dos productos
  Para verificar que el flujo E2E de compra funciona correctamente

  Escenario: Compra exitosa de dos productos como invitado
    Dado que el usuario se encuentra en la pagina principal de la tienda
    Cuando el usuario agrega los siguientes productos al carrito
      | producto       |
      | MacBook        |
      | iPhone         |
    Y el usuario visualiza el carrito de compras
    Entonces el carrito debe contener 2 productos
    Cuando el usuario procede al checkout como invitado
    Y el usuario completa los datos de facturacion
      | campo     | valor              |
      | firstName | Juan               |
      | lastName  | Perez              |
      | email     | juan@test.com      |
      | telephone | 3001234567         |
      | address1  | Calle 123          |
      | city      | Bogota             |
      | postCode  | 110111             |
      | country   | Colombia           |
      | zone      | Bogota D.C.        |
    Y el usuario confirma la orden de compra
    Entonces el usuario debe ver el mensaje de confirmacion "Your order has been placed!"
