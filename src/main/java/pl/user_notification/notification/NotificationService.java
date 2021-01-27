package pl.user_notification.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationService {
   private NotificationRepository notificationRepository;

   public Notification save(Notification notification) {
      return notificationRepository.save(notification);
   }
}
