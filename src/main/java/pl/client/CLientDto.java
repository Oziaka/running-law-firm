package pl.client;

import lombok.*;
import pl.address.AddressDto;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class CLientDto {

   private Long id;

   private String email;

   private String password;

   private String name;

   private String surename;

   private String phoneNumber;

   private AddressDto addressDto;

   private Long addressId;

   @Builder
   public CLientDto(Long id, String email, String password, String name, String surename, String phoneNumber, AddressDto addressDto, Long addressId) {
      this.id = id;
      this.email = email;
      this.password = password;
      this.name = name;
      this.surename = surename;
      this.phoneNumber = phoneNumber;
      this.addressDto = addressDto;
      this.addressId = addressId;
   }
}
