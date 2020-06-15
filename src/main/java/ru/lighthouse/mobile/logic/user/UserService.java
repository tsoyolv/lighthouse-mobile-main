package ru.lighthouse.mobile.logic.user;

import ru.lighthouse.mobile.logic.user.entity.User;

public interface UserService {
    User getOrCreate(User user);
    User getByPhoneNumber(String phoneNumber);
}
