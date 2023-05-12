package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WhatsappService {

    WhatsappRepository whatsappRepository=new WhatsappRepository();
    public String createUser(String name, String mobile) throws Exception {
      return whatsappRepository.createUser(name,mobile);
    }
    public Group createGroup(List<User> users){
      return whatsappRepository.createGroup(users);
    }
    public int createMessage(String content){
      return whatsappRepository.createMessage(content);
    }
    public int sendMessage(Message message, User sender, Group group) throws Exception{
      return whatsappRepository.sendMessage(message,sender,group);
    }
   /* public String changeAdmin(User approver, User user, Group group){

    }
    public int removeUser(User user){

    }
    public String findMessage(Date start, Date end, int K){

    }*/
}
