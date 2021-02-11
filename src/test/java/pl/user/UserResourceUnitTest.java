package pl.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.address.Address;
import pl.address.AddressBuilderMaker;
import pl.address.AddressMapper;
import pl.address.AddressProvider;
import pl.user.directory.DirectoryProvider;
import pl.user_role.UserRole;
import pl.user_role.UserRoleProvider;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static pl.tool.RandomUtils.randomLong;

class UserResourceUnitTest {
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
      addressProvider = Mockito.mock(AddressProvider.class);
      userService = new UserService(userRepository, userRoleProvider, passwordEncoder, directoryProvider, addressProvider);
      userResource = new UserResource(userService);
   }

   @Test
   void registerReturnUserDtoWhenRegisteredSuccessful() {
      // given
      UserDto userDto = UserRandomTool.randomUserDto();
      Long userId = randomLong();
      Long addressId = randomLong();
      // when
      Mockito.when(passwordEncoder.encode(any())).thenReturn(userDto.getPassword());
      Mockito.when(userRoleProvider.getDefaults()).thenReturn(new ArrayList<>(defaultUserRoles));
      Mockito.when(addressProvider.saveAddress(any())).thenAnswer(invocation -> {
         Address address = invocation.getArgument(0, Address.class);
         address.setId(addressId);
         return address;
      });
      Mockito.when(userRepository.save(any())).thenAnswer(invocation -> {
         User user = invocation.getArgument(0, User.class);
         user.setId(userId);
         return user;
      });
      ResponseEntity<UserDto> registeredUserResponseEntity = userResource.register(userDto);
      // then
      UserDto expecteduserdto = UserBuilderMaker.toUserDtoBuilder(userDto).id(userId)
         .addressDto(AddressBuilderMaker.toAddressDtoBuilder(userDto.getAddressDto()).id(addressId).build()).password(null).build();
      Assertions.assertEquals(CREATED, registeredUserResponseEntity.getStatusCode());
      Assertions.assertEquals(expecteduserdto, registeredUserResponseEntity.getBody());
   }

   @Test
   void editUserReturnEditedUserWhenAllFieldsValid() {
      // given
      Long userId = randomLong();
      Long addressId = randomLong();
      User user = UserRandomTool.randomUser();
      user = UserRandomTool.randomUserBuilder().password(null).id(userId).address(AddressBuilderMaker.toAddressBuilder(user.getAddress()).id(addressId).build()).build();
      UserDto userWithUpdatedFields = UserRandomTool.randomUserDto();
      // when
      Mockito.when(userRepository.findByEmail(any())).thenReturn(Optional.of(user), Optional.empty());
      Mockito.when(userRepository.save(any())).thenAnswer(invocation -> {
         User userToSave = invocation.getArgument(0, User.class);
         userToSave.setPassword(null);
         return userToSave;
      });
      Principal principal = user::getEmail;
      ResponseEntity<UserDto> updatedUserResponseEntity = userResource.editUser(principal, userWithUpdatedFields);
      // then
      UserDto expectedUpdatedUser = UserBuilderMaker.toUserDtoBuilder(userWithUpdatedFields).id(userId).password(null).addressDto(AddressMapper.toDto(user.getAddress())).build();
      Assertions.assertEquals(OK, updatedUserResponseEntity.getStatusCode());
      Assertions.assertEquals(expectedUpdatedUser, updatedUserResponseEntity.getBody());
   }
}
