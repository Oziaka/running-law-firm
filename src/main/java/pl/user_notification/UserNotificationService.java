package pl.user_notification;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import pl.exception.ThereIsNoYourPropertyException;
import pl.user.User;
import pl.user.UserProvider;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserNotificationService {
   private UserNotificationRepository userNotificationRepository;
   private UserProvider userProvider;

   List<UserNotificationDto> getNotifications(Principal principal, Pageable pageable, Specification<UserNotification> userNotificationSpecification) {
      User user = userProvider.getUser(principal);
//      userNotificationSpecification.and(new IsUserNotification(user));
      return getAll(pageable, userNotificationSpecification).stream().map(UserNotificationMapper::toDto).collect(Collectors.toList());
   }

   UserNotificationDto updateStatus(Principal principal, Long userNotificationId, Status newStatus) {
      UserNotification userNotification = getOne(principal.getName(), userNotificationId);
      userNotification.setStatus(newStatus);
      UserNotification savedUserNotification = save(userNotification);
      return UserNotificationMapper.toDto(savedUserNotification);
   }

   private List<UserNotification> getAll(Pageable pageable, Specification<UserNotification> userNotificationSpecification) {
      return userNotificationRepository.findAll(userNotificationSpecification, pageable);
   }

   private UserNotification getOne(String email, Long userNotificationId) {
      return userNotificationRepository.getByUserEmailAndAndId(email, userNotificationId).orElseThrow(ThereIsNoYourPropertyException::new);
   }

   public UserNotification save(UserNotification userNotification) {
      return userNotificationRepository.save(userNotification);
   }
}
