package pl.client;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.address.Address;
import pl.address.AddressProvider;
import pl.exception.DataNotFoundExeption;
import pl.user.User;
import pl.user.UserProvider;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ClientService {

   private ClientRepository clientRepository;
   private UserProvider userProvider;
   private AddressProvider addressProvider;

   CLientDto addClient(CLientDto cLientDto, Principal principal) {
      User user = userProvider.getUser(principal);
      Client client = ClientMapper.toEntity(cLientDto);
      Address address = addressProvider.getAddress(user, cLientDto.getAddressId());
      client.setAddress(address);
      Client savedClient = clientRepository.save(client);
      return ClientMapper.toDto(savedClient);
   }

   CLientDto getClient(Principal principal, Long clientId) {
      Client client = getClient(userProvider.getUser(principal), clientId);
      return ClientMapper.toDto(client);
   }

   private Client getClient(User user, Long cliendId) {
      return clientRepository.findClient(cliendId, user).orElseThrow(() -> new DataNotFoundExeption("Nie znaleziono klienta"));
   }

   List<CLientDto> getAllClients(Principal principal) {
      return getAllClients(userProvider.getUser(principal)).stream().map(ClientMapper::toDto).collect(Collectors.toList());
   }

   private List<Client> getAllClients(User worker) {
      return clientRepository.findAllClients(worker);
   }
}
