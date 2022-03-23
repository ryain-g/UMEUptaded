package com.hnbafrica.userMgmt.service;


import com.hnbafrica.userMgmt.dto.User;
import com.hnbafrica.userMgmt.entity.UserEntity;

import java.util.List;

public interface UserService {


         String getVerificationCode();
         void addUserAndSendEmail(User user);

         String checkIUserExist(String name);

         List<User> getUserList();

         UserEntity getUserByFirstName(String name);

         void updateUserByFirstName(String name);

         int enableUser(String code);


}
