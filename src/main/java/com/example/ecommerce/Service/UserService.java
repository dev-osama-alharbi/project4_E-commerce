package com.example.ecommerce.Service;

import com.example.ecommerce.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    ArrayList<User> users= new ArrayList<>();

    public ArrayList<User> getUser(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }

    public boolean updateUser(int id ,User user){
        for (int i = 0; i <users.size() ; i++) {
            if(users.get(i).getId()==id){
                users.set(i, user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(int id){
        for (int i = 0; i <users.size() ; i++) {
            if (users.get(i).getId()==id){
                users.remove(i);
                return true;
            }
        }
        return false;
    }
}
