import UserData.User;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;



import static Constructor.Base.default_user;


public class UserApiTests {
@Feature("apiTests")
    @Nested
    class ApiUserTests {
    User user = new User();

    @Test
    void createUser() {
        Response response = user.createUser(default_user);

        Assertions.assertEquals(200, response.statusCode());
    }
}
}
