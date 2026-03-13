Feature: Gestión de mascotas en el PetStore

  Background:
    Given que el servicio PetStore esta disponible

  Scenario Outline: Registrar una nueva mascota en la tienda
    When se agrega una nueva mascota con nombre "<nombre>", categoria "<categoria>", photoUrl "<photoUrl>" y estado "<estado>"
    Then la respuesta debe tener codigo de estado 200
    And la mascota registrada debe tener nombre "<nombre>" y estado "<estado>"

    Examples:
      | nombre   | categoria | photoUrl                             | estado    |
      | Firulais | Dogs      | https://example.com/firulais.jpg     | available |
      | Michi    | Cats      | https://example.com/michi.jpg        | pending   |

  Scenario Outline: Buscar mascotas por estado
    When se buscan mascotas con estado "<estado>"
    Then la respuesta debe tener codigo de estado 200
    And la lista de mascotas no debe estar vacia
    And todas las mascotas deben tener estado "<estado>"

    Examples:
      | estado    |
      | available |
      | pending   |
      | sold      |
