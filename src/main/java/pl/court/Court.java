package pl.court;

import lombok.*;
import pl.address.Address;

import javax.persistence.*;

@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "court")
@ToString
public class Court {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "court_id")
   private Long id;

   private String name;

   @OneToOne
   @MapsId
   private Address address;

   @Builder
   public Court(Long id, String name, Address address) {
      this.id = id;
      this.name = name;
      this.address = address;
   }
}
