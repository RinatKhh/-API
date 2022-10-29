package diplom;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest {
    User user;
    String accessToken = "";

    @Before
     public void setUp(){
        user = User.getRandomUser();
    }

    @After
    public void teardown() {
        if (accessToken!=null) {
            UserMethod.deleteUser(accessToken);
        }
    }
    @Test
    @DisplayName("Check create new User")
    @Description("Basic test for create User")
    public void createNewUserAndReturnTrue(){
        Response response =UserMethod.createUser(user);
        accessToken = response.then().extract().path("accessToken");
        response.then().log().all().assertThat()
        .body("success", equalTo(true))
        .statusCode(200);

    }
    // нельзя создать двух одинаковых курьеров;
    @Test
    @DisplayName("Check create double User return Error")
    @Description("Basic test for create double User ")
    public void createDoubleUserAndReturnError() {
        accessToken = UserMethod.createUser(user).then().extract().path("accessToken");
        Response response = UserMethod.createUser(user);
        response.then().log().all()
                .assertThat()
                .body("message", equalTo("User already exists"))
                .statusCode(403);

    }

    // без email
    @Test
    @DisplayName("Check create  User without email return Error")
    @Description("Basic test for create User ")
    public void createNewUserWithoutEmailReturnError() {
        user = User.getWithoutEmailUser();
        Response response = UserMethod.createUser(user);
        accessToken = response.then().extract().path("accessToken");
        response.then().log().all()
                .assertThat()
                .body("message", equalTo("Email, password and name are required fields"))
                .statusCode(403);
    }
    //без пароля
    @Test
    @DisplayName("Check create  User without password return Error")
    @Description("Basic test for create d User ")
    public void createNewUserWithoutPasswordReturnError() {
        user = User.getWithoutPasswordUser();
        Response response = UserMethod.createUser(user);
        accessToken = response.then().extract().path("accessToken");
        response.then().log().all()
                .assertThat()
                .body("message", equalTo("Email, password and name are required fields"))
                .statusCode(403);
    }
    //без имени
    @Test
    @DisplayName("Check create User  without name return Error")
    @Description("Basic test for create  User ")
    public void createNewUserWithoutNameReturnError() {
        user = User.getWithoutNameUser();
        Response response =UserMethod.createUser(user);
        accessToken = response.then().extract().path("accessToken");
        response.then().log().all()
                .assertThat()
                .body("message", equalTo("Email, password and name are required fields"))
                .statusCode(403);
    }
}
