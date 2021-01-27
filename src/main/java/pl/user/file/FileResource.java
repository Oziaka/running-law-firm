package pl.user.file;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Validated
@RestController
@AllArgsConstructor
@CrossOrigin("${cors.allowed-origins}")
@RequestMapping("/directory/{directoryId}")
public class FileResource {

   private FileService fileService;

   @PostMapping("/add")
   public ResponseEntity<FileDto> addFileDto(Principal principal, MultipartFile multipartFile, @PathVariable Long directoryId){
      return ResponseEntity.status(HttpStatus.CREATED).body(fileService.addFile(principal,multipartFile,directoryId));
   }
}
