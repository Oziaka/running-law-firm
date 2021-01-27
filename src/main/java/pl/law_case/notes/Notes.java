package pl.law_case.notes;

import lombok.*;
import pl.law_case.LawCase;

import javax.persistence.*;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "notes")
@ToString
public class Notes {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "law_case_id")
   private Long id;

   private String title;

   @Column(length = 10000)
   private String text;

   @ManyToOne
   private LawCase lawCase;
}
