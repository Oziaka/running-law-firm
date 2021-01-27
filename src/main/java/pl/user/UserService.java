package pl.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.exception.DataNotFoundExeption;
import pl.user_role.UserRole;
import pl.user_role.UserRoleService;

import java.security.Principal;

@Service
@AllArgsConstructor
public class UserService {

   private static final String DEFAULT_WALLET_NAME = "Wallet";

   private UserRepository userRepository;
   private UserRoleService userRoleService;
   private PasswordEncoder passwordEncoder;

   UserDto addUserWithDefaultsResources(UserDto userDto) {
      User user = UserMapper.toEntity(userDto);
      encodePassword(user);
      addDefaultRoles(user);
      user = this.saveUser(user);
      User savedUser = this.saveUser(user);
      return UserMapper.toDtoForSelf(savedUser);
   }

   private void addDefaultRoles(User user) {
      userRoleService.getDefaults().forEach(user::addRole);
   }

   private void encodePassword(User user) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
   }

   UserDto getProfile(Principal principal) {
      return UserMapper.toDtoForSelf(this.getUser(principal));
   }

   UserDto editUser(Principal principal, UserDto userDto) {
      if (!isUserHasUniqueEmail(userDto.getEmail()))
         throw new RuntimeException("User must have unique email");
      User user = this.getUser(principal);
      User updatedUser = updateUserFromNotNullFieldsInUserDto(user, userDto);
      User savedUser = this.saveUser(updatedUser);
      return UserMapper.toDtoForSelf(savedUser);
   }

   public UserDto grantPermission(Long userRoleId, String email) {
      User user = this.getUser(() -> email);
      UserRole userRole = userRoleService.getOne(userRoleId);
      user.addRole(userRole);
      User save = this.saveUser(user);
      return UserMapper.toDtoWithRoles(save);
   }

   public UserDto revokePermission(Long userRoleId, String email) {
      User user = this.getUser(() -> email);
      UserRole userRole = userRoleService.getOne(userRoleId);
      user.removeRole(userRole);
      User save = this.saveUser(user);
      return UserMapper.toDtoWithRoles(save);
   }

   private User updateUserFromNotNullFieldsInUserDto(User user, UserDto userDto) {
//      if (userDto.getUserName() != null)
//         user.set(userDto.getUserName());
      if (userDto.getEmail() != null)
         user.setEmail(userDto.getEmail());
      if (userDto.getPassword() != null)
         user.setPassword(userDto.getPassword());
      return user;
   }

   private Boolean isUserHasUniqueEmail(String email) {
      return !this.userRepository.findByEmail(email).isPresent();
   }

   private User getUser(String email) {
      return userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundExeption("User not found"));
   }

   public User getUser(Principal principal) {
      return getUser(principal.getName());
   }

   boolean emailIsUsed(String email) {
      return userRepository.findByEmail(email).isPresent();
   }

   public User saveUser(User user) {
      return userRepository.save(user);
   }
}
