package com.hnbafrica.userMgmt.service;

import com.hnbafrica.userMgmt.repository.RoleRepository;
import com.hnbafrica.userMgmt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindUserIdRoleId {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

//    public List<Role> getRoleId(List<Role> roleName){
//        List<Role> roleId = roleRepository.findAllRoleByName(roleName);
//        return roleId;
//    }
//
//    public  Long getUserId(String userName){
//        User user = userRepository.findUserByFirstName(userName);
//        Long userId = user.getId();
//        return userId;
//    }

}
