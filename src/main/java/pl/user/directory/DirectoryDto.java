package pl.user.directory;

import lombok.Builder;
import pl.user.file.File;

import java.util.List;

public class DirectoryDto {

   private Long id;

   private List<File> files;

   private List<Directory> directories;

   private DirectoryDto directoryDto;

   @Builder
   public DirectoryDto(Long id, List<File> files, List<Directory> directories, DirectoryDto directoryDto) {
      this.id = id;
      this.files = files;
      this.directories = directories;
      this.directoryDto = directoryDto;
   }
}
