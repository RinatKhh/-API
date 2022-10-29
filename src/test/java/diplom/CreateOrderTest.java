package diplom;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.equalTo;


public class CreateOrderTest {

    String accessToken = "";
    String email;
    User user;
    ArrayList<String> ingredients = new ArrayList<>();
    @Before
    public void setUp(){
        user = User.getRandomUser();
        accessToken = UserMethod.createUser(user).then().extract().path("accessToken");
        email = user.getEmail();
    }
    @After
    public void teardown() {
        UserMethod.deleteUser(accessToken);
    }
    @Test
    @DisplayName("Check create new Order with Authorization And Ingredients")
    @Description("Basic test for create Oder")
    public void createNewOrderWithAuthorizationAndIngredientsTrue(){
        OrderMethod.createOrder(accessToken,"{\"ingredients\": \""+ OrderMethod.getIngredients()+"\"}")
                .then().log().all().assertThat()
                .body("success", equalTo(true))
                .statusCode(200);
    }
    @Test
    @DisplayName("Check create new Order with Authorization And incorrect ingredients")
    @Description("Basic test for create Oder")
    public void createNewOrderWithAuthorizationAndIncorrectIngredientsFalse(){
        ingredients.add("01test");
        OrderMethod.createOrder(accessToken,"{\"ingredients\": \""+OrderMethod.getIngredients()+"error\"}")
                .then().log().all().assertThat()
                .statusCode(500);
    }
    @Test
    @DisplayName("Check create new Order with Authorization And Not ingredients")
    @Description("Basic test for create Oder")
    public void createNewOrderWithAuthorizationAndNotIngredientsFalse(){
        OrderMethod.createOrder(accessToken,"{}")
                .then().log().all().assertThat()
                .statusCode(400);
    }
    @Test
    @DisplayName("Check create new Order without Authorization And Ingredients")
    @Description("Basic test for create Oder")
    public void createNewOrderWithoutAuthorizationAndIngredientsTrue(){
        OrderMethod.createOrder("","{\"ingredients\": \""+OrderMethod.getIngredients()+"\"}")
                .then().log().all().assertThat()
                .body("success", equalTo(true))
                .statusCode(200);
    }
    @Test
    @DisplayName("Check create new Order without And incorrect ingredients")
    @Description("Basic test for create Oder")
    public void createNewOrderWithoutAuthorizationAndIncorrectIngredientsFalse(){
        OrderMethod.createOrder("","{\"ingredients\": \""+OrderMethod.getIngredients()+"error\"}")
                .then().log().all().assertThat()
                .statusCode(500);
    }
    @Test
    @DisplayName("Check create new Order without Authorization And Not ingredients")
    @Description("Basic test for create Oder")
    public void createNewOrderWithoutAuthorizationAndNotIngredientsFalse(){
        OrderMethod.createOrder("","{}")
                .then().log().all().assertThat()
                .statusCode(400);
    }
}
