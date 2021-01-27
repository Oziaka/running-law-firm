package pl.law_case.activity;

public class ActivityMapper {
   public static Activity toEntity(ActivityDto activityDto) {
      return Activity.builder()
         .id(activityDto.getId())
         .isDone(activityDto.isDone())
         .description(activityDto.getDescription())
         .onInvoice(activityDto.isOnInvoice())
         .time(activityDto.getTime())
         .title(activityDto.getTitle())
         .price(activityDto.getPrice())
         .build();
   }

   public static ActivityDto toDto(Activity activity) {
      return ActivityDto.builder()
         .id(activity.getId())
         .isDone(activity.isDone())
         .description(activity.getDescription())
         .onInvoice(activity.isOnInvoice())
         .time(activity.getTime())
         .title(activity.getTitle())
         .price(activity.getPrice())
         .build();
   }
}
