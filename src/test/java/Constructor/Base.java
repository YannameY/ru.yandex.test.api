package Constructor;


import UserData.UserBuilder;

public class Base {
    public static final UserBuilder default_user = UserBuilder.builder()
            .id(0)
            .username("username")
            .firstName("name")
            .lastName("surname")
            .email("test@mail.ru")
            .password("password")
            .phone("89990001212")
            .userStatus(0)
            .build();
    }

