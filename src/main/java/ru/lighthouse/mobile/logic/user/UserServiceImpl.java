package ru.lighthouse.mobile.logic.user;

import org.springframework.stereotype.Service;
import ru.lighthouse.mobile.logic.user.entity.User;
import ru.lighthouse.mobile.logic.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getOrCreate(User user) {
        User byPhoneNumber = userRepository.findByPhoneNumber(user.getPhoneNumber());
        if (byPhoneNumber != null) {
            byPhoneNumber.setLastLogin(user.getLastLogin());
            userRepository.save(byPhoneNumber);
            return byPhoneNumber;
        }
        user.getAuthorities().forEach(a -> a.setUser(user));
        return userRepository.save(user);
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber);
    }
}
