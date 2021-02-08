package pl.user.directory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DirectoryProvider {

   private DirectoryRepository directoryRepository;

   public Directory saveDirectory(Directory directory) {
      return directoryRepository.save(directory);
   }
}
