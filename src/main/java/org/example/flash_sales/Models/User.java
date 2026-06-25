package org.example.flash_sales.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.example.flash_sales.Enums.UserType;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String id;

    @NotNull
    private String username;



    @Email
    @NotNull
    private String email;

    @Enumerated(EnumType.STRING)
    private UserType type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
