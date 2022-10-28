package diplom;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;

public class GetOrderUserTest extends UserMethod {
    String accessToken = "";
    String email;
    User user;
    ArrayList<String> ingredients = new ArrayList<>();
    @Before
    public void setUp(){
        user = User.getRandomUser();
        accessToken = createUser(user).then().extract().path("accessToken");
        email = user.getEmail();
        OrderMethod.createOrder(accessToken,"{\"ingredients\": \""+OrderMethod.getIngredients()+"\"}");
    }
    @After
    public void teardown() {
        DeleteUser(accessToken);
    }

    @Test
    @DisplayName("Check get  order user with Authorization")
    @Description("Basic test for get order")
    public void GetOrderWithAuthorizationTrue(){
        OrderMethod.getOrderUser(accessToken)
                .then().log().all().assertThat()
                .body("success", equalTo(true))
                .statusCode(200);
    }
    @Test
    @DisplayName("Check get  order user without Authorization")
    @Description("Basic test for get order")
    public void GetOrderWithoutAuthorizationFalse(){
        OrderMethod.getOrderUser("")
                .then().log().all().assertThat()
                .body("success", equalTo(false))
                .statusCode(401);
    }
}
