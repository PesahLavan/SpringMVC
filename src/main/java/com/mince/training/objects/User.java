package com.mince.training.objects;

import javax.validation.constraints.Size;

public class User {

    @Size(min = 6, message = "The name must be more than 6 characters")
    private String name;

    @Size(min = 5, max = 10, message = "The password must be between 5 and 10 characters")
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
