package pl.law_case.notes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.law_case.LawCase;
import pl.law_case.LawCaseService;
import pl.user.User;
import pl.user.UserProvider;

import java.security.Principal;

@AllArgsConstructor
@Service
public class NoteService {

   private NoteRepository noteRepository;
   private UserProvider userProvider;
   private LawCaseService lawCaseService;

   public NoteDto addNote(Principal principal, NoteDto noteDto, Long lawCaseId) {
      User user = userProvider.getUser(principal);
      Note note = NoteMapper.toEntity(noteDto);
      LawCase lawCase = lawCaseService.getLawCase(user, lawCaseId);
      note.setLawCase(lawCase);
      Note savedNote = noteRepository.save(note);
      return NoteMapper.toDto(savedNote);
   }
}
