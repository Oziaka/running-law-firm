package pl.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.user.directory.DirectoryService;
import pl.user_role.UserRole;
import pl.user_role.UserRoleService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.HttpStatus.CREATED;

class UserResourceTest {
   private UserRepository userRepository;
   private UserRoleService userRoleService;
   private DirectoryService directoryService;
   private UserService userService;
   private PasswordEncoder passwordEncoder;
   private UserResource userResource;
   private final List<UserRole> defaultUserRoles = List.of(new UserRole("ROLE_USER", "Default role"));


   @BeforeEach
   void init() {
      userRepository = Mockito.mock(UserRepository.class);
      userRoleService = Mockito.mock(UserRoleService.class);
      passwordEncoder = Mockito.mock(PasswordEncoder.class);
      directoryService = Mockito.mock(DirectoryService.class);
      userService = new UserService(userRepository, userRoleService, passwordEncoder,directoryService);
      userResource = new UserResource(userService);
   }

   @Test
   void registerReturnUserDtoWhenRegisteredSuccessful() {
      // given
      UserDto userDto = UserRandomTool.randomUserDto();
      // when
      Mockito.when(passwordEncoder.encode(any())).thenReturn(userDto.getPassword());
      Mockito.when(userRoleService.getDefaults()).thenReturn(new ArrayList<>(defaultUserRoles));
      User user = User.builder().email(userDto.getEmail()).password(userDto.getPassword()).build();
      user.setId(1L);
      Mockito.when(userRepository.save(any())).thenReturn(user);
      ResponseEntity<UserDto> registeredUserResponseEntity = userResource.register(userDto);
      // then
      UserDto excpectedUserDto = UserDto.builder().email(userDto.getEmail()).userName(userDto.getUserName()).build();
      Assertions.assertEquals(registeredUserResponseEntity.getStatusCode(), CREATED);
      Assertions.assertEquals(registeredUserResponseEntity.getBody(), excpectedUserDto);
   }

   @Test
   void editUserReturnEditedUserWhenAllFieldsValid() {
      // given
      User user = UserRandomTool.randomUser();
      user.setPassword(null);
      UserDto userWithUpdatedFields = UserRandomTool.randomUserDto();
      // when
      Mockito.when(userRepository.findByEmail(any())).thenReturn(Optional.empty(), Optional.of(user));
      Mockito.when(userRepository.save(any())).thenAnswer(invocation -> {
         User userToSave = invocation.getArgument(0, User.class);
         userToSave.setPassword(null);
         return userToSave;
      });
      Principal principal = user::getEmail;
      ResponseEntity<UserDto> updatedUserResponseEntity = userResource.editUser(principal, userWithUpdatedFields);
      // then
      UserDto expectedUpdatedUser = UserDto.builder().email(userWithUpdatedFields.getEmail()).items(userWithUpdatedFields.getItems()).build();
      Assertions.assertEquals(updatedUserResponseEntity.getStatusCode(), HttpStatus.OK);
      Assertions.assertEquals(updatedUserResponseEntity.getBody(), expectedUpdatedUser);
   }
}
