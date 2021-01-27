package pl.user.file;

import pl.user.directory.Directory;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;

public class FileDto {

   private Long id;

   private String name;

   private Directory directory;

   byte[] content;


}
