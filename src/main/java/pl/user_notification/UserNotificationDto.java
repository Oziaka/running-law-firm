package pl.user_notification;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@EqualsAndHashCode
public class UserNotificationDto {

   private Long id;

   private Status status;

   private String tittle;

   private String description;

   private LocalDateTime dateOfAdding;

   private Map<String, String> items;

   @Builder
   public UserNotificationDto(Long id, Status status, String tittle, String description, LocalDateTime dateOfAdding, Map<String, String> items) {
      this.id = id;
      this.status = status;
      this.tittle = tittle;
      this.description = description;
      this.dateOfAdding = dateOfAdding;
      this.items = items;
   }
}
