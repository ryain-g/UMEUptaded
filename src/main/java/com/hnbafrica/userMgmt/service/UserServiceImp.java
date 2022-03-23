package com.hnbafrica.userMgmt.service;


import com.hnbafrica.userMgmt.utility.Email;
import com.hnbafrica.userMgmt.utility.EmailService;
import com.hnbafrica.userMgmt.utility.Uuid;
import com.hnbafrica.userMgmt.dto.User;
import com.hnbafrica.userMgmt.entity.UserEntity;
import com.hnbafrica.userMgmt.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImp implements UserService{

    private static final Logger log = LoggerFactory.getLogger(UserServiceImp.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    Uuid uuid;

    @Autowired
    EmailService emailService;

    @Autowired
    Email email;

    @Autowired
    Environment environment;

    @Value("${server.address}")
    private String serverAddress;

    @Value("${server.port}")
    private String serverPort;

    @Value("${user.activation.api}")
    private String userActivationApi;

    @Value("${server.http}")
    private String serverHttp;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String getVerificationCode(){
        return uuid.createCode();
    }

    public void addUserAndSendEmail(User user){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        UserEntity userEntity=modelMapper.map(user,UserEntity.class);
        userEntity.setVerificationCode(getVerificationCode());
        userEntity.setEnable(false);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userRepository.save(userEntity);
        //use future class here
        try{
            sendVerificationEmail(userEntity);
        } catch ( InterruptedException e){
            e.printStackTrace();

        }
       


    }
    @Async
    public void sendVerificationEmail(UserEntity user) throws MailException, InterruptedException   {
        Thread.sleep(3000);
       
            String url = serverHttp+serverAddress+":"+serverPort+userActivationApi+user.getVerificationCode();
            log.info("User Activation URL {}",url);
            email.setFrom("innovationhnb@gmail.com");
            email.setTo(user.getEmail());
            email.setSubject("Verification Email");

        Map<String, Object> model = new HashMap<String,Object>();
        model.put("name", user.getFirstName());
        model.put("body", "To confirm your account, please click below : " );
        model.put("link",url);
        model.put("sign", "HnB");
        email.setProps(model);

            try {
                emailService.send(email);
            }catch (MessagingException e){
                e.printStackTrace();
            }

    }

    public String checkIUserExist(String name){
        String userName = userRepository.findUserByFirstName(name).getFirstName();
        if(userName.contains(name)){
            return userName;
        }
        else{
            return null;
        }
    }

    public List<User> getUserList(){
        List<UserEntity> userList= userRepository.findAll();
        return modelMapper.map(userList, new TypeToken<List<User>>() {}.getType());
    }

    public UserEntity getUserByFirstName(String name){
        if(checkIUserExist(name).isEmpty()){
            return null;
        }
        else {

            return  userRepository.findUserByFirstName(name);
        }
    }

    public void updateUserByFirstName(String name){
        userRepository.updateUserByFirstName(name);
    }

    public int enableUser(String code){
        return userRepository.enableUser(code);
    }

}
