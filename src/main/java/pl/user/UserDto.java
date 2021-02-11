package pl.user;


import lombok.*;
import org.hibernate.validator.constraints.Length;
import pl.address.AddressDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class UserDto {

   private Long id;

   @UniqueUserEmail(message = "Email is used by another account")
   @Email(message = "Must be a well-formed email address")
   @NotNull(message = "User must have email")
   private String email;

   @Length(min = 5, message = "Password length must be longer than 5")
   @NotNull(message = "User must havUe password")
   private String password;

   private String name;

   private String userName;

   private String surename;

   private AddressDto addressDto;

   @Builder
   public UserDto(Long id, @Email(message = "Must be a well-formed email address") @NotNull(message = "User must have email") String email, @Length(min = 5, message = "Password length must be longer than 5") @NotNull(message = "User must havUe password") String password, String name, String userName, String surename, AddressDto addressDto) {
      this.id = id;
      this.email = email;
      this.password = password;
      this.name = name;
      this.userName = userName;
      this.surename = surename;
      this.addressDto = addressDto;
   }
}
