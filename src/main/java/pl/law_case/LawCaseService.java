package pl.law_case;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.exception.DataNotFoundExeption;
import pl.user.User;
import pl.user.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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

   public LawCase getLawCase(User user, Long lawCaseId) {
      return lawCaseRepository.getLawCase(user, lawCaseId).orElseThrow(() -> new DataNotFoundExeption("Law case not found"));
   }

   public LawCaseDto getLawCase(Principal principal, Long lawCaseId) {
      User user = userService.getUser(principal);
      LawCase lawCase = this.getLawCase(user, lawCaseId);
      return LawCaseMapper.toDto(lawCase);
   }

   public List<LawCaseDto> getLawCases(Principal principal) {
      User user = userService.getUser(principal);
      List<LawCase> lawCases = lawCaseRepository.getAll(user);
      return lawCases.stream().map(LawCaseMapper::toDto).collect(Collectors.toList());
   }
}
