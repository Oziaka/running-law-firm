package pl.user_role;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.exception.DataNotFoundExeption;

import java.util.List;

@AllArgsConstructor
@Service
public class UserRoleProvider {

   private UserRoleRepository userRoleRepository;

   public List<UserRole> getDefaults() {
      return userRoleRepository.getDefaultRoles();
   }

   public UserRole getOne(Long userRoleId) {
      return userRoleRepository.findById(userRoleId).orElseThrow(() -> new DataNotFoundExeption("User role not found"));
   }
}
