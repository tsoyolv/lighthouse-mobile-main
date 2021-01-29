package ru.lighthouse.mobile.main.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.lighthouse.mobile.main.service.user.entity.User;
import ru.lighthouse.mobile.main.service.user.repository.AuthorityRepository;
import ru.lighthouse.mobile.main.service.user.repository.UserRepository;

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

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber);
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
