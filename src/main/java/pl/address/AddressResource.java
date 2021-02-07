package pl.address;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Validated
@RestController
@AllArgsConstructor
@CrossOrigin("${cors.allowed-origins}")
@RequestMapping("/address")
public class AddressResource {

   private AddressService addressService;

   @PutMapping("/add")
   public ResponseEntity<AddressDto> addAddress(Principal principal, @RequestBody AddressDto addressDto) {
      return ResponseEntity.status(HttpStatus.CREATED).body(addressService.addAddress(principal, addressDto));
   }

   @GetMapping("/{addressId}")
   public ResponseEntity<AddressDto> getAddress(Principal principal, @PathVariable Long addressId) {
      return ResponseEntity.ok(addressService.getAddress(principal, addressId));
   }

   @DeleteMapping("/delete/{addressId}")
   public ResponseEntity deleteAddress(Principal principal, @PathVariable Long addressId) {
      addressService.removeAddress(principal, addressId);
      return ResponseEntity.ok().build();
   }
}
