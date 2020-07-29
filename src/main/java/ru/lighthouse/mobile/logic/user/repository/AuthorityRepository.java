package ru.lighthouse.mobile.logic.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lighthouse.mobile.logic.user.entity.Authority;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findBySystemName(String systemName);
    Authority getBySystemName(String systemName);
}
