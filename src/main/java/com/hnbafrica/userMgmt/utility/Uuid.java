package com.hnbafrica.userMgmt.utility;

import org.springframework.stereotype.Service;

import java.util.UUID;



@Service

public class Uuid {
    public String createCode() {
        java.util.UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public  String createCode(String name){
        UUID uuid = UUID.fromString(name);
        return uuid.toString();
    }
}

