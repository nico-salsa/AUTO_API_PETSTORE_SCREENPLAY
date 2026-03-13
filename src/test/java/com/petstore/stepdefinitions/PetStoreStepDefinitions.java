package com.petstore.stepdefinitions;

import com.petstore.models.Usuario;
import com.petstore.questions.CodigoRespuestaApi;
import com.petstore.tasks.AgregarMascota;
import com.petstore.tasks.BuscarMascotasPorEstado;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import java.util.List;

import static com.petstore.util.Constantes.BASE_URL;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static org.assertj.core.api.Assertions.assertThat;

public class PetStoreStepDefinitions {

    private Usuario usuario;

    @Given("que el servicio PetStore esta disponible")
    public void queElServicioPetStoreEstaDisponible() {
        usuario = new Usuario();
        usuario.setNombre("el tester");
        OnStage.setTheStage(OnlineCast.whereEveryoneCan(CallAnApi.at(BASE_URL)));
    }

    @When("se agrega una nueva mascota con nombre {string}, categoria {string}, photoUrl {string} y estado {string}")
    public void seAgregaUnaNuevaMascota(String nombre, String categoria, String photoUrl, String estado) {
        OnStage.theActorCalled(usuario.getNombre()).attemptsTo(
            AgregarMascota.conDatos(nombre, categoria, photoUrl, estado)
        );
    }

    @When("se buscan mascotas con estado {string}")
    public void seBuscanMascotasConEstado(String estado) {
        OnStage.theActorCalled(usuario.getNombre()).attemptsTo(
            BuscarMascotasPorEstado.conEstado(estado)
        );
    }

    @Then("la respuesta debe tener codigo de estado {int}")
    public void laRespuestaDebeTenerCodigoDeEstado(int codigoEsperado) {
        assertThat(OnStage.theActorCalled(usuario.getNombre())
            .asksFor(CodigoRespuestaApi.obtenido()))
            .isEqualTo(codigoEsperado);
    }

    @And("la mascota registrada debe tener nombre {string} y estado {string}")
    public void laMascotaRegistradaDebeTenerNombreYEstado(String nombre, String estado) {
        assertThat(lastResponse().jsonPath().getString("name"))
            .as("El nombre de la mascota registrada debe ser '%s'", nombre)
            .isEqualTo(nombre);
        assertThat(lastResponse().jsonPath().getString("status"))
            .as("El estado de la mascota registrada debe ser '%s'", estado)
            .isEqualTo(estado);
    }

    @And("la lista de mascotas no debe estar vacia")
    public void laListaDeMascotasNoDebeEstarVacia() {
        assertThat(lastResponse().jsonPath().getList("$"))
            .as("La lista de mascotas no debe estar vacía")
            .isNotEmpty();
    }

    @And("todas las mascotas deben tener estado {string}")
    public void todasLasMascotasDebenTenerEstado(String estado) {
        List<String> statuses = lastResponse().jsonPath().getList("status");
        assertThat(statuses)
            .as("La lista de estados no debe estar vacía")
            .isNotEmpty();
        statuses.forEach(s ->
            assertThat(s)
                .as("Todas las mascotas deben tener estado '%s', pero se encontró '%s'", estado, s)
                .isEqualTo(estado)
        );
    }
}
