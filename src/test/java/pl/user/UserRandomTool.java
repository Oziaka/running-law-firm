package pl.user;

import pl.address.AddressRandomTool;

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
         .password(randomString())
         .addressDto(AddressRandomTool.addressDtoBuilder().build())
         .surename(randomString())
         .name(randomString())
         .userName(randomString());
   }

   public static User.UserBuilder randomUserBuilder() {
      return User.builder()
         .email(randomEmaiL())
         .password(randomString())
         .address(AddressRandomTool.addressBuilder().build())
         .surename(randomString())
         .name(randomString())
         .userName(randomString());

   }

   private static String randomEmaiL() {
      return randomString() + '@' + randomString(2) + "." + randomString(2);
   }
}
