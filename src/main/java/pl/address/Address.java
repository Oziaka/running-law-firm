package pl.address;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "address")
@ToString
public class Address {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "address_id")
   private Long id;

   private String street;

   private String city;

   private String postcode;

   private String country;

   @Builder
   public Address(Long id, String street, String city, String postcode, String country) {
      this.id = id;
      this.street = street;
      this.city = city;
      this.postcode = postcode;
      this.country = country;
   }
}
