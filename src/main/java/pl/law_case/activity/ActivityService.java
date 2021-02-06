package pl.law_case.activity;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.exception.DataNotFoundExeption;
import pl.law_case.LawCase;
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
      LawCase lawCase = lawCaseService.getLawCase(user, lawCaseId);
      activity.setLawCase(lawCase);
      Activity savedActivity = activityRepository.save(activity);
      return ActivityMapper.toDto(savedActivity);
   }

   public ActivityDto editActivity(Principal principal, ActivityDto activityNewValue, Long lawCaseId, Long activityId) {
      User user = userService.getUser(principal);
      Activity activityWithNewvalue = ActivityMapper.toEntity(activityNewValue);
      Activity activity = getActivity(lawCaseId, activityId, user);
      updatedNoNullFields(activity, activityWithNewvalue);
      Activity savedActivity = activityRepository.save(activity);
      return ActivityMapper.toDto(savedActivity);
   }

   private void updatedNoNullFields(Activity activity, Activity activityWithNewvalue) {
      if (activityWithNewvalue.getTitle() != null)
         activity.setTitle(activityWithNewvalue.getTitle());
      if (activityWithNewvalue.getDescription() != null)
         activity.setDescription(activityWithNewvalue.getDescription());
      if (activityWithNewvalue.getPrice() != null)
         activity.setPrice(activityWithNewvalue.getPrice());
   }

   private Activity getActivity(Long lawCaseId, Long activityId, User user) {
      return activityRepository.getActivity(lawCaseId, activityId, user).orElseThrow(() -> new DataNotFoundExeption("Can not found activity"));
   }
}
