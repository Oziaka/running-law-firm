package pl.address;

public class AddressMapper {
   public static Address toEntity(AddressDto addressDto) {
      return Address.builder()
         .id(addressDto.getId())
         .street(addressDto.getStreet())
         .city(addressDto.getCity())
         .postcode(addressDto.getPostcode())
         .country(addressDto.getCountry())
         .build();
   }

   public static AddressDto toDto(Address address) {
      return AddressDto.builder()
         .id(address.getId())
         .street(address.getStreet())
         .city(address.getCity())
         .postcode(address.getPostcode())
         .country(address.getCountry())
         .build();
   }
}
