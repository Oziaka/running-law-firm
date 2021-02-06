package pl.law_case.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.user.User;

import java.util.Optional;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

   @Query("SELECT a FROM Activity a join a.lawCase lc join a.lawCase.workers w WHERE w = :user and :lowCaseId = lc.id and a.id = :id")
   Optional<Activity> getActivity(@Param("lowCaseId")Long lawCaseId, @Param("id")Long activityId,@Param("user") User user);
}
