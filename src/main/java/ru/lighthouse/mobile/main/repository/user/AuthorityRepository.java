package ru.lighthouse.mobile.main.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lighthouse.mobile.main.repository.user.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority getBySystemName(String systemName);
}
