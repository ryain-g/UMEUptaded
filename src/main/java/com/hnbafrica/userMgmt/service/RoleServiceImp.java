package com.hnbafrica.userMgmt.service;

import com.hnbafrica.userMgmt.entity.Role;
import com.hnbafrica.userMgmt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImp implements RoleService{

    @Autowired
    RoleRepository roleRepository;


    public void addRole(Role role){
        roleRepository.save(role);
        }
    public List<Role> getAllRoles(){
        List<Role> roles = roleRepository.findAll();
        return roles;
    }
//    public void deleteRoleByName(String name){
//        roleRepository.deleteAllByName(name);
//    }


}
