package pl.law_case.activity;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Validated
@RestController
@AllArgsConstructor
@CrossOrigin("${cors.allowed-origins}")
@RequestMapping("law_case/{lawCaseId}/activity")
public class ActivityResource {

   private ActivityService activityService;

   @PostMapping("/add")
   public ResponseEntity<ActivityDto> addActivity(Principal principal, @RequestBody ActivityDto activityDto, @PathVariable Long lawCaseId) {
      return ResponseEntity.status(HttpStatus.CREATED).body(activityService.addActivity(principal, activityDto, lawCaseId));
   }

   @PostMapping("{activityId}/edit")
   public ResponseEntity<ActivityDto> editActivity(Principal principal, @RequestBody ActivityDto activityDto, @PathVariable Long lawCaseId, @PathVariable Long activityId) {
      return ResponseEntity.ok(activityService.editActivity(principal, activityDto, lawCaseId, activityId));
   }
}
