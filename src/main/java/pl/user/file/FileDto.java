package pl.user.file;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;
import pl.user.directory.Directory;

public class FileDto {

   MultipartFile content;
   private Long id;
   private String name;
   private Directory directory;

   @Builder
   public FileDto(Long id, String name, Directory directory, MultipartFile content) {
      this.id = id;
      this.name = name;
      this.directory = directory;
      this.content = content;
   }
}
