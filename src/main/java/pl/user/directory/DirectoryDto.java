package pl.user.directory;

import pl.user.file.File;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

public class DirectoryDto {

   private Long id;

   private List<File> files;

   private List<Directory> directories;

   private DirectoryDto directoryDto;
}
