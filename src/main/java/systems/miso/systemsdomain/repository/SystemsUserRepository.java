package systems.miso.systemsdomain.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import systems.miso.systemsdomain.model.SystemsUser;

@Repository
public interface SystemsUserRepository extends JpaRepository<SystemsUser, Integer> {
    Optional<SystemsUser> findOneUserByExternalId(final String externalId);
    Set<SystemsUser> findUsersByFirstnameOrLastname(final String firstname, final String lastname);
}
