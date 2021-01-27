package pl.address;

import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class AddressDto {

   private Long id;

   private String street;

   private String city;

   private String postcode;

   private String country;

   @Builder
   public AddressDto(Long id, String street, String city, String postcode, String country) {
      this.id = id;
      this.street = street;
      this.city = city;
      this.postcode = postcode;
      this.country = country;
   }
}
