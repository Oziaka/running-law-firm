package pl.user_notification;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserNotificationRepository extends JpaRepository<UserNotification, Long> {

   List<UserNotification> findAll(Specification<UserNotification> userNotificationSpecification, Pageable pageable);

   Optional<UserNotification> getByUserEmailAndAndId(String email, Long userNotificationId);
}
