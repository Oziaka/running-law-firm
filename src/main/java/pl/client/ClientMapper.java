package pl.client;

import pl.address.AddressMapper;

public class ClientMapper {
   public static Client toEntity(CLientDto cLientDto) {
      return Client.builder()
         .id(cLientDto.getId())
         .address(AddressMapper.toEntity(cLientDto.getAddressDto()))
         .email(cLientDto.getEmail())
         .phoneNumber(cLientDto.getPhoneNumber())
         .password(cLientDto.getPassword())
         .surename(cLientDto.getSurename())
         .name(cLientDto.getName())
         .build();
   }

   public static CLientDto toDto(Client client) {
      return CLientDto.builder()
         .id(client.getId())
         .addressDto(AddressMapper.toDto(client.getAddress()))
         .email(client.getEmail())
         .phoneNumber(client.getPhoneNumber())
         .password(client.getPassword())
         .surename(client.getSurename())
         .name(client.getName())
         .build();
   }
}
