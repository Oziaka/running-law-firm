package pl.law_case.activity;


import lombok.*;
import pl.law_case.LawCase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "activity")
@ToString
public class Activity {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "activity_id")
   private Long id;

   @ManyToOne
   private LawCase lawCase;

   private String title;

   @Column(length = 10000)
   private String description;

   private BigDecimal price;

   private LocalDateTime time;

   private boolean isDone;

   private boolean onInvoice;

   @Builder
   public Activity(Long id, LawCase lawCase, String title, String description, BigDecimal price, LocalDateTime time, boolean isDone, boolean onInvoice) {
      this.id = id;
      this.lawCase = lawCase;
      this.title = title;
      this.description = description;
      this.price = price;
      this.time = time;
      this.isDone = isDone;
      this.onInvoice = onInvoice;
   }
}
