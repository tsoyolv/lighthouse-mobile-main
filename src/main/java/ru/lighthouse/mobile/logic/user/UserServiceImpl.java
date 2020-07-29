package ru.lighthouse.mobile.logic.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lighthouse.mobile.logic.user.entity.User;
import ru.lighthouse.mobile.logic.user.repository.AuthorityRepository;
import ru.lighthouse.mobile.logic.user.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public User getOrCreate(User user) {
        User found = repository.findByPhoneNumber(user.getPhoneNumber());
        if (found != null) {
            return updateInternal(found, user);
        } else {
            return createInternal(user);
        }
    }

    private User createInternal(User user) {
        user.setAuthorities(Collections.singleton(authorityRepository.getBySystemName("ROLE_IOS_USER")));
        return repository.save(user);
    }

    private User updateInternal(User toUpdate, User user) {
        toUpdate.setUserAgent(user.getUserAgent());
        toUpdate.setLastLogin(user.getLastLogin());
        repository.save(toUpdate);
        return toUpdate;
    }
}
