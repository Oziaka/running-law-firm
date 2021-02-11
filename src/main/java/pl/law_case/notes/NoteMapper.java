package pl.law_case.notes;

import pl.law_case.LawCaseMapper;

public class NoteMapper {

   public static Note toEntity(NoteDto noteDto) {
      return Note.builder()
         .id(noteDto.getId())
         .title(noteDto.getTitle())
         .createdDate(noteDto.getTime())
         .text(noteDto.getText())
         .lawCase(LawCaseMapper.toEntity(noteDto.getLawCaseDto()))
         .build();
   }

   public static NoteDto toDto(Note note) {
      return NoteDto.builder()
         .id(note.getId())
         .title(note.getTitle())
         .time(note.getCreatedDate())
         .text(note.getText())
         .lawCaseDto(LawCaseMapper.toDto(note.getLawCase()))
         .build();
   }
}
