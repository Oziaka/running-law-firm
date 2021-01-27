package pl.law_case;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.user.User;

import java.util.List;
import java.util.Optional;

public interface LawCaseRepository extends JpaRepository<LawCase, Long> {

   @Query("SELECT lc FROM LawCase lc join lc.workers w WHERE lc.id = :lawCaseId AND w = :user")
   Optional<LawCase> getLawCase(User user, Long lawCaseId);

   @Query("SELECT lc FROM LawCase  lc join  lc.workers w WHERE w = :user")
   List<LawCase> getAll(@Param("user")User user);
}
