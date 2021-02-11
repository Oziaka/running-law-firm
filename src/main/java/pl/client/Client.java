package pl.client;

import lombok.*;
import pl.address.Address;

import javax.persistence.*;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "client")
@ToString
public class Client {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "client_id")
   private Long id;

   private String email;

   private String name;

   private String surename;

   private String phoneNumber;

   @OneToOne
   @MapsId
   private Address address;

   @Builder
   public Client(Long id, String email, String name, String surename, String phoneNumber, Address address) {
      this.id = id;
      this.email = email;
      this.name = name;
      this.surename = surename;
      this.phoneNumber = phoneNumber;
      this.address = address;
   }
}
