package pl.law_case;

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
@RequestMapping("/law_case")
public class LawCaseResource {

   private LawCaseService lawCaseService;

   @PostMapping("/add")
   public ResponseEntity<LawCaseDto> addLawCase(Principal principal, @RequestBody LawCaseDto lawCaseDto) {
      return ResponseEntity.status(HttpStatus.CREATED).body(lawCaseService.addLawCase(principal, lawCaseDto));
   }

   @GetMapping("/{lawCaseId}")
   public ResponseEntity<LawCaseDto> getLawCase(Principal principal, @PathVariable Long lawCaseId) {
      return ResponseEntity.ok(lawCaseService.getLawCase(principal, lawCaseId));
   }

   @GetMapping
   public ResponseEntity<List<LawCaseDto>> getLawCases(Principal principal) {
      return ResponseEntity.ok(lawCaseService.getLawCases(principal));
   }
}
