package ru.lighthouse.mobile.main.service.user;

import ru.lighthouse.mobile.main.service.user.entity.User;

public interface UserService {
    User getOrCreate(User user);
    User getByPhoneNumber(String phoneNumber);
}
