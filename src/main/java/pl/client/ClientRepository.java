package pl.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

   @Query("SELECT c FROM LawCase lc join lc.clients c join lc.workers w WHERE c.id = :id and w = :user")
   Optional<Client> findClient(@Param("id") Long id, @Param("user") User user);

   @Query("SELECT c FROM LawCase lc join lc.clients c join lc.workers w WHERE w = :worker")
   List<Client> findAllClients(@Param("worker") User worker);

}

