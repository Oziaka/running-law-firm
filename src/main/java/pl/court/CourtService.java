package pl.court;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.address.Address;
import pl.address.AddressService;
import pl.exception.DataNotFoundExeption;
import pl.user.UserService;

import java.security.Principal;

@AllArgsConstructor
@Service
public class CourtService {

   private UserService userService;
   private CourtRepository courtRepository;
   private AddressService addressService;

   public CourtDto addCourt(Principal principal, CourtDto courtDto, Long addressId) {
      userService.getUser(principal);
      Address address = addressService.getAddress(addressId);
      Court court = CourtMapper.toEntity(courtDto);
      court.setAddress(address);
      Court savedCourt = courtRepository.save(court);
      return CourtMapper.toDto(savedCourt);
   }

   public CourtDto getCourt(Principal principal, Long courtId) {
      userService.getUser(principal);
      Court court = courtRepository.findById(courtId).orElseThrow(() -> new DataNotFoundExeption("Court not found"));
      return CourtMapper.toDto(court);
   }



}
