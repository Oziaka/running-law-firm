package pl.law_case;

import lombok.*;
import pl.client.CLientDto;
import pl.court.CourtDto;
import pl.law_case.activity.ActivityDto;
import pl.user.UserDto;

import javax.validation.constraints.Null;
import java.util.List;
import java.util.Set;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class LawCaseDto {

   private Long id;

   private Set<UserDto> workers;

   private Set<CLientDto> clients;

   private List<ActivityDto> activities;

   private CourtDto courtDto;

   private String signature;

   private Priority priority;

   @Builder
   public LawCaseDto(Long id, Set<UserDto> workers, Set<CLientDto> clients, List<ActivityDto> activities, CourtDto courtDto, String signature, Priority priority) {
      this.id = id;
      this.workers = workers;
      this.clients = clients;
      this.activities = activities;
      this.courtDto = courtDto;
      this.signature = signature;
      this.priority = priority;
   }
}
