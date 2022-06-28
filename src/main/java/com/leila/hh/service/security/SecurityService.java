package com.leila.hh.service.security;

public interface SecurityService {
    boolean isAuthenticated();
    void autoLogin(String username, String password);
}
