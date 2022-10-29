package diplom;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class ChangeDataUserTest{
    String accessToken = "";
    String email;
    String name;
    User user;
    @Before
    public void setUp(){
        user = User.getRandomUser();
        accessToken = UserMethod.createUser(user).then().extract().path("accessToken");
        email = user.getEmail();
        name = user.getName();
    }
    @After
    public void teardown() {
        UserMethod.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Check change user data with authorization")
    public void checkChangeUserDataWithAuthorizationTrue(){
        user = new User(email,name);
        UserMethod.changDataUser(accessToken,user)
                .then().log().all()
                .assertThat()
                .body("success", equalTo(true))
                .statusCode(200);
    }
    @Test
    @DisplayName("Check change user data without authorization")
    public void checkChangeUserDataWithoutAuthorizationFalse(){
        user = new User("new"+email,name);
        UserMethod.changDataUser("",user)
                .then().log().all()
                .assertThat()
                .body("success", equalTo(false))
                .statusCode(401);
    }

}

