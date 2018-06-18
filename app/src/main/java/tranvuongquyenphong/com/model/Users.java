package tranvuongquyenphong.com.model;

import java.io.Serializable;

public class Users implements Serializable {
    public String username,password,email,birthday,name,id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Users(String password, String username) {
        this.username = username;
        this.password = password;
    }

    public Users() {
    }

    public Users(String email, String birthday, String name, String id) {
        this.email = email;
        this.birthday = birthday;
        this.name = name;
        this.id = id;
    }

    public Users(String username, String password, String email, String birthday, String name, String id) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.name = name;
        this.id = id;
    }
}
