/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.geraldi.domain;

/**
 *
 * @author admin
 */
public class NewGameRequest {
    private String name;
    private String password;

    public NewGameRequest() {
    }

    public NewGameRequest(String name, String password) {
        this.name = name;
        this.password = password;
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

    @Override
    public String toString() {
        return "NewGameRequest{" + "name=" + name + ", password=" + password + '}';
    }
    
    
    
}
