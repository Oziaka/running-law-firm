package pl.address;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.exception.DataNotFoundExeption;
import pl.user.UserService;

import java.security.Principal;

@Service
@AllArgsConstructor
public class AddressService {

   private AddressRepository addressRepository;
   private UserService userService;

   public Address getAddress(Long id) {
      return addressRepository.findById(id).orElseThrow(() -> new DataNotFoundExeption("Address nor found"));
   }

   public AddressDto addAddress(Principal principal, AddressDto addressDto) {
      userService.getUser(principal);
      Address address = AddressMapper.toEntity(addressDto);
      Address savedAddress = addressRepository.save(address);
      return AddressMapper.toDto(savedAddress);
   }

   public AddressDto getAddress(Principal principal, Long addressId) {
      userService.getUser(principal);
      return AddressMapper.toDto(getAddress(addressId));
   }
}
