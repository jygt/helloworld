package com.example.helloWorld.security;

import java.util.List;

public interface UserRoleService  {
    List<UserRole> findByUserId(String userId);
    Long Count();
}
