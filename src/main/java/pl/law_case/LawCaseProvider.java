package pl.law_case;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.exception.DataNotFoundExeption;
import pl.user.User;
import pl.user.UserProvider;

import java.security.Principal;

@AllArgsConstructor
@Service
public class LawCaseProvider {

   private LawCaseRepository lawCaseRepository;
   private UserProvider userProvider;

   public LawCase getLawCase(User user, Long lawCaseId) {
      return lawCaseRepository.getLawCase(user, lawCaseId).orElseThrow(() -> new DataNotFoundExeption("Law case not found"));
   }

   public LawCaseDto getLawCase(Principal principal, Long lawCaseId) {
      User user = userProvider.getUser(principal);
      LawCase lawCase = this.getLawCase(user, lawCaseId);
      return LawCaseMapper.toDto(lawCase);
   }
}
