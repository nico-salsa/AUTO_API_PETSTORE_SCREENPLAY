package com.petstore.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class BuscarMascotasPorEstado implements Task {

    private final String estado;

    public BuscarMascotasPorEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Get.resource("/pet/findByStatus?status=" + estado)
                .with(request -> request
                    .accept("application/json"))
        );
        actor.remember("estadoBuscado", estado);
    }

    public static BuscarMascotasPorEstado conEstado(String estado) {
        return new BuscarMascotasPorEstado(estado);
    }
}
