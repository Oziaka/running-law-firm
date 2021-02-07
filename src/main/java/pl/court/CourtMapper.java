package pl.court;

import pl.address.AddressMapper;

public class CourtMapper {
   public static Court toEntity(CourtDto courtDto) {
      return Court.builder()
         .id(courtDto.getId())
         .name(courtDto.getName())
         .address(AddressMapper.toEntity(courtDto.getAddressDto()))
         .build();
   }

   public static CourtDto toDto(Court court) {
      return CourtDto.builder()
         .id(court.getId())
         .name(court.getName())
         .addressDto(AddressMapper.toDto(court.getAddress()))
         .build();
   }

}
