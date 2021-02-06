package pl.user;

import pl.tool.RandomUtils;

import static pl.tool.RandomUtils.randomString;

public class UserRandomTool {

   public static UserDto randomUserDto() {
      return randomUserDtoBuilder().build();
   }
   public static User randomUser() {
      return randomUserBuilder().build();
   }

   public static UserDto.UserDtoBuilder randomUserDtoBuilder() {
      return UserDto.builder()
         .email(randomEmaiL())
         .password(randomString());
   }
   public static User.UserBuilder randomUserBuilder() {
      return User.builder()
         .email(randomEmaiL())
         .password(randomString());
   }

   private static String randomEmaiL() {
      return randomString() + RandomUtils.randomString(3) + '@' + RandomUtils.randomString(2) + "." + RandomUtils.randomString(2);
   }
}
