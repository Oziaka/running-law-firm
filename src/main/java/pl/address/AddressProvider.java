package pl.address;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.exception.DataNotFoundExeption;
import pl.user.User;

@Service
@AllArgsConstructor
public class AddressProvider {

   private AddressRepository addressRepository;

   public Address getAddress(User user, Long addressId) {
      return addressRepository.getAddressBelongsToWorkerCourt(user, addressId).orElseGet(() -> addressRepository.getUserAddress(user, addressId).orElseGet(() -> addressRepository.getUserClientAddress(user, addressId).orElseThrow(() -> new DataNotFoundExeption("Address not found exception"))));
   }

   public Address saveAddress(Address address) {
      return addressRepository.save(address);
   }
}
