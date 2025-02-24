package TestOption1;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.parsing.Parser;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiUserTests {

    private static final String BaseURI = "https://petstore.swagger.io/v2/";



    @Test
    @Feature("POST Запрос")
    @Description("Этот тест проверят создание пользователя")
    public void methodPostTests() {

        String bodyJson = """
                [
                  {
                    "id": 0,
                    "username": "string",
                    "firstName": "string",
                    "lastName": "string",
                    "email": "string",
                    "password": "string",
                    "phone": "string",
                    "userStatus": 0
                  }
                ]""";



        ValidatableResponse response = given()
                .filter(new AllureRestAssured())
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .log().all()
                .body(bodyJson)
                .when().post(BaseURI + "user/createWithList")
                .then().log().all();
        response.statusCode(200)


                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("ok"));


    }

    @Test
    @Feature("GET Запрос")
    @Description("Этот тест проверят получение пользователя по его id")
    public void methodGetTests() {


        ValidatableResponse response = given()
                .filter(new AllureRestAssured())
                .header("accept", "application/json")
                .log().all()
                .when().get(BaseURI + "user/string")
                .then().log().all();
        response.statusCode(200)


                .body("id", not(emptyOrNullString()))
                .body("username", not(emptyOrNullString()))
                .body("firstName", not(emptyOrNullString()))
                .body("lastName", not(emptyOrNullString()))
                .body("email", not(emptyOrNullString()))
                .body("password", not(emptyOrNullString()))
                .body("phone", not(emptyOrNullString()))
                .body("userStatus", equalTo(0));


    }


    @Test
    @Feature("PUT Запрос")
    @Description("Этот тест проверят получение пользователя по его id")
    public void methodPutTests() {

        String bodyJson = """
                {
                  "id": 1,
                  "username": "string",
                  "firstName": "string",
                  "lastName": "string",
                  "email": "string",
                  "password": "string",
                  "phone": "string",
                  "userStatus": 0
                }""";


        ValidatableResponse response = given()
                .filter(new AllureRestAssured())
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .log().all()
                .body(bodyJson)
                .when().put(BaseURI + "user/1")
                .then().log().all();
        response.statusCode(200)


                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", not(emptyOrNullString()));


    }

    @Test
    @Feature("DELETE Запрос")
    @Description("Этот тест проверят удаление пользователя по его id")
    public void methodDeleteTests() {


        ValidatableResponse response = given()
                .filter(new AllureRestAssured())
                .header("accept", "application/json")
                .header("content-type", "application/json")
                .log().all()
                .when().delete(BaseURI + "user/string")
                .then().log().all();
        response.statusCode(200)


                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", not(emptyOrNullString()));

    }

    @Test
    @Feature("DELETE Запрос")
    @Description("Этот тест проверят регистрацию пользователя в магазине petstore(но запрос не должен быть get, и параметры должны передаваться в теле запроса)")
    public void methodGetLoginTests() {


        ValidatableResponse response = given()
                .filter(new AllureRestAssured())
                .header("accept", "application/json")
                .queryParam("username", "admin")
                .queryParam("password", "admin")
                .log().all()
                .when().get(BaseURI + "user/login")
                .then().log().all();
        response.statusCode(200)


                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", not(emptyOrNullString()));

    }


    @Test
    @Feature("GET Запрос")
    @Description("Этот тест проверят выход из аккаунта пользователя в магазине petstore")
    public void methodGetLogoutTests() {


        ValidatableResponse response = given()
                .filter(new AllureRestAssured())
                .header("accept", "application/json")
                .log().all()
                .when().get(BaseURI + "user/logout")
                .then().log().all();
        response.statusCode(200)

                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("ok"));

    }
}

















