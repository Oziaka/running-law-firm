package pl.user;


import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.Map;

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

   private String userName;

   @Builder
   public UserDto(Long id, @Email(message = "Must be a well-formed email address") @NotNull(message = "User must have email") String email, @Length(min = 5, message = "Password length must be longer than 5") @NotNull(message = "User must havUe password") String password, String userName) {
      this.id = id;
      this.email = email;
      this.password = password;
      this.userName = userName;
   }
}
