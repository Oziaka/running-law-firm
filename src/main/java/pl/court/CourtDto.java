package pl.court;

import lombok.*;
import pl.address.Address;
import pl.address.AddressDto;

import javax.persistence.MapsId;
import javax.persistence.OneToOne;
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class CourtDto {
   private Long id;

   private String name;

   private AddressDto addressDto;

   @Builder
   public CourtDto(Long id, String name, AddressDto addressDto) {
      this.id = id;
      this.name = name;
      this.addressDto = addressDto;
   }
}
