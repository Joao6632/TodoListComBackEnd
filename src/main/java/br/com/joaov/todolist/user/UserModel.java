package br.com.joaov.todolist.user;

public class UserModel {
    
    private String username;
    private String name;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public String GetUsername() {
        return username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

}
