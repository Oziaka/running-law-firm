package pl.law_case.notes;

import lombok.*;
import pl.law_case.LawCase;
import pl.law_case.LawCaseDto;

import java.time.LocalDateTime;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class NoteDto {
   private Long id;

   private String title;

   private String text;

   private LawCaseDto lawCaseDto;

   private LocalDateTime time;

   @Builder
   public NoteDto(Long id, String title, String text, LawCaseDto lawCaseDto, LocalDateTime time) {
      this.id = id;
      this.title = title;
      this.text = text;
      this.lawCaseDto = lawCaseDto;
      this.time = time;
   }
}
