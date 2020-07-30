package ru.lighthouse.mobile.main.logic.user;

import ru.lighthouse.mobile.main.logic.user.entity.User;

public interface UserService {
    User getOrCreate(User user);
}
