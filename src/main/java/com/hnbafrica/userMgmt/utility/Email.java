package com.hnbafrica.userMgmt.utility;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
public class Email {
 String subject;
 String body;
 String from;
 String to;
 Map<String, Object> props;
}
