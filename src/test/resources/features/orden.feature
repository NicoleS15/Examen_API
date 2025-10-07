Feature: Creación y Consulta de Orden en PetStore

  Scenario Outline: Crear y consultar una orden en PetStore
    Given una orden con petId <petId>, quantity <quantity> y status "<status>"
    When envío la solicitud de creación de la orden
    Then la respuesta del POST debe tener status code 200
    And el cuerpo debe contener un id no nulo y el status "<status>"
    When consulto la orden creada por su id
    Then la respuesta del GET debe tener status code 200
    And el cuerpo del GET debe contener el mismo id y status "<status>"

    Examples:
      | petId | quantity | status   |
      | 1     | 4        | placed   |
      | 3     | 3        | approved |


