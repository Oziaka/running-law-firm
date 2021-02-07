package pl.court;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.address.Address;
import pl.address.AddressProvider;
import pl.exception.DataNotFoundExeption;
import pl.user.User;
import pl.user.UserProvider;

import java.security.Principal;

@AllArgsConstructor
@Service
public class CourtService {

   private UserProvider userProvider;
   private CourtRepository courtRepository;
   private AddressProvider addressProvider;

    CourtDto addCourt(Principal principal, CourtDto courtDto, Long addressId) {
      User user = userProvider.getUser(principal);
      Address address = addressProvider.getAddress(user, addressId);
      Court court = CourtMapper.toEntity(courtDto);
      court.setAddress(address);
      Court savedCourt = courtRepository.save(court);
      return CourtMapper.toDto(savedCourt);
   }

    CourtDto getCourt(Principal principal, Long courtId) {
      userProvider.getUser(principal);
      Court court = courtRepository.findById(courtId).orElseThrow(() -> new DataNotFoundExeption("Court not found"));
      return CourtMapper.toDto(court);
   }


}
