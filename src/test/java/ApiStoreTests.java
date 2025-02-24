
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiStoreTests {
    private static final String BaseURI = "https://petstore.swagger.io/v2/";

    @Test
    @Feature("POST Запрос")
    @Description("Этот тест проверят оформление заказа на покупку питомца")
    public void methodPostTests() {

        String bodyJson = """
                {
                  "id": 1,
                  "petId": 1,
                  "quantity": 1,
                  "shipDate": "2025-02-22T20:53:50.314Z",
                  "status": "placed",
                  "complete": true
                }""";

        ValidatableResponse response = given()
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .log().all()
                .body(bodyJson)
                .when().post(BaseURI + "store/order")
                .then().log().all();
        response.statusCode(200)


                .body("id", equalTo(1))
                .body("petId", equalTo(1))
                .body("quantity", equalTo(1))
                .body("shipDate", not(emptyOrNullString()))
                .body("status", equalTo("placed"))
                .body("complete", equalTo(true));


    }


    @Test
    @Feature("GET Запрос")
    @Description("Этот тест проверят поиск заказа на покупку по идентификатору")
    public void methodGetTests() {
        methodPostTests();

        ValidatableResponse response = given()
                .header("accept", "application/json")
                .log().all()
                .when().get(BaseURI + "store/order/1")
                .then().log().all();
        response.statusCode(200)


                .body("id", equalTo(1))
                .body("petId", equalTo(1))
                .body("quantity", equalTo(1))
                .body("shipDate", not(emptyOrNullString()))
                .body("status", equalTo("placed"))
                .body("complete", equalTo(true));
    }


    @Test
    @Feature("GET Запрос")
    @Description("Этот тест возвращает инвентарь питомцев по статусу")
    public void methodGetInventoryTests() {


        ValidatableResponse response = given()
                .header("accept", "application/json")
                .log().all()
                .when().get(BaseURI + "store/inventory")
                .then().log().all();
        response.statusCode(200);


    }



    @Test
    @Feature("DELETE Запрос")
    @Description("Этот тест проверят удаление заказа на покупку по идентификатору")
    public void methodDeleteTests() {
        methodPostTests();

        ValidatableResponse response = given()
                .header("accept", "application/json")
                .log().all()
                .when().delete(BaseURI + "store/order/1")
                .then().log().all();
        response.statusCode(200)


                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("1"));

    }

}