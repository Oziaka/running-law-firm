package pl.address;

import pl.tool.RandomUtils;

public class AddressRandomTool {
   public static Address.AddressBuilder addressBuilder() {
      return Address.builder()
         .city(RandomUtils.randomString())
         .country(RandomUtils.randomString())
         .postcode(RandomUtils.randomString())
         .street(RandomUtils.randomString());
   }
   public static  AddressDto.AddressDtoBuilder addressDtoBuilder() {
      return AddressDto.builder()
         .city(RandomUtils.randomString())
         .country(RandomUtils.randomString())
         .postcode(RandomUtils.randomString())
         .street(RandomUtils.randomString());
   }


}
