package com.example.demo;

public class LogIn {
    private String id;
    private String username;
    private String password;

    public LogIn(String id, String username, String pass) throws IllegalArgumentException
    {
        this.id = id;

        if(username.length() < 1)
            throw new IllegalArgumentException("Username required.");
        else
            this.username = username;



        if(pass.length()<1)
            throw new IllegalArgumentException("Password required.");
        else
            this.password = pass;
    }

    public String getUsername() { return this.username; }
    public String getPassword(){ return this.password; }
    public String getId() { return this.id; }

    public void setUsername(String username) throws IllegalArgumentException
    {
        if(username.length() < 1)
            throw new IllegalArgumentException("Username required.");
        else
            this.username = username;
    }

    public void setPassword(String pass) throws IllegalArgumentException
    {
        if(pass.length()<1)
            throw new IllegalArgumentException("Password required.");
        else
            this.password = pass;
    }
}
