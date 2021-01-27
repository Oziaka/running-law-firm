package pl.law_case.notes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.law_case.LawCase;
import pl.law_case.LawCaseDto;
import pl.law_case.LawCaseService;
import pl.user.User;
import pl.user.UserService;

import java.security.Principal;

@AllArgsConstructor
@Service
public class NoteService {

   private NoteRepository noteRepository;
   private UserService userService;
   private LawCaseService lawCaseService;

   public NoteDto addNote(Principal principal, NoteDto noteDto, Long lawCaseId) {
      User user = userService.getUser(principal);
      Note note = NoteMapper.toEntity(noteDto);
      LawCase lawCase = lawCaseService.getLawCase(user, lawCaseId);
      note.setLawCase(lawCase);
      Note savedNote = noteRepository.save(note);
      return NoteMapper.toDto(savedNote);
   }
}
