package pl.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.address.AddressProvider;
import pl.user.directory.DirectoryProvider;
import pl.user.directory.DirectoryService;
import pl.user_role.UserRole;
import pl.user_role.UserRoleProvider;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.HttpStatus.CREATED;

class UserResourceTest {
   private final List<UserRole> defaultUserRoles = List.of(new UserRole("ROLE_USER", "Default role"));
   private UserRepository userRepository;
   private UserRoleProvider userRoleProvider;
   private DirectoryProvider directoryProvider;
   private UserService userService;
   private PasswordEncoder passwordEncoder;
   private UserResource userResource;
   private AddressProvider addressProvider;

   @BeforeEach
   void init() {
      userRepository = Mockito.mock(UserRepository.class);
      userRoleProvider = Mockito.mock(UserRoleProvider.class);
      passwordEncoder = Mockito.mock(PasswordEncoder.class);
      directoryProvider = Mockito.mock(DirectoryProvider.class);
      userService = new UserService(userRepository, userRoleProvider, passwordEncoder, directoryProvider,addressProvider);
      userResource = new UserResource(userService);
   }

   @Test
   void registerReturnUserDtoWhenRegisteredSuccessful() {
      // given
      UserDto userDto = UserRandomTool.randomUserDto();
      // when
      Mockito.when(passwordEncoder.encode(any())).thenReturn(userDto.getPassword());
      Mockito.when(userRoleProvider.getDefaults()).thenReturn(new ArrayList<>(defaultUserRoles));
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
      User user = UserRandomTool.randomUserBuilder().password(null).build();
      UserDto userWithUpdatedFields = UserRandomTool.randomUserDto();
      // when
      Mockito.when(userRepository.findByEmail(any())).thenReturn(Optional.of(user),Optional.empty());
      Mockito.when(userRepository.save(any())).thenAnswer(invocation -> {
         User userToSave = invocation.getArgument(0, User.class);
         userToSave.setPassword(null);
         return userToSave;
      });
      Principal principal = user::getEmail;
      ResponseEntity<UserDto> updatedUserResponseEntity = userResource.editUser(principal, userWithUpdatedFields);
      // then
      UserDto expectedUpdatedUser = UserDto.builder().email(userWithUpdatedFields.getEmail()).build();
      Assertions.assertEquals(updatedUserResponseEntity.getStatusCode(), HttpStatus.OK);
      Assertions.assertEquals(updatedUserResponseEntity.getBody(), expectedUpdatedUser);
   }
}
