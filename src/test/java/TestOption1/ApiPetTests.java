package TestOption1;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


@Epic("Тестирование API")
public class ApiPetTests {

    private static final String BaseURI = "https://petstore.swagger.io/v2/";

    @Test
    @Feature("POST Запрос")
    @Description("Этот тест проверяет добавление нового питомца в магазин petstore")
    public void methodPostTests() {

        String bodyJson = """
                {
                 "id": 1,
                 "category": {
                 "id": 0,
                 "name": "string" },
                 "name": "doggie",
                 "photoUrls": [
                 "string" ],
                 "tags": [{
                 "id": 0,
                 "name": "string"}],
                 "status": "available"
                }""";

        given()
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .filter(new AllureRestAssured())
                .log().all()
                .body(bodyJson)
                .when().post(BaseURI + "pet")
                .then().log().all()
                .statusCode(200)

                .body("id", equalTo(1))
                .body("category.id", equalTo(0))
                .body("category.name", not(emptyOrNullString()))
                .body("name", not(emptyOrNullString()))
                .body("photoUrls", anyOf(empty(), not(empty())))
                .body("tags", anyOf(empty(), not(empty())))
                .body("status", not(emptyOrNullString()));

    }

    @Test
    @Feature("GET Запрос")
    @Description("Этот тест проверяет получение питомца по его id petstore.")
    public void methodGetTests() {
        methodPostTests();

        given()
                .header("accept", "application/json")
                .filter(new AllureRestAssured())
                .log().all()
                .when().get(BaseURI + "pet/1")
                .then().log().all()
                .statusCode(200)

                .body("id", equalTo(1))
                .body("category.id", not(emptyOrNullString()))
                .body("category.name", not(emptyOrNullString()))
                .body("name", not(emptyOrNullString()))
                .body("photoUrls", anyOf(empty(), not(empty())))
                .body("tags", anyOf(empty(), not(empty())))
                .body("status", not(emptyOrNullString()));

    }

    @Test
    @Feature("GET Запрос")
    @Description("Этот тест проверяет получение питомца по его id petstore.")
    public void methodGetAvailableTests() {


        given()
                .header("accept", "application/json")
                .queryParam("status", "available")
                .filter(new AllureRestAssured())
                .log().all()
                .when().get(BaseURI + "pet/findByStatus")
                .then().log().all()
                .statusCode(200)

                .body("status", not(emptyOrNullString()));

    }



    @Test
    @Feature("PUT Запрос")
    @Description("Этот тест проверяет обновление id существующего питомца")
    public void methodPutTests() {


        String bodyJson = """
                {
                 "id": 0,
                 "category": {
                 "id": 0,
                 "name": "string" },
                 "name": "doggie",
                 "photoUrls": [
                 "string" ],
                 "tags": [{
                 "id": 0,
                 "name": "string"}],
                 "status": "available"
                }""";

        given()
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .filter(new AllureRestAssured())
                .log().all()
                .body(bodyJson)
                .when().put(BaseURI + "pet")
                .then().log().all()
                .statusCode(200)

                .body("id", not(emptyOrNullString()))
                .body("category.id", equalTo(0))
                .body("category.name", not(emptyOrNullString()))
                .body("name", not(emptyOrNullString()))
                .body("photoUrls", anyOf(empty(), not(empty())))
                .body("tags", anyOf(empty(), not(empty())))
                .body("status", not(emptyOrNullString()));

    }

    @Test
    @Feature("DELETE Запрос")
    @Description("Этот тест проверяет удаление питомца из магазина petstore.")
    public void methodDeleteTests() {
        methodPostTests();

        given()
                .header("accept", "application/json")
                .filter(new AllureRestAssured())
                .log().all()
                .when().delete(BaseURI + "pet/1")
                .then().log().all()
                .statusCode(200)

                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("1"));

    }
}