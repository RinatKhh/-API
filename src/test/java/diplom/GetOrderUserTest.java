package diplom;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.Matchers.equalTo;

public class GetOrderUserTest  {
    String accessToken = "";
    String email;
    User user;
    IngredientList ingredients = new IngredientList();

    @Before
    public void setUp(){
        user = User.getRandomUser();
        accessToken = UserMethod.createUser(user).then().extract().path("accessToken");
        email = user.getEmail();
        ingredients.setIngredients(OrderMethod.getIngredients());
        OrderMethod.createOrder(accessToken,ingredients);
    }
    @After
    public void teardown() {
        UserMethod.deleteUser(accessToken);
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
