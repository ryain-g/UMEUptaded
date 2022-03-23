package com.hnbafrica.userMgmt.service;

import com.hnbafrica.userMgmt.entity.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;


import java.util.List;


public interface RoleService {
         void addRole(Role role);
         List<Role> getAllRoles();
}
