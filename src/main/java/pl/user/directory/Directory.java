package pl.user.directory;

import lombok.*;
import pl.user.file.File;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "directory")
@ToString
public class Directory {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "directory_id")
   private Long id;

//   @OneToMany(mappedBy = "directory")
//   private List<File> files;

   private String name;

   @Builder
   public Directory(Long id, List<File> files, String name) {
      this.id = id;
//      this.files = files;
      this.name = name;
   }
}
