package pl.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.address.Address;
import pl.address.AddressService;
import pl.exception.DataNotFoundExeption;
import pl.user.User;
import pl.user.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ClientService {

   private ClientRepository clientRepository;
   private UserService userService;
   private AddressService addressService;

   public CLientDto addClient(CLientDto cLientDto, Principal principal) {
      User user = userService.getUser(principal);
      Client client = ClientMapper.toEntity(cLientDto);
      Address address = addressService.getAddress(cLientDto.getAddressId());
      client.setAddress(address);
      Client savedClient = clientRepository.save(client);
      return ClientMapper.toDto(savedClient);
   }

   public CLientDto getClient(Principal principal, Long clientId) {
      Client client = getClient(userService.getUser(principal), clientId);
      return ClientMapper.toDto(client);
   }

   private Client getClient(User user, Long cliendId) {
      return clientRepository.findClient(cliendId, user).orElseThrow(() -> new DataNotFoundExeption("Nie znaleziono klienta"));
   }

   public List<CLientDto> getAllClients(Principal principal) {
      return getAllClients(userService.getUser(principal)).stream().map(ClientMapper::toDto).collect(Collectors.toList());
   }

   private List<Client> getAllClients(User worker) {
      return clientRepository.findAllClients(worker);
   }
}
