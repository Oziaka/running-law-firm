package pl.address;

public class AddressBuilderMaker {
   public static Address.AddressBuilder toAddressBuilder(Address address) {
      return Address.builder()
         .id(address.getId())
         .street(address.getStreet())
         .city(address.getCity())
         .postcode(address.getPostcode())
         .country(address.getCountry());
   }

   public static AddressDto.AddressDtoBuilder toAddressDtoBuilder(AddressDto addressDto) {
      return AddressDto.builder()
         .id(addressDto.getId())
         .street(addressDto.getStreet())
         .city(addressDto.getCity())
         .postcode(addressDto.getPostcode())
         .country(addressDto.getCountry());
   }
}
