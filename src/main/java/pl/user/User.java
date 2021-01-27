package pl.user;

import lombok.*;
import pl.address.Address;
import pl.user.directory.Directory;
import pl.user_notification.UserNotification;
import pl.user_role.UserRole;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "\"user\"")
@ToString
public class User {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "user_id")
   private Long id;

   @Column(nullable = false, unique = true)
   private String email;

   @Column(nullable = false)
   private String password;

   private String name;

   private String surename;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "user_roles",
      joinColumns = @JoinColumn(name = "id_user"),
      inverseJoinColumns = @JoinColumn(name = "id_user_role"))
   private Set<UserRole> roles;

   @OneToMany(mappedBy = "user")
   private List<UserNotification> userNotifications;

   @OneToOne
   @MapsId
   private Address address;

   @OneToOne
   @MapsId
   private Directory directory;

   @Builder
   public User(Long id, String email, String password, String userName, Set<UserRole> roles, List<UserNotification> userNotifications, Address address) {
      this.id = id;
      this.email = email;
      this.password = password;
//      this.userName = userName;
      this.roles = roles;
      this.userNotifications = userNotifications;
      this.address = address;
   }

   public void addRole(UserRole userRole) {
      if (roles == null)
         roles = new HashSet<>();
      roles.add(userRole);
   }

   public void removeRole(UserRole userRole) {
      this.roles.remove(userRole);
   }

}
