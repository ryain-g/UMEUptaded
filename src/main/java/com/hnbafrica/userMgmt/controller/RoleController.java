package com.hnbafrica.userMgmt.controller;

import com.hnbafrica.userMgmt.entity.Role;
import com.hnbafrica.userMgmt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/roles")
public class RoleController {
    @Autowired
    RoleService roleService;

    @PostMapping(path="/addRole")
    public void addNewRole (@RequestBody Role role) {
        roleService.addRole(role);
    }

    @GetMapping(path="/allRoles")
    @ResponseBody
    public List<Role> getRoles() {
        return roleService.getAllRoles();
    }
}
