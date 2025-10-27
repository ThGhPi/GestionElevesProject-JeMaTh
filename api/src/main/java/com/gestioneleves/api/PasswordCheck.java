/**package com.gestioneleves.api;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordCheck {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = "$2a$10$z7H1/jMo7N.vCWkX/jgVbOFpnKny6ScxjiX7OuGTMAGnQBOkYo/5q";
        System.out.println("Test kmah: " + encoder.matches("kambest!", hash));
        System.out.println(encoder.encode("kambest!"));
        System.out.println(encoder.encode("Kambest!"));
    }
}*/
