/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.geraldi.domain;

/**
 *
 * @author admin
 */
public class MessageAPI {
    private String message;

    public MessageAPI() {
    }

    public MessageAPI(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" + "message=" + message + '}';
    }
    
    
}
