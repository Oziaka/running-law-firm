package pl.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.exception.DataNotFoundExeption;

import java.security.Principal;

@Service
@AllArgsConstructor
public class UserProvider {

   private UserRepository userRepository;

   public User save(User user) {
      return userRepository.save(user);
   }

   private User getUser(String email) {
      return userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundExeption("User not found"));
   }

   public User getUser(Principal principal) {
      return getUser(principal.getName());
   }
}
