@login
Feature: Verificar la funcionalidad de la ventana de inicio de sesion

  @AgR-1
  Scenario: Inicio de sesion con credenciales validos
    Given usuario rellena el campo de correo con "mezquerro@hiberus.com"
    And usuario rellena el campo de contrasena con "12345"
    When usuario clica el boton de iniciar sesion
    Then el usuario aparece en la ventana de listado de fincas

