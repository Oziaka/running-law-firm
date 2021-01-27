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
@Table(name = "file")
@ToString
public class Directory {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "directory_id")
   private Long id;

   @OneToMany(mappedBy = "directory")
   private List<File> files;


//   @OneToMany(mappedBy = "directory")
//   private List<Directory> directories;
//
//   @ManyToOne
//   private Directory directory;
}
