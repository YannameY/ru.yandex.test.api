package UserData;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.qameta.allure.restassured.AllureRestAssured;

import static io.restassured.RestAssured.given;

public class User {

    RequestSpecification requestSpecification = given();
    private static final String BaseURI = "https://petstore.swagger.io/v2/";

    public User() {
        RestAssured.defaultParser = Parser.JSON;
        this.requestSpecification.contentType(ContentType.JSON);
        this.requestSpecification.accept(ContentType.JSON);
        this.requestSpecification.baseUri(BaseURI);
        this.requestSpecification.filter(new AllureRestAssured());

    }
    public Response createUser(UserBuilder user) {
        this.requestSpecification.body(user);
     return given(this.requestSpecification).post("user").andReturn();
    }
}
