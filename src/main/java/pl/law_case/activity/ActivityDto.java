package pl.law_case.activity;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class ActivityDto {

   private Long id;

   private String title;

   private String description;

   private BigDecimal price;

   private LocalDateTime time;

   private boolean isDone;

   private boolean onInvoice;

   @Builder
   public ActivityDto(Long id, String title, String description, BigDecimal price, LocalDateTime time, boolean isDone, boolean onInvoice) {
      this.id = id;
      this.title = title;
      this.description = description;
      this.price = price;
      this.time = time;
      this.isDone = isDone;
      this.onInvoice = onInvoice;
   }
}
