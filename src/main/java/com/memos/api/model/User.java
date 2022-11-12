package com.memos.api.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Document
public class User {
    @Id
    private String id;
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }

    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Email
    private String email;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonIgnore
    private String password;
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    private AuthProvider provider;
    public AuthProvider getProvider() {
        return provider;
    }
    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    private String providerId;
    public String getProviderId() {
        return providerId;
    }
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
