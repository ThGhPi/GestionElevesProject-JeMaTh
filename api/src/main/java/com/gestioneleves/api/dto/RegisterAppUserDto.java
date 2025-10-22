package com.gestioneleves.api.dto;

import com.gestioneleves.api.entity.UserRole;


public class RegisterAppUserDto {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String username;
    private String phoneNumber;
    private String postalAddress;
    private UserRole role;

    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setUsername(String username) { this.username = username; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setPostalAddress(String postalAddress) { this.postalAddress = postalAddress; }
    public void setRole(UserRole role) { this.role = role; }

    public String getFirstname() { return this.firstname; }
    public String getLastname() { return this.lastname; }
    public String getEmail() { return this.email; }
    public String getPassword() { return this.password; }
    public String getUsername() { return this.username; }
    public String getPhoneNumber() { return this.phoneNumber; }
    public String getPostalAddress() { return this.postalAddress; }
    public UserRole getRole() { return this.role; }
}
