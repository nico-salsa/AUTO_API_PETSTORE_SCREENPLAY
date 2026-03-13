package com.petstore.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class AgregarMascota implements Task {

    private final String nombre;
    private final String categoria;
    private final String photoUrl;
    private final String estado;

    public AgregarMascota(String nombre, String categoria, String photoUrl, String estado) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.photoUrl = photoUrl;
        this.estado = estado;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        long id = System.currentTimeMillis() % 1000000;
        String body = String.format(
            "{" +
            "\"id\": %d," +
            "\"category\": {\"id\": 1, \"name\": \"%s\"}," +
            "\"name\": \"%s\"," +
            "\"photoUrls\": [\"%s\"]," +
            "\"tags\": [{\"id\": 0, \"name\": \"string\"}]," +
            "\"status\": \"%s\"" +
            "}",
            id, categoria, nombre, photoUrl, estado
        );

        actor.attemptsTo(
            Post.to("/pet")
                .with(request -> request
                    .contentType("application/json")
                    .accept("application/json")
                    .body(body))
        );

        actor.remember("nombreMascota", lastResponse().jsonPath().getString("name"));
        actor.remember("estadoMascota", lastResponse().jsonPath().getString("status"));
    }

    public static AgregarMascota conDatos(String nombre, String categoria, String photoUrl, String estado) {
        return new AgregarMascota(nombre, categoria, photoUrl, estado);
    }
}
