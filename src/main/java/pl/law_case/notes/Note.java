package pl.law_case.notes;

import lombok.*;
import pl.law_case.LawCase;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "notes")
@ToString
public class Note {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "law_case_id")
   private Long id;

   private String title;

   @Column(length = 10000)
   private String text;

   @ManyToOne
   private LawCase lawCase;

   private LocalDateTime time;

   @Builder
   public Note(Long id, String title, String text, LawCase lawCase, LocalDateTime time) {
      this.id = id;
      this.title = title;
      this.text = text;
      this.lawCase = lawCase;
      this.time = time;
   }
}
