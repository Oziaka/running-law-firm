package pl.user;

import pl.address.AddressMapper;

public class UserBuilderMaker {

   public static User.UserBuilder toUserBuilder(User user) {
      return User.builder()
         .id(user.getId())
         .email(user.getEmail())
         .password(user.getPassword())
         .name(user.getName())
         .surename(user.getSurename())
         .userName(user.getUserName())
         .address(user.getAddress());
   }


   public static UserDto.UserDtoBuilder toUserDtoBuilder(UserDto userDto) {
      return UserDto.builder()
         .id(userDto.getId())
         .email(userDto.getEmail())
         .password(userDto.getPassword())
         .name(userDto.getName())
         .surename(userDto.getSurename())
         .userName(userDto.getUserName())
         .addressDto(userDto.getAddressDto());
   }
}
