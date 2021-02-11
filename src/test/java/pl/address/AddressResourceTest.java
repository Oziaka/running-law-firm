package pl.address;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import pl.tool.RandomUtils;
import pl.user.UserRandomTool;
import pl.user.User;
import pl.user.UserProvider;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

class AddressResourceTest {

   private AddressRepository addressRepository;
   private AddressService addressService;
   private UserProvider userProvider;
   private AddressResource addressResource;

   @BeforeEach
   void init() {
      addressRepository = Mockito.mock(AddressRepository.class);
      userProvider = Mockito.mock(UserProvider.class);
      addressService = new AddressService(addressRepository, userProvider);
      addressResource = new AddressResource(addressService);
   }

   @Test
   void addAddressReturnAddressDtoWhenAddressIsWellPreformedAndUserIsRegistered() {
      // given
      AddressDto addressDto = AddressRandomTool.addressDtoBuilder().build();
      User user = UserRandomTool.randomUser();
      // when
      Long addressId = RandomUtils.randomLong();
      Mockito.when(userProvider.getUser(any())).thenReturn(user);
      Mockito.when(addressRepository.save(any())).thenAnswer(invocation -> {
         Address address = invocation.getArgument(0, Address.class);
         address.setId(addressId);
         return address;
      });
      ResponseEntity<AddressDto> addAddressResponseEntity = addressResource.addAddress(user::getName, addressDto);
      // then
      AddressDto expectedAddressDto = AddressDto.builder().id(addressId).city(addressDto.getCity()).country(addressDto.getCountry()).postcode(addressDto.getPostcode()).street(addressDto.getStreet()).build();
      Assertions.assertEquals(CREATED, addAddressResponseEntity.getStatusCode());
      Assertions.assertEquals(expectedAddressDto, addAddressResponseEntity.getBody());
   }

//   @Test
//   void getAddressReturnAddressDtoWhenAddressIsUserPropertyAndAddressIsExist() {
//      Long addressId = RandomUtils.randomLong();
//      Address address = AddressRandomTool.addressBuilder().id(addressId).build();
//      User user = UserRandomTool.randomUser();
//      // when
//      Mockito.when(userProvider.getUser(any())).thenReturn(user);
//      Mockito.when(addressRepository.findById(any())).thenReturn(Optional.of(address));
//      ResponseEntity<AddressDto> getAddressResponseEntity = addressResource.getAddress(user::getName, addressId);
//      // then
//      AddressDto expectedAddressDto = AddressDto.builder().id(address.getId()).city(address.getCity()).country(address.getCountry()).postcode(address.getPostcode()).street(address.getStreet()).build();
//      Assertions.assertEquals(OK, getAddressResponseEntity.getStatusCode());
//      Assertions.assertEquals(expectedAddressDto, getAddressResponseEntity.getBody());
//   }

   @Test
   void removeAddressReturnOnlyStatusWhenAddressBelongsToUserOrHisClientsOrCourtAddressWhichBelongsToUser() {
      // given
      Long addressId = RandomUtils.randomLong();
      Address address = AddressRandomTool.addressBuilder().id(addressId).build();
      User user = UserRandomTool.randomUser();
      // when
      Mockito.when(userProvider.getUser(any())).thenReturn(user);
      Mockito.when(addressRepository.getUserAddress(any(), any())).thenReturn(Optional.of(address));
      ResponseEntity removeAddressResponseEntity = addressResource.deleteAddress(user::getName, addressId);
      // then
      Assertions.assertEquals(OK, removeAddressResponseEntity.getStatusCode());
   }
}
