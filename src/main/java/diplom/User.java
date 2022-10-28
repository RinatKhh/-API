package diplom;

import org.apache.commons.lang3.RandomStringUtils;
public class User {
        private String email;

        private String password;

        private String name;

        public User(String email, String password, String name) {

            this.email = email;

            this.password = password;

            this.name = name;

        }
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
        public static User getRandomUser() {

            return new User(
                    RandomStringUtils.randomAlphanumeric(10) + "@test.com",
                    "P@ssw0rd",
                    RandomStringUtils.randomAlphabetic(10)
            );
        }
       public static User getWithoutEmailUser() {

           return new User(
                   "",
                   "P@ssw0rd",
                   RandomStringUtils.randomAlphabetic(10)
           );
       }
        public static User getWithoutPasswordUser() {

            return new User(
                    RandomStringUtils.randomAlphanumeric(10) + "@test.com",
                    "",
                    RandomStringUtils.randomAlphabetic(10)
            );
        }
        public static User getWithoutNameUser() {

             return new User(
                     RandomStringUtils.randomAlphanumeric(10) + "@test.com",
                     "P@ssw0rd",
                     ""
             );

        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }


        public String getPassword() {

            return password;

        }

        public void setPassword(String password) {

            this.password = password;

        }

        public String getName() {

            return name;

        }

        public void setName(String name) {

            this.name = name;

        }
    }
