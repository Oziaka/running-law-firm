package pl.address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.user.User;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {


   @Query("SELECT a FROM User u join u.address a WHERE u = :user AND :id = a.id")
   Optional<Address> getUserAddress(@Param("user") User user, @Param("id") Long addressId);

   @Query("SELECT a FROM LawCase lc join lc.workers w join lc.clients c join c.address a WHERE a.id = :id and w = :user")
   Optional<Address> getUserClientAddress(@Param("user") User user, @Param("id") Long addressId);

   @Query("SELECT a FROM LawCase lc join lc.workers w join lc.court c join c.address a WHERE :user = w AND :id = a.id")
   Optional<Address> getAddressBelongsToWorkerCourt(@Param("user") User user, @Param("id") Long addressId);

}
