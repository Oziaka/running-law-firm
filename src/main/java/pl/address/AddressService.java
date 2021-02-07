package pl.address;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.exception.DataNotFoundExeption;
import pl.user.User;
import pl.user.UserProvider;

import java.security.Principal;

@Service
@AllArgsConstructor
public class AddressService {

   private AddressRepository addressRepository;
   private UserProvider userProvider;

   Address getAddress(User user, Long addressId) {
      return addressRepository.getAddressBelongsToWorkerCourt(user, addressId).orElseGet(() -> addressRepository.getUserAddress(user, addressId).orElseGet(() -> addressRepository.getUserClientAddress(user, addressId).orElseThrow(() -> new DataNotFoundExeption("Address not found exception"))));
   }

   public AddressDto addAddress(Principal principal, AddressDto addressDto) {
      userProvider.getUser(principal);
      Address address = AddressMapper.toEntity(addressDto);
      Address savedAddress = addressRepository.save(address);
      return AddressMapper.toDto(savedAddress);
   }

   public AddressDto getAddress(Principal principal, Long addressId) {
      User user = userProvider.getUser(principal);
      return AddressMapper.toDto(getAddress(user, addressId));
   }

   public void removeAddress(Principal principal, Long addressId) {
      User user = userProvider.getUser(principal);
      Address address = getAddress(user, addressId);
      addressRepository.delete(address);
   }
}
