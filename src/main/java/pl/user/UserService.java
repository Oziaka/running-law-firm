package pl.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.address.Address;
import pl.address.AddressProvider;
import pl.exception.DataNotFoundExeption;
import pl.exception.NewEntityCanNotHaveIdException;
import pl.user.directory.Directory;
import pl.user.directory.DirectoryProvider;
import pl.user_role.UserRole;
import pl.user_role.UserRoleProvider;

import java.security.Principal;

import static pl.user.directory.DirectoryFinalValue.DEFALUT_USER_FOLDER_NAME;

@Service
@AllArgsConstructor
public class UserService {

   private UserRepository userRepository;
   private UserRoleProvider userRoleProvider;
   private PasswordEncoder passwordEncoder;
   private DirectoryProvider directoryProvider;
   private AddressProvider addressProvider;

   UserDto addUserWithDefaultsResources(UserDto userDto) {
      if (userDto.getId() != null)
         throw new NewEntityCanNotHaveIdException("New user can not have id");
      User user = UserMapper.toEntity(userDto);
      encodePassword(user);
      addDefaultRoles(user);
      addDefaultDirectory(user);
      addDefaultAddress(user);
      User savedUser = userRepository.save(user);
      return UserMapper.toDtoForSelf(savedUser);
   }

   private void addDefaultAddress(User user) {
      Address address = Address.builder().build();
      Address savedAddress = addressProvider.saveaAddress(address);
      user.setAddress(savedAddress);
   }


   private void addDefaultDirectory(User user) {
      Directory mainDirectory = Directory.builder()
         .name(DEFALUT_USER_FOLDER_NAME)
         .build();
      Directory savedDirectory = directoryProvider.saveDirectory(mainDirectory);
      user.setDirectory(mainDirectory);
   }

   private void addDefaultRoles(User user) {
      userRoleProvider.getDefaults().forEach(user::addRole);
   }

   private void encodePassword(User user) {
      user.setPassword(passwordEncoder.encode(user.getPassword()));
   }

   UserDto getProfile(Principal principal) {
      return UserMapper.toDtoForSelf(this.getUser(principal));
   }

   UserDto editUser(Principal principal, UserDto userDto) {
      User user = this.getUser(principal);
      User updatedUserValue = UserMapper.toEntity(userDto);
      User updatedUser = updateUserFromNotNullFieldsInUserDto(user, updatedUserValue);
      User savedUser = userRepository.save(updatedUser);
      return UserMapper.toDtoForSelf(savedUser);
   }

   public UserDto grantPermission(Long userRoleId, String email) {
      User user = this.getUser(() -> email);
      UserRole userRole = userRoleProvider.getOne(userRoleId);
      user.addRole(userRole);
      User save = userRepository.save(user);
      return UserMapper.toDtoWithRoles(save);
   }

   public UserDto revokePermission(Long userRoleId, String email) {
      User user = this.getUser(() -> email);
      UserRole userRole = userRoleProvider.getOne(userRoleId);
      user.removeRole(userRole);
      User save = userRepository.save(user);
      return UserMapper.toDtoWithRoles(save);
   }

   private User updateUserFromNotNullFieldsInUserDto(User user, User updatedUserValue) {
      if (updatedUserValue.getEmail() != null && isUserHasUniqueEmail(updatedUserValue.getEmail()))
         user.setEmail(updatedUserValue.getEmail());
      if (updatedUserValue.getPassword() != null)
         user.setPassword(updatedUserValue.getPassword());
      if (updatedUserValue.getName() != null)
         user.setName(updatedUserValue.getName());
      if (updatedUserValue.getSurename() != null)
         user.setName(updatedUserValue.getName());
      return user;
   }

   private Boolean isUserHasUniqueEmail(String email) {
      return !this.userRepository.findByEmail(email).isPresent();
   }

   private User getUser(String email) {
      return userRepository.findByEmail(email).orElseThrow(() -> new DataNotFoundExeption("User not found"));
   }

   User getUser(Principal principal) {
      return getUser(principal.getName());
   }

   boolean emailIsUsed(String email) {
      return userRepository.findByEmail(email).isPresent();
   }
}
