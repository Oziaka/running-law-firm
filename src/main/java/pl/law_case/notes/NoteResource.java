package pl.law_case.notes;

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
@RequestMapping("law_case/{lawCaseId}/law_case")
public class NoteResource {

   private NoteService noteService;

   @GetMapping("/add")
   public ResponseEntity<NoteDto> addNotes(Principal principal, @RequestBody NoteDto noteDto, @PathVariable Long lawCaseId) {
      return ResponseEntity.status(HttpStatus.CREATED).body(noteService.addNote(principal, noteDto, lawCaseId));
   }
}
