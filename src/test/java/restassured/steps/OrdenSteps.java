package restassured.steps;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OrdenSteps {

    private Response response;
    private Map<String, Object> requestBody;
    private int orderId;

    @Given("una orden con petId {int}, quantity {int} y status {string}")
    public void unaOrdenConDatos(int petId, int quantity, String status) {
        requestBody = new HashMap<>();
        requestBody.put("petId", petId);
        requestBody.put("quantity", quantity);
        requestBody.put("status", status);
        requestBody.put("complete", true);
    }

    @When("envío la solicitud de creación de la orden")
    public void envioLaSolicitudDeCreacionDeLaOrden() {
        response = given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/store/order");

        orderId = response.jsonPath().getInt("id");
    }

    @Then("la respuesta del POST debe tener status code {int}")
    public void laRespuestaDelPOSTDebeTenerStatusCode(int expectedCode) {
        response.then().statusCode(expectedCode);
    }

    @Then("el cuerpo debe contener un id no nulo y el status {string}")
    public void elCuerpoDebeContenerUnIdNoNuloYStatus(String expectedStatus) {
        assertThat(orderId, notNullValue());
        assertThat(response.jsonPath().getString("status"), equalTo(expectedStatus));
    }

    @When("consulto la orden creada por su id")
    public void consultoLaOrdenCreadaPorSuId() {
        response = given()
                .baseUri("https://petstore.swagger.io/v2")
                .when()
                .get("/store/order/" + orderId);
    }

    @Then("la respuesta del GET debe tener status code {int}")
    public void laRespuestaDelGETDebeTenerStatusCode(int expectedCode) {
        response.then().statusCode(expectedCode);
    }

    @Then("el cuerpo del GET debe contener el mismo id y status {string}")
    public void elCuerpoDelGETDebeContenerElMismoIdYStatus(String expectedStatus) {
        assertThat(response.jsonPath().getInt("id"), equalTo(orderId));
        assertThat(response.jsonPath().getString("status"), equalTo(expectedStatus));
    }
}
