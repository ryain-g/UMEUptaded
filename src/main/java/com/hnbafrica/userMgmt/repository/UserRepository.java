package com.hnbafrica.userMgmt.repository;

import com.hnbafrica.userMgmt.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.firstName = :firstName")
    public UserEntity findUserByFirstName(@Param("firstName") String firstName);

    @Query("Update UserEntity u set  u.firstName = :firstName")
    public UserEntity updateUserByFirstName(@Param("firstName") String firstName);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("Update UserEntity u set  u.enable = true WHERE u.verificationCode = ?1")
    public int enableUser(@Param("code") String code);
}
