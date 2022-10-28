package diplom;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiSetting {
    // ручки для тестирования
    static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    static final String REGISTER = "api/auth/register";
    static final String DELETE = "api/auth/user";
    static final String SIGNIN = "api/auth/login";
    static final String EDITUSER = "api/auth/user";
    static final String SUBSTRING = "Bearer ";
    static final String ORDER= "api/orders";
    static final String INGREDIENTS = "api/ingredients";
    protected static RequestSpecification getSpec() {
        return given()
                .header("Content-Type", "application/json")
                .baseUri(BASE_URL);
    }
    // вынимаем токен из строки
    protected static String getAccessToken(String token) {
        return token.replace(SUBSTRING,"");
    }
}
