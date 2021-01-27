package pl.user.directory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.exception.DataNotFoundExeption;
import pl.user.User;

@AllArgsConstructor
@Service
public class DirectoryService {

   private DirectoryRepository directoryRepository;

   public Directory getDirectory(User user, Long directoryId) {
      return directoryRepository.getDirectory(user,directoryId).orElseThrow(()-> new DataNotFoundExeption("Directory not found"));
   }
}
