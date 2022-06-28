package com.leila.hh.service.security;


import com.leila.hh.entity.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
