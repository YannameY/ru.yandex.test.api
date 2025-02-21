import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import java.util.Collections;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

public class ApiPetTests {

    private static final String BaseURI = "https://petstore.swagger.io/v2/";

    @Test
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
    public void methodGetTests() {


        given()
                .header("accept", "application/json")
                .queryParam("page", "1")
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
    public void methodDeleteTests() {


        given()
                .header("accept", "application/json")
                .log().all()
                .when().delete(BaseURI + "pet/1")
                .then().log().all()
                .statusCode(200)

                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("1"));

    }
}