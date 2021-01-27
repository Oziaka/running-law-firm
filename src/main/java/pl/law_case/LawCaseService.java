package pl.law_case;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.user.User;
import pl.user.UserService;

import java.security.Principal;

@Service
@AllArgsConstructor
public class LawCaseService {

   private LawCaseRepository lawCaseRepository;
   private UserService userService;

   public LawCaseDto addLawCase(Principal principal, LawCaseDto lawCaseDto) {
      User worker = userService.getUser(principal);
      LawCase lawCase = LawCaseMapper.toEntity(lawCaseDto);
      lawCase.addWorker(worker);
      LawCase savedLawCase = lawCaseRepository.save(lawCase);
      return LawCaseMapper.toDto(savedLawCase);
   }
}
