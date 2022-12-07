package diplom;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class SignInUserTest{
    String accessToken = "";
    String email;
    String password;
    User user;
    @Before
    public void setUp(){
        user = User.getRandomUser();
        accessToken = UserMethod.createUser(user).then().extract().path("accessToken");
        email = user.getEmail();
        password = user.getPassword();
    }
    @After
    public void teardown() {
        UserMethod.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Check user sigin")
    @Description("Basic test to check access to the system")
    public void checkUserCanSigInTrue() {
        user = new User(email,password);
        UserMethod.sigInUser(user)
                 .then()
                 .assertThat()
                 .body("success", equalTo(true))
                 .statusCode(200);
    }
    @Test
    @DisplayName("Checking with incorrect login and password return Error  ")
    @Description("the system will return an error if the login and password is incorrect")
    public void checkWithIncorrectEmailPasswordReturnError() {
        User user_error = new User("test"+email,"test"+password);
        UserMethod.sigInUser(user_error)
                .then()
                .assertThat()
                .body("message", equalTo("email or password are incorrect"))
                .statusCode(401);
    }

}
