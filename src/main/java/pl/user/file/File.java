package pl.user.file;

import lombok.*;
import pl.user.directory.Directory;

import javax.persistence.*;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "file")
@ToString
public class File {

   @Lob
   byte[] content;
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "file_id")
   private Long id;
   private String name;
   @ManyToOne
   private Directory directory;

   @Builder
   public File(Long id, String name, Directory directory, byte[] content) {
      this.id = id;
      this.name = name;
      this.directory = directory;
      this.content = content;
   }
}
