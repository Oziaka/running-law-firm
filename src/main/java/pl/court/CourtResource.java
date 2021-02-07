package pl.court;

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
@RequestMapping("/court")
public class CourtResource {

   private CourtService courtService;

   @PostMapping("/address/{addressId}")
   public ResponseEntity<CourtDto> addCourt(Principal principal, @RequestBody CourtDto courtDto, @PathVariable Long addressId) {
      return ResponseEntity.status(HttpStatus.CREATED).body(courtService.addCourt(principal, courtDto, addressId));
   }

   @GetMapping("/{courtId}")
   public ResponseEntity<CourtDto> getCourt(Principal principal, Long courtId) {
      return ResponseEntity.ok(courtService.getCourt(principal, courtId));
   }

}
