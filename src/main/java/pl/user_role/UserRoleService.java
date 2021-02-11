package pl.user_role;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.exception.DataNotFoundExeption;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserRoleService {

   private UserRoleRepository userRoleRepository;

   List<UserRoleDto> getAllRoles() {
      return this.getAll().stream().map(UserRoleMapper::toDto).collect(Collectors.toList());
   }

   UserRoleDto updateRole(UserRoleDto userRoleDto, Long userRoleId) {
      UserRole role = this.getOne(userRoleId);
      role.setDescription(userRoleDto.getDescription());
      return UserRoleMapper.toDto(this.save(role));
   }

   List<UserRole> getDefaults() {
      return userRoleRepository.getDefaultRoles();
   }

   List<UserRole> getAll() {
      return userRoleRepository.findAll();
   }

   UserRole getOne(Long userRoleId) {
      return userRoleRepository.findById(userRoleId).orElseThrow(() -> new DataNotFoundExeption("User role not found"));
   }

   private UserRole save(UserRole userRole) {
      return userRoleRepository.save(userRole);
   }
}
