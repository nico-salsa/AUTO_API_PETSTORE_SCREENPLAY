# PetSwagger Tests

Proyecto de automatización de pruebas API para [Swagger PetStore](https://petstore.swagger.io) usando el patrón **Screenplay** con **Serenity BDD**, **Cucumber** y **Java**.

## Stack tecnológico

| Herramienta | Versión |
|---|---|
| Java | 16 |
| Gradle | 8.x |
| Serenity BDD | 4.2.34 |
| Cucumber | 7.20.1 |
| JUnit Platform | 5.11.4 |

## Estructura del proyecto

```
src/test/java/com/petstore/
├── hooks/          # ConfigurarApi – asigna la habilidad CallAnApi al actor
├── models/         # Usuario – modelo del actor de prueba
├── questions/      # CodigoRespuestaApi – pregunta el código HTTP de la última respuesta
├── runners/        # PetStoreTestRunner – runner JUnit 5 + Cucumber
├── stepdefinitions/# Implementación de los pasos Gherkin
├── tasks/          # AgregarMascota, BuscarMascotasPorEstado – tareas Screenplay
└── util/           # Constantes – URL base de la API

src/test/resources/
├── features/       # gestion_mascotas.feature
└── serenity.conf   # Configuración de Serenity
```

## Escenarios de prueba

**Feature:** `Gestión de mascotas en el PetStore`

| Escenario | Endpoint | Datos |
|---|---|---|
| Registrar una nueva mascota | `POST /pet` | Firulais (Dogs/available), Michi (Cats/pending) |
| Buscar mascotas por estado | `GET /pet/findByStatus` | available, pending, sold |

Ambos escenarios validan el código de respuesta HTTP `200` y la integridad de los datos retornados por la API.

## Ejecución

```bash
# Ejecutar pruebas + generar reporte Serenity
./gradlew

# Solo compilar y ejecutar pruebas
./gradlew clean test

# Solo regenerar el reporte
./gradlew aggregate
```

> En Windows usar `.\gradlew.bat` en lugar de `./gradlew`.

## Reporte

Tras la ejecución, el reporte HTML de Serenity queda disponible en:

```
target/site/serenity/index.html
```
