package diplom;

import io.restassured.response.Response;

import java.util.Collections;
import java.util.List;

public class OrderMethod extends ApiSetting {
    // создать заказы пользователя
    public static Response createOrder(String accessToken, IngredientList ingredients) {
        return getSpec().given().auth()
                .oauth2(getAccessToken(accessToken))
                .body(ingredients)
                .when()
                .post(ORDER);
    }
    // получить заказы пользователя
    public static Response getOrderUser(String accessToken) {
        return getSpec().given().log().all().auth()
                .oauth2(getAccessToken(accessToken))
                .when()
                .get(ORDER);
    }
    // получить случайно ингредиент из общего списка
    public static List<String> getIngredients() {
        Ingredients ingredients = getSpec().given()
                .get(INGREDIENTS)
                .body().as(Ingredients.class);
        int max = ingredients.getData().size()-1;
        int i = (int) (Math.random() * ++max);
        return Collections.singletonList(ingredients.getData().get(i).get_id());
    }
}
