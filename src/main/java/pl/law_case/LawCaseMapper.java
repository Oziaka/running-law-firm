package pl.law_case;

import pl.client.ClientMapper;
import pl.court.CourtMapper;
import pl.law_case.activity.ActivityMapper;
import pl.user.UserMapper;

import java.util.stream.Collectors;

public class LawCaseMapper {
   public static LawCase toEntity(LawCaseDto lawCaseDto) {
      return LawCase.builder()
         .id(lawCaseDto.getId())
         .court(CourtMapper.toEntity(lawCaseDto.getCourtDto()))
         .activities(lawCaseDto.getActivities().stream().map(ActivityMapper::toEntity).collect(Collectors.toList()))
         .clients(lawCaseDto.getClients().stream().map(ClientMapper::toEntity).collect(Collectors.toSet()))
         .signature(lawCaseDto.getSignature())
         .priority(lawCaseDto.getPriority())
         .workers(lawCaseDto.getWorkers().stream().map(UserMapper::toEntity).collect(Collectors.toSet()))
         .build();
   }


   public static LawCaseDto toDto(LawCase lawCase) {
      return LawCaseDto.builder()
         .id(lawCase.getId())
         .courtDto(CourtMapper.toDto(lawCase.getCourt()))
         .activities(lawCase.getActivities().stream().map(ActivityMapper::toDto).collect(Collectors.toList()))
         .clients(lawCase.getClients().stream().map(ClientMapper::toDto).collect(Collectors.toSet()))
         .signature(lawCase.getSignature())
         .priority(lawCase.getPriority())
         .workers(lawCase.getWorkers().stream().map(UserMapper::toDto).collect(Collectors.toSet()))
         .build();
   }
}
