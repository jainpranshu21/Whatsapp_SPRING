package com.driver;

import java.time.LocalDate;
import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class WhatsappRepository {

    //Assume that each user belongs to at most one group
    //You can use the below mentioned hashmaps or delete these and create your own.
    private HashMap<Group, List<User>> groupUserMap;
    private HashMap<Group, List<Message>> groupMessageMap;
    private HashMap<Message, User> senderMap;
    private HashMap<Group, User> adminMap;
    private HashSet<String> userMobile;
    private int customGroupCount;
    private int messageId;

    public WhatsappRepository(){
        this.groupMessageMap = new HashMap<Group, List<Message>>();
        this.groupUserMap = new HashMap<Group, List<User>>();
        this.senderMap = new HashMap<Message, User>();
        this.adminMap = new HashMap<Group, User>();
        this.userMobile = new HashSet<>();
        this.customGroupCount = 0;
        this.messageId = 0;
    }
    public String createUser (String name,String mobNo) throws Exception{
        if(userMobile.contains(mobNo))
            throw new Exception("User already exists");
        userMobile.add(mobNo);
        User user=new User(name,mobNo);
        return "SUCCESS";
    }
    public Group createGroup(List<User> users){
        int no=users.size();
        if(no>2){
            customGroupCount++;
            Group group=new Group("Group "+customGroupCount,no);
            groupUserMap.put(group,users);
            adminMap.put(group,users.get(0));
            return group;
        }
        String name=users.get(1).getName();
        Group group=new Group(name,2);
        return group;
    }

    public int createMessage(String content){
       messageId++;
       Message message=new Message(messageId,content, LocalDate.now());
       return messageId;
    }

    public int sendMessage(Message message, User sender, Group group) throws Exception{
        List<User>users=new ArrayList<>();
        if(!groupMessageMap.containsKey(group)){
            groupMessageMap.put(group,new ArrayList<>());
        }
        boolean check1=false,check2=false;
      for(Group group1:groupUserMap.keySet()){
          if(group1==group){
              users=groupUserMap.get(group1);
              check1=true;
          }
      }
      for(User user:users){
          if(user==sender){
              check2=true;
          }
      }
      if(check1)throw new Exception("Group does not exist");
      if(check2)throw new Exception("You are not allowed to send message");
      List<Message>messages=groupMessageMap.get(group);
      messages.add(message);
      groupMessageMap.put(group,messages);
      int count=messages.size();
      return count;
    }
}
