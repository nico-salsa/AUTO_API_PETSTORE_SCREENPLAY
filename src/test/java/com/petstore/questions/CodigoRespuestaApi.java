package com.petstore.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class CodigoRespuestaApi implements Question<Integer> {

    @Override
    public Integer answeredBy(Actor actor) {
        return lastResponse().statusCode();
    }

    public static CodigoRespuestaApi obtenido() {
        return new CodigoRespuestaApi();
    }
}
