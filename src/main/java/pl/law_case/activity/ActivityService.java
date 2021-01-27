package pl.law_case.activity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.law_case.LawCaseService;
import pl.user.User;
import pl.user.UserService;

import java.security.Principal;

@Service
@AllArgsConstructor
public class ActivityService {

   private ActivityRepository activityRepository;
   private UserService userService;
   private LawCaseService lawCaseService;

   public ActivityDto addActivity(Principal principal, ActivityDto activityDto, Long lawCaseId) {
      User user = userService.getUser(principal);
      Activity activity = ActivityMapper.toEntity(activityDto);

      return null;
   }
}
