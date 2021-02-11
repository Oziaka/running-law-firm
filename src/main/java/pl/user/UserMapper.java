package pl.user;


import pl.address.AddressMapper;

public class UserMapper {

   private UserMapper() {
   }

   public static UserDto toDtoForSelf(User user) {
      return UserDto.builder()
         .id(user.getId())
         .email(user.getEmail())
         .userName(user.getUserName())
         .name(user.getName())
         .surename(user.getSurename())
         .addressDto(AddressMapper.toDto(user.getAddress()))
         .build();
   }


   public static User toEntity(UserDto userDto) {
      return User.builder()
         .id(userDto.getId())
         .email(userDto.getEmail())
         .password(userDto.getPassword())
         .name(userDto.getName())
         .surename(userDto.getSurename())
         .userName(userDto.getUserName())
         .address(AddressMapper.toEntity(userDto.getAddressDto()))
         .build();
   }

   public static UserDto toDto(User user) {
      if (user.getEmail() != null)
         return UserDto.builder()
            .email(user.getEmail())
            .name(user.getName())
            .surename(user.getSurename())
            .build();
      else
         return UserDto.builder()
            .email(user.getEmail())
            .build();
   }


   public static UserDto toDtoWithRoles(User user) {
      return UserDto.builder()
         .email(user.getEmail())
         .build();
   }
}
