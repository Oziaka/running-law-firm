package pl.user.directory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.user.User;

import java.util.Optional;

@Repository
interface DirectoryRepository extends JpaRepository<Directory, Long> {
   @Query("SElECT d FROM LawCase lw join lw.directory d join lw.workers w WHERE d.id = :id and w = :user")
   Optional<Directory> getDirectory(@Param("user") User user, @Param("id") Long directoryId);
}
