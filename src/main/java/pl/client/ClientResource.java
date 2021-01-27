package pl.client;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Validated
@RestController
@AllArgsConstructor
@CrossOrigin("${cors.allowed-origins}")
@RequestMapping("/client")
public class ClientResource {

   private ClientService clientService;

   @PostMapping("/add")
   public ResponseEntity<CLientDto> addClient(Principal principal, @RequestBody CLientDto cLientDto) {
      return ResponseEntity.status(HttpStatus.CREATED).body(clientService.addClient(cLientDto, principal));
   }

   @GetMapping("/{clientId}")
   public ResponseEntity<CLientDto> getClient(Principal principal, @PathVariable Long clientId) {
      return ResponseEntity.ok(clientService.getClient(principal, clientId));
   }

   @GetMapping
   public ResponseEntity<List<CLientDto>> getAllClients(Principal principal){
      return ResponseEntity.ok(clientService.getAllClients(principal));
   }
}
