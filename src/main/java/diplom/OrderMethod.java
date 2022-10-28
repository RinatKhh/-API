package diplom;

import io.restassured.response.Response;

public class OrderMethod extends ApiSetting {
    // создать заказы пользователя
    public static Response createOrder(String accessToken, String ingredients) {
        return getSpec().given().auth()
                .oauth2(getAccessToken(accessToken))
                .body(ingredients)
                .when()
                .post(ORDER);
    }
    // получить заказы пользователя
    public static Response getOrderUser(String accessToken) {
        return getSpec().given().auth()
                .oauth2(getAccessToken(accessToken))
                .when()
                .get(ORDER);
    }
    // получить случайно ингредиент из общего списка
    public static String getIngredients() {
        Ingredients ingredients = getSpec().given()
                .get(INGREDIENTS)
                .body().as(Ingredients.class);
        int max = ingredients.getData().size()-1;
        int i = (int) (Math.random() * ++max);
        return  ingredients.getData().get(i).get_id();
    }
}
