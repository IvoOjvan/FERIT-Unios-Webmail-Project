package Classes;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class User {
    private String username;
    private String password;

    public static User GetUser(){
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("./user.json");
        User user;
        try {
            user = mapper.readValue(file, User.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    public User(){}

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }
}
