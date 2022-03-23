package com.hnbafrica.userMgmt.repository;

import com.hnbafrica.userMgmt.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    //    Long findRoleByName(String role);
//    void deleteAllByName(String name);
//    List<Role> findAllRoleByName(List<Role> roleList);
}
