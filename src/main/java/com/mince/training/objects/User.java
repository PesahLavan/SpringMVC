package com.mince.training.objects;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

    public User() {
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Size(min = 6, message = "{name.size.error}")
    private String name;

    @Size(min = 5, max = 10, message = "{password.size.error}")
    private String password;


    private boolean admin;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
