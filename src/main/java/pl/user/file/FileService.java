package pl.user.file;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.user.User;
import pl.user.UserService;
import pl.user.directory.Directory;
import pl.user.directory.DirectoryDto;
import pl.user.directory.DirectoryService;

import java.io.IOException;
import java.security.Principal;

@AllArgsConstructor
@Service
public class FileService {

   private FileRepository fileRepository;
   private DirectoryService directoryService;
   private UserService userService;

   public FileDto addFile(Principal principal, MultipartFile multipartFile, Long directoryId) {
      User user = userService.getUser(principal);
      Directory directory = directoryService.getDirectory(user,directoryId);
      File file = null;
      try {
         file = File.builder().name(multipartFile.getName()).content(multipartFile.getBytes()).directory(directory).build();
      } catch (IOException e) {
         e.printStackTrace();
      }
      File savedFile = fileRepository.save(file);

      return null;
   }
}
